package com.stockservice.stockservice.domain.catalog;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "drug_form_catalog")
public class DrugFormCatalog {
    @Id
    private Long id;
    private String name;
    private String abbreviation;
}
