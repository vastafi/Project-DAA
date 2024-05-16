package com.stockservice.stockservice.domain;

import com.stockservice.stockservice.domain.catalog.DrugCategoryCatalog;
import com.stockservice.stockservice.domain.catalog.DrugFormCatalog;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "drug_stock")
public class DrugStock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drug_stock_id_gen")
    @SequenceGenerator(name = "drug_stock_id_gen", sequenceName = "stock_seq", allocationSize = 1)
    private Long id;
    private String name;
    /**
     * Divizarea
     */
    private Integer splitting;
    /**
     * Doza concentrarea
     */
    private Integer doseConcentration;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_fk")
    private DrugFormCatalog form;
    private String producer;
    private String country;
    private Double quantity;
    private Double price;
    private String description;
    private String activeSubstance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_fk")
    private DrugCategoryCatalog category;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_fk")
//    @JoinTable(name = "pharmacy_drug_stock",
//            joinColumns = @JoinColumn(name = "drug_stock_fk"),
//            inverseJoinColumns = @JoinColumn(name = "pharmacy_fk"))
    private Pharmacy pharmacy;
}
