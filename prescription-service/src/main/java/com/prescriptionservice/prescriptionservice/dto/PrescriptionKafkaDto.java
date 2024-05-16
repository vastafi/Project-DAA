package com.prescriptionservice.prescriptionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PrescriptionKafkaDto {
    private String patientEmail;
    private List<PrescriptionDrugDto> prescriptionDrugs;
}
