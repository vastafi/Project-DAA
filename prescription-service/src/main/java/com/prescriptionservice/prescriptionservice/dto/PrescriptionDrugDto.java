package com.prescriptionservice.prescriptionservice.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.prescriptionservice.prescriptionservice.domain.PrescriptionDrug}
 */
@Value
public class PrescriptionDrugDto implements Serializable {
    Long id;
    String name;
    String description;
    String activeSubstance;
    String administrationMethod;
    Double dosage;
    MeasurementUnitCatalogDto measurementUnit;
}