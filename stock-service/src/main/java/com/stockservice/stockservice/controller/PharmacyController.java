package com.stockservice.stockservice.controller;

import com.stockservice.stockservice.dto.PharmacyDto;
import com.stockservice.stockservice.service.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pharmacy")
public class PharmacyController {
    private final PharmacyService pharmacyService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public PharmacyDto createUpdatePharmacy(@RequestBody PharmacyDto pharmacyDto) {
        return pharmacyService.createUpdatePharmacy(pharmacyDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public PharmacyDto getPharmacyInfo(@PathVariable Long id) {
        return pharmacyService.getPharmacyInfo(id);
    }
}
