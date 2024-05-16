package com.stockservice.stockservice.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
public class DrugStockListWithPharmaciesDto implements Serializable {
    Long id;
    String name;
    Double price;
    PharmacyDto pharmacy;
}
