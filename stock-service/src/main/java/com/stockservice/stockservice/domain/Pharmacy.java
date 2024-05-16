package com.stockservice.stockservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pharmacy")
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pharmacy_id_gen")
    @SequenceGenerator(name = "pharmacy_id_gen", sequenceName = "stock_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String address;
    private String phone;
}
