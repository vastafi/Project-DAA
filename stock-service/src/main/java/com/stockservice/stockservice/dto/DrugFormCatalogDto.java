package com.stockservice.stockservice.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.stockservice.stockservice.domain.catalog.DrugFormCatalog}
 */
@Value
public class DrugFormCatalogDto implements Serializable {
    Long id;
    String name;
    String abbreviation;
}