package com.stockservice.stockservice.dto;

import com.stockservice.stockservice.dto.DrugCategoryCatalogDto;
import com.stockservice.stockservice.dto.DrugFormCatalogDto;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.stockservice.stockservice.domain.DrugStock}
 */
@Value
public class DrugStockDetailsDto implements Serializable {
    Long id;
    String name;
    Integer splitting;
    Integer doseConcentration;
    DrugFormCatalogDto form;
    String producer;
    String country;
    Double quantity;
    Double price;
    String description;
    DrugCategoryCatalogDto category;
}