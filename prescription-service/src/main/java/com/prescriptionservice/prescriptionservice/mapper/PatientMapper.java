package com.prescriptionservice.prescriptionservice.mapper;

import com.prescriptionservice.prescriptionservice.domain.Patient;
import com.prescriptionservice.prescriptionservice.dto.PatientDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatientMapper {
    Patient toEntity(PatientDto patientDto);

    PatientDto toDto(Patient patient);

    List<Patient> toEntity(List<PatientDto> patientDtos);
    List<PatientDto> toDto(List<Patient> patients);
}