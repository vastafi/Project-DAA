package com.stockservice.stockservice.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeasurementUnitCatalogDto implements Serializable {
    Long id;
    String name;
}