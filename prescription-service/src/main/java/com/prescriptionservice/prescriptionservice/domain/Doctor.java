package com.prescriptionservice.prescriptionservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_id_gen")
    @SequenceGenerator(name = "doctor_id_gen", sequenceName = "prescription_seq", allocationSize = 1)
    private Long id;
    /**
     * Codul de parafa al medicului
     */
    private String code;
    private String firstName;
    private String lastName;
    private String phone;
    private Boolean doctorApprovedFlag;
    private String userFk;
}
