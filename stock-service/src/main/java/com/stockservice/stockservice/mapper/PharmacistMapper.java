package com.stockservice.stockservice.mapper;

import com.stockservice.stockservice.domain.Pharmacist;
import com.stockservice.stockservice.dto.PharmacistDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PharmacistMapper {
    Pharmacist toEntity(PharmacistDto pharmacistDto);

    PharmacistDto toDto(Pharmacist pharmacist);
}