package com.stockservice.stockservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoundDrugsDto {
    public PrescriptionDrugDto prescriptionDrug;
    public List<DrugStockListWithPharmaciesDto> foundDrugs;
    public List<DrugStockListWithPharmaciesDto> alternatives;
}
