package com.stockservice.stockservice.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.stockservice.stockservice.domain.Pharmacist}
 */
@Value
public class PharmacistDto implements Serializable {
    Long id;
    String licenceCode;
    String firstName;
    String lastName;
    String phone;
}