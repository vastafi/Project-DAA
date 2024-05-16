package com.prescriptionservice.prescriptionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PrescriptionDto {
    private Long id;
    private String series;
    private Long number;
    private LocalDateTime createdDate;
    private PrescriptionStatusCatalogDto status;
    /**
     * Cod unic de inregistrare
     */
    private String cui;
    private Integer validityDays;
    private PatientDto patient;
    private DoctorDto doctor;
    private List<PrescriptionDrugDto> prescriptionDrugs;
}
