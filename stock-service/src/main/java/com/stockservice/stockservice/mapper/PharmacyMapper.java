package com.stockservice.stockservice.mapper;

import com.stockservice.stockservice.domain.Pharmacy;
import com.stockservice.stockservice.dto.PharmacyDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PharmacyMapper {
    Pharmacy toEntity(PharmacyDto pharmacyDto);

    PharmacyDto toDto(Pharmacy pharmacy);

    List<Pharmacy> toEntity(List<PharmacyDto> pharmacyDtos);
    List<PharmacyDto> toDto(List<Pharmacy> pharmacies);
}