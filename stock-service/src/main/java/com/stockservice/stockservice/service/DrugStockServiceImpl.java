package com.stockservice.stockservice.service;

import com.stockservice.stockservice.domain.DrugStock;
import com.stockservice.stockservice.domain.Pharmacy;
import com.stockservice.stockservice.dto.*;
import com.stockservice.stockservice.mapper.DrugStockMapper;
import com.stockservice.stockservice.mapper.PharmacyMapper;
import com.stockservice.stockservice.repository.DrugStockRepository;
import com.stockservice.stockservice.utils.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class DrugStockServiceImpl implements DrugStockService{
    private final DrugStockRepository drugStockRepository;
    private final DrugStockMapper drugStockMapper;
    private final PharmacyMapper pharmacyMapper;
    private final SecurityService securityService;
    private final EmailService emailService;
    private final SpringTemplateEngine thymeleafTemplateEngine;
    @Override
    public Page<DrugStockListDto> getDrugSuggestions(String name, Pageable pageable) {
        Page<DrugStock> drugStocks = drugStockRepository.findByNameContaining(name, pageable);

        return new PageImpl<>(drugStockMapper.toListDto(drugStocks.getContent()), pageable, drugStocks.getTotalElements());
    }

    @Override
    public DrugStockDto getDrugStock(Long id) {
        return drugStockMapper.toDto(drugStockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drug not found")));
    }

    @Override
    public List<PharmacyDto> getPharmacies(String name) {
        return pharmacyMapper.toDto(drugStockRepository.findByNameContaining(name)
                .stream().map(DrugStock::getPharmacy).toList());
    }

    @Override
    public DrugStockDetailsDto createUpdateDrugStock(DrugStockDetailsDto drugStockDetailsDto) {
        DrugStock drugStock = drugStockMapper.toEntity(drugStockDetailsDto);
        drugStock.setPharmacy(securityService.getCurrentPharmacy());
        return drugStockMapper.toDetailsDto(drugStockRepository.save(drugStock));
    }

    @Override
    public DrugStockDetailsDto getDrugStockDetails(Long id) {
        return drugStockMapper.toDetailsDto(drugStockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drug not found")));
    }

    @Override
    @KafkaListener(topics = "prescription-topic", groupId = "drug-service")
    public void listenToPrescriptions(@Payload PrescriptionKafkaDto prescriptionKafkaDto,
                                      @Header(KafkaHeaders.GROUP_ID) String groupId,
                                      @Header(KafkaHeaders.RECEIVED_KEY) String key,
                                      @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                                      @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                      @Header(KafkaHeaders.OFFSET) Long offset,
                                      Acknowledgment ack) {
        log.info("groupId " + groupId);
        log.info("key " + key);
        log.info("partition " + partition);
        log.info("topic " + topic);
        log.info("offset " + offset);
        ack.acknowledge();
        List<FoundDrugsDto> foundDrugs = new ArrayList<>();
        prescriptionKafkaDto.getPrescriptionDrugs().forEach(prescriptionDrugDto -> {
            List<DrugStock> alternatives = new ArrayList<>();
            List<DrugStock> exactMatches = drugStockRepository.findByNameAndQuantity(prescriptionDrugDto.getName(), prescriptionDrugDto.getDosage());
            if(exactMatches == null || exactMatches.isEmpty()) {
                alternatives.addAll(drugStockRepository.findByActiveSubstanceAndQuantity(prescriptionDrugDto.getActiveSubstance(), prescriptionDrugDto.getDosage()));
            }
            foundDrugs.add(FoundDrugsDto.builder()
                    .prescriptionDrug(prescriptionDrugDto)
                    .foundDrugs(exactMatches != null ? drugStockMapper.toListWithPharmaciesDto(exactMatches) : null)
                    .alternatives(drugStockMapper.toListWithPharmaciesDto(alternatives))
                    .build());
        });

        Map<String, Object> variables = new HashMap<>();
        variables.put("drugs", foundDrugs);

        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(variables);

        String evaluated = thymeleafTemplateEngine.process("drug_search_results", thymeleafContext);
        emailService.sendHtmlMessage(prescriptionKafkaDto.getPatientEmail(), "Found drugs", evaluated, null);
    }
}
