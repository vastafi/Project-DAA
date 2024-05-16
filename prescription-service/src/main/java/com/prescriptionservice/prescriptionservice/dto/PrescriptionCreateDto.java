package com.prescriptionservice.prescriptionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PrescriptionCreateDto {
    private Long id;
    private String series;
    private Long number;
    private LocalDateTime createdDate;
    /**
     * Cod unic de inregistrare
     */
    private String cui;
    private Integer validityDays;
    private PatientDto patient;
    private List<PrescriptionDrugDto> prescriptionDrugs;
}
