package com.stockservice.stockservice.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.stockservice.stockservice.domain.DrugStock}
 */
@Value
public class DrugStockDto implements Serializable {
    Long id;
    String name;
    Integer splitting;
    Integer doseConcentration;
    String producer;
    DrugFormCatalogDto form;
    String country;
    Double quantity;
    Double price;
    String description;
}