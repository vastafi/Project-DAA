package com.stockservice.stockservice.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.stockservice.stockservice.domain.catalog.DrugCategoryCatalog}
 */
@Value
public class DrugCategoryCatalogDto implements Serializable {
    Long id;
    String name;
}