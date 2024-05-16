package com.stockservice.stockservice.controller;

import com.stockservice.stockservice.dto.PharmacistDto;
import com.stockservice.stockservice.service.PharmacistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pharmacist")
public class PharmacistController {
    private final PharmacistService pharmacistService;

    @PostMapping
    public PharmacistDto createUpdatePharmacist(@RequestBody PharmacistDto pharmacistDto) {
        return pharmacistService.createUpdatePharmacist(pharmacistDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/approve/{id}")
    public void approvePharmacist(@PathVariable Long id) {
        pharmacistService.approvePharmacist(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PHARMACIST')")
    @GetMapping("/{id}")
    public PharmacistDto getPharmacistInfo(@PathVariable Long id) {
        return pharmacistService.getPharmacistInfo(id);
    }
}
