package com.prescriptionservice.prescriptionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DoctorDto {
    private Long id;
    /**
     * Codul de parafa al medicului
     */
    private String code;
    private String firstName;
    private String lastName;
    private String phone;
}
