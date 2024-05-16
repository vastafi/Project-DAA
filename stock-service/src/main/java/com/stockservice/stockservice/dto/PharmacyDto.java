package com.stockservice.stockservice.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.stockservice.stockservice.domain.Pharmacy}
 */
@Value
public class PharmacyDto implements Serializable {
    Long id;
    String name;
    String address;
    String phone;
}