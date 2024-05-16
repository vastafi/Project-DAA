package com.prescriptionservice.prescriptionservice.service;

import com.prescriptionservice.prescriptionservice.dto.PrescriptionCreateDto;
import com.prescriptionservice.prescriptionservice.dto.PrescriptionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PrescriptionService {
    PrescriptionCreateDto createPrescription(PrescriptionCreateDto prescriptionCreateDto);
    PrescriptionDto getPrescription(Long id);
    PrescriptionCreateDto updatePrescription(PrescriptionCreateDto prescriptionCreateDto);
    void activatePrescription(Long id);
    void cancelPrescription(Long id);
    Page<PrescriptionDto> getMyPrescriptions(Pageable pageable);
    void releaseDrugPrescription(Long id);
    List<PrescriptionDto> getAllPrescriptions();
}
