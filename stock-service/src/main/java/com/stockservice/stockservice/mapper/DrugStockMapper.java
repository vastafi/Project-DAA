package com.stockservice.stockservice.mapper;

import com.stockservice.stockservice.domain.DrugStock;
import com.stockservice.stockservice.dto.DrugStockDetailsDto;
import com.stockservice.stockservice.dto.DrugStockDto;
import com.stockservice.stockservice.dto.DrugStockListDto;
import com.stockservice.stockservice.dto.DrugStockListWithPharmaciesDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DrugStockMapper {
    DrugStock toEntity(DrugStockDto drugStockDto);
    DrugStock toEntity(DrugStockDetailsDto drugStockDto);

    DrugStockDto toDto(DrugStock drugStock);

    DrugStockListDto toListDto(DrugStock drugStock);

    DrugStockDetailsDto toDetailsDto(DrugStock drugStock);

    DrugStockListWithPharmaciesDto toListWithPharmaciesDto(DrugStock drugStock);

    List<DrugStockListWithPharmaciesDto> toListWithPharmaciesDto(List<DrugStock> drugStocks);

    List<DrugStock> toEntity(List<DrugStockDto> drugStockDtos);
    List<DrugStockDto> toDto(List<DrugStock> drugStocks);
    List<DrugStockListDto> toListDto(List<DrugStock> drugStocks);
    List<DrugStockDetailsDto> toDetailsDto(List<DrugStock> drugStocks);
}