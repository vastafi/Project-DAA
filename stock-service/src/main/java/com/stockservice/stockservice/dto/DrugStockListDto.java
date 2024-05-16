package com.stockservice.stockservice.dto;

import lombok.Value;

import java.io.Serializable;

@Value
public class DrugStockListDto implements Serializable {
    Long id;
    String name;
    Double price;
}
