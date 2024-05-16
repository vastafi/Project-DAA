package com.stockservice.stockservice.service;

import com.stockservice.stockservice.dto.PharmacyDto;
import com.stockservice.stockservice.mapper.PharmacyMapper;
import com.stockservice.stockservice.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PharmacyServiceImpl implements PharmacyService{
    private final PharmacyRepository pharmacyRepository;
    private final PharmacyMapper pharmacyMapper;
    @Override
    public PharmacyDto createUpdatePharmacy(PharmacyDto pharmacyDto) {
        return pharmacyMapper.toDto(pharmacyRepository.save(pharmacyMapper.toEntity(pharmacyDto)));
    }

    @Override
    public PharmacyDto getPharmacyInfo(Long id) {
        return pharmacyMapper.toDto(pharmacyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pharmacy not found")));
    }
}
