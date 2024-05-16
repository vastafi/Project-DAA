package com.prescriptionservice.prescriptionservice.mapper;

import com.prescriptionservice.prescriptionservice.domain.Prescription;
import com.prescriptionservice.prescriptionservice.dto.PrescriptionCreateDto;
import com.prescriptionservice.prescriptionservice.dto.PrescriptionDto;
import com.prescriptionservice.prescriptionservice.dto.PrescriptionKafkaDto;
import com.prescriptionservice.prescriptionservice.service.SecurityService;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", builder = @org.mapstruct.Builder(disableBuilder = true))
public interface PrescriptionMapper {
     PrescriptionCreateDto toCreateDto(Prescription prescription);
     Prescription toEntity(PrescriptionCreateDto prescriptionCreateDto);
     PrescriptionDto toDto(Prescription prescription);
     @Mapping(target = "patientEmail", expression = "java(securityService.getCurrentUserEmail())")
     PrescriptionKafkaDto toKafkaDto(Prescription prescription, @Context SecurityService securityService);

     List<PrescriptionCreateDto> toDtoCreateList(List<Prescription> prescriptions);
     List<Prescription> toEntityList(List<PrescriptionCreateDto> prescriptionCreateDtos);
     List<PrescriptionDto> toDtoList(List<Prescription> prescriptions);

     @AfterMapping
     default void setPrescriptionDrugs(@MappingTarget Prescription prescription) {
            prescription.getPrescriptionDrugs().forEach(prescriptionDrug -> prescriptionDrug.setPrescription(prescription));
     }
}
