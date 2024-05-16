package com.prescriptionservice.prescriptionservice.mapper;

import com.prescriptionservice.prescriptionservice.domain.Doctor;
import com.prescriptionservice.prescriptionservice.dto.DoctorDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DoctorMapper {
    Doctor toEntity(DoctorDto doctorDto);

    DoctorDto toDto(Doctor doctor);

    List<Doctor> toEntityList(List<DoctorDto> doctorDtos);
    List<DoctorDto> toDtoList(List<Doctor> doctors);
}