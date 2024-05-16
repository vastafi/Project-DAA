package com.stockservice.stockservice.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionDrugDto implements Serializable {
    Long id;
    String name;
    String description;
    String manufacturer;
    String activeSubstance;
    String administrationMethod;
    Double dosage;
    MeasurementUnitCatalogDto measurementUnit;
}