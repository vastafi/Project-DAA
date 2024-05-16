package com.prescriptionservice.prescriptionservice.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.prescriptionservice.prescriptionservice.domain.catalog.MeasurementUnitCatalog}
 */
@Value
public class MeasurementUnitCatalogDto implements Serializable {
    Long id;
    String name;
}