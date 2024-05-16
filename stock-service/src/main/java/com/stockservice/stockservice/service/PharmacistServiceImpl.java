package com.stockservice.stockservice.service;

import com.stockservice.stockservice.domain.Pharmacist;
import com.stockservice.stockservice.dto.PharmacistDto;
import com.stockservice.stockservice.mapper.PharmacistMapper;
import com.stockservice.stockservice.repository.PharmacistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PharmacistServiceImpl implements PharmacistService{
    private final PharmacistRepository pharmacistRepository;
    private final PharmacistMapper pharmacistMapper;
    private final SecurityService securityService;
    @Override
    public PharmacistDto createUpdatePharmacist(PharmacistDto pharmacistDto) {
        Pharmacist pharmacist = pharmacistMapper.toEntity(pharmacistDto);
        pharmacist.setUserFk(securityService.getCurrentUserId());
        return pharmacistMapper.toDto(pharmacistRepository.save(pharmacist));
    }

    @Override
    public void approvePharmacist(Long id) {
        Pharmacist pharmacist = pharmacistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pharmacist not found"));
        pharmacist.setPharmacistApprovedFlag(true);
        pharmacistRepository.save(pharmacist);
    }

    @Override
    public PharmacistDto getPharmacistInfo(Long id) {
        return pharmacistMapper.toDto(pharmacistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pharmacist not found")));
    }
}
