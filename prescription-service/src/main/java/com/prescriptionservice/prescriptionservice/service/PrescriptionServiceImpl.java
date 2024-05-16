package com.prescriptionservice.prescriptionservice.service;

import com.prescriptionservice.prescriptionservice.domain.Prescription;
import com.prescriptionservice.prescriptionservice.domain.catalog.PrescriptionStatusCatalog;
import com.prescriptionservice.prescriptionservice.dto.PrescriptionCreateDto;
import com.prescriptionservice.prescriptionservice.dto.PrescriptionDto;
import com.prescriptionservice.prescriptionservice.dto.PrescriptionKafkaDto;
import com.prescriptionservice.prescriptionservice.enums.PrescriptionStatusEnum;
import com.prescriptionservice.prescriptionservice.mapper.PrescriptionMapper;
import com.prescriptionservice.prescriptionservice.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService{
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMapper prescriptionMapper;
    private final SecurityService securityService;
    private final KafkaTemplate<String, PrescriptionKafkaDto> kafkaTemplate;
    @Value(value = "${kafka.prescription-topic}")
    private String prescriptionTopic;

    @Override
    public PrescriptionCreateDto createPrescription(PrescriptionCreateDto prescriptionCreateDto) {
        Prescription prescription = prescriptionMapper.toEntity(prescriptionCreateDto);
        prescription.setStatus(PrescriptionStatusCatalog.builder().id(PrescriptionStatusEnum.DRAFT.getId()).build());
        return prescriptionMapper.toCreateDto(prescriptionRepository.save(prescription));
    }

    @Override
    public PrescriptionDto getPrescription(Long id) {
        return prescriptionMapper.toDto(prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found")));
    }

    @Override
    public PrescriptionCreateDto updatePrescription(PrescriptionCreateDto prescriptionCreateDto) {
        Prescription prescription = prescriptionRepository.findById(prescriptionCreateDto.getId())
                .orElseThrow(() -> new RuntimeException("Prescription not found"));

        if(!prescription.getStatus().getId().equals(PrescriptionStatusEnum.DRAFT.getId()))
            throw new RuntimeException("Prescription is active and cannot be updated");
        Prescription updatedPrescription = prescriptionMapper.toEntity(prescriptionCreateDto);
        updatedPrescription.setStatus(prescription.getStatus());
        return prescriptionMapper.toCreateDto(prescriptionRepository.save(updatedPrescription));
    }

    @Override
    public void activatePrescription(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
        if(!PrescriptionStatusEnum.getEnumById(prescription.getStatus().getId()).isTransitionAllowed(PrescriptionStatusEnum.ACTIVE))
            throw new RuntimeException("State transition not allowed");
        prescription.setStatus(PrescriptionStatusCatalog.builder().id(PrescriptionStatusEnum.ACTIVE.getId()).build());
        prescriptionRepository.save(prescription);
    }

    @Override
    public void cancelPrescription(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
        if(!PrescriptionStatusEnum.getEnumById(prescription.getStatus().getId()).isTransitionAllowed(PrescriptionStatusEnum.CANCELLED))
            throw new RuntimeException("State transition not allowed");
        prescription.setStatus(PrescriptionStatusCatalog.builder().id(PrescriptionStatusEnum.CANCELLED.getId()).build());
        prescriptionRepository.save(prescription);
    }

    @Override
    public Page<PrescriptionDto> getMyPrescriptions(Pageable pageable) {
        List<PrescriptionDto> prescriptionDtos = prescriptionMapper.toDtoList(prescriptionRepository.findAllByUserFk(securityService.getCurrentUserId(), pageable).getContent());
        return new PageImpl<>(prescriptionDtos, pageable, prescriptionDtos.size());
    }

    @Override
    public void releaseDrugPrescription(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
        CompletableFuture<SendResult<String, PrescriptionKafkaDto>> future = kafkaTemplate.send(prescriptionTopic, LocalDateTime.now().toString(), prescriptionMapper.toKafkaDto(prescription, securityService));
        future.whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Sent prescription with id=[" + prescription.getId() +
                                "] with offset=[" + result.getRecordMetadata().offset() + "]");
                    } else {
                        log.error("Unable to send prescription with id=["
                                + prescription.getId() + "] due to : " + ex.getMessage());
                    }
                }
        );
    }

    @Override
    public List<PrescriptionDto> getAllPrescriptions() {
        return prescriptionMapper.toDtoList(prescriptionRepository.findAll());
    }
}
