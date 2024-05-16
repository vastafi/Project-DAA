package com.prescriptionservice.prescriptionservice.domain;

import com.prescriptionservice.prescriptionservice.domain.catalog.PrescriptionStatusCatalog;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "prescription")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prescription_id_gen")
    @SequenceGenerator(name = "prescription_id_gen", sequenceName = "prescription_seq", allocationSize = 1)
    private Long id;
    private String series;
    private Long number;
    @CreatedDate
    private LocalDateTime createdDate;
    /**
     * Cod unic de inregistrare
     */
    private String cui;
    private Integer validityDays;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_fk")
    private PrescriptionStatusCatalog status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_fk")
    private Doctor doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_fk")
    private Patient patient;
    @OneToMany(mappedBy = "prescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrescriptionDrug> prescriptionDrugs;
}
