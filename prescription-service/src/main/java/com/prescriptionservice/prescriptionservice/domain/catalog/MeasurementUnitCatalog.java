package com.prescriptionservice.prescriptionservice.domain.catalog;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "measurement_unit_catalog")
public class MeasurementUnitCatalog {
    @Id
    private Long id;
    private String name;
    private String abbreviation;
}
