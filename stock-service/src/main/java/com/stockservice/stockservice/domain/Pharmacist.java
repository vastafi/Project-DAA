package com.stockservice.stockservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pharmacist")
public class Pharmacist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pharmacist_id_gen")
    @SequenceGenerator(name = "pharmacist_id_gen", sequenceName = "stock_seq", allocationSize = 1)
    private Long id;
    private String licenseCode;
    private String firstName;
    private String lastName;
    private String phone;
    private Boolean pharmacistApprovedFlag;
    private String userFk;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_fk")
    private Pharmacy pharmacy;
}
