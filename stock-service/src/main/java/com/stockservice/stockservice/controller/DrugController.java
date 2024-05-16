package com.stockservice.stockservice.controller;

import com.stockservice.stockservice.dto.DrugStockDetailsDto;
import com.stockservice.stockservice.dto.DrugStockDto;
import com.stockservice.stockservice.dto.DrugStockListDto;
import com.stockservice.stockservice.dto.PharmacyDto;
import com.stockservice.stockservice.service.DrugStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drug")
public class DrugController {
    private final DrugStockService drugService;

    @GetMapping("/{id}")
    public DrugStockDto getDrug(@PathVariable Long id) {
        return drugService.getDrugStock(id);
    }

    @GetMapping("/pharmacies")
    public List<PharmacyDto> getPharmacies(@RequestParam String name) {
        return drugService.getPharmacies(name);
    }

    @GetMapping("/suggestions")
    public Page<DrugStockListDto> getDrugSuggestions(@RequestParam String name, Pageable pageable) {
        return drugService.getDrugSuggestions(name, pageable);
    }

    @PreAuthorize("hasRole('ROLE_PHARMACIST')")
    @PostMapping
    public DrugStockDetailsDto createUpdateDrugStock(@RequestBody DrugStockDetailsDto drugStockDto) {
        return drugService.createUpdateDrugStock(drugStockDto);
    }

    @PreAuthorize("hasRole('ROLE_PHARMACIST')")
    @GetMapping("/details/{id}")
    public DrugStockDetailsDto getDrugStockDetails(@PathVariable Long id) {
        return drugService.getDrugStockDetails(id);
    }
}
