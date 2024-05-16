package com.prescriptionservice.prescriptionservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_id_gen")
    @SequenceGenerator(name = "patient_id_gen", sequenceName = "prescription_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime birthDate;
    private String insurancePolicyNr;
    private String address;
    private String userFk;
}
