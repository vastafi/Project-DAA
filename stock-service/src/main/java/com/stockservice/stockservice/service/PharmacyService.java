package com.stockservice.stockservice.service;

import com.stockservice.stockservice.dto.PharmacyDto;

public interface PharmacyService {
    PharmacyDto createUpdatePharmacy(PharmacyDto pharmacyDto);
    PharmacyDto getPharmacyInfo(Long id);
}
