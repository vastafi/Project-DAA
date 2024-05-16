package com.stockservice.stockservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PrescriptionKafkaDto implements Serializable {
    private String patientEmail;
    private List<PrescriptionDrugDto> prescriptionDrugs;
}
