package com.prescriptionservice.prescriptionservice.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.prescriptionservice.prescriptionservice.domain.catalog.PrescriptionStatusCatalog}
 */
@Value
public class PrescriptionStatusCatalogDto implements Serializable {
    Long id;
    String name;
}