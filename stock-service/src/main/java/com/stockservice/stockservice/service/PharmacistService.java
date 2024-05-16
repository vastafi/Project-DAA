package com.stockservice.stockservice.service;

import com.stockservice.stockservice.dto.PharmacistDto;

public interface PharmacistService {
    PharmacistDto createUpdatePharmacist(PharmacistDto pharmacistDto);
    void approvePharmacist(Long id);
    PharmacistDto getPharmacistInfo(Long id);
}
