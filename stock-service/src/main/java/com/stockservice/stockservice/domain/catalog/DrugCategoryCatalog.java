package com.stockservice.stockservice.domain.catalog;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "drug_category_catalog")
public class DrugCategoryCatalog {
    @Id
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_fk")
    private DrugCategoryCatalog parent;
}
