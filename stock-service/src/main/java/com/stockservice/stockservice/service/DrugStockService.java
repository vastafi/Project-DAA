package com.stockservice.stockservice.service;

import com.stockservice.stockservice.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.support.Acknowledgment;

import java.util.List;

public interface DrugStockService {
    Page<DrugStockListDto> getDrugSuggestions(String name, Pageable pageable);
    DrugStockDto getDrugStock(Long id);
    List<PharmacyDto> getPharmacies(String name);
    DrugStockDetailsDto createUpdateDrugStock(DrugStockDetailsDto drugStockDetailsDto);
    DrugStockDetailsDto getDrugStockDetails(Long id);
    void listenToPrescriptions(PrescriptionKafkaDto prescriptionKafkaDto,
                               String groupId,
                               String key,
                               Integer partition,
                               String topic,
                               Long offset,
                               Acknowledgment ack);
}
