package com.prescriptionservice.prescriptionservice.domain;

import com.prescriptionservice.prescriptionservice.domain.catalog.MeasurementUnitCatalog;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "prescription_drug")
public class PrescriptionDrug {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prescription_drug_id_gen")
    @SequenceGenerator(name = "prescription_drug_id_gen", sequenceName = "prescription_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String description;
    private String activeSubstance;
    private String administrationMethod;
    private Double dosage;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prescription_fk")
    private Prescription prescription;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_unit_fk")
    private MeasurementUnitCatalog measurementUnit;
}
