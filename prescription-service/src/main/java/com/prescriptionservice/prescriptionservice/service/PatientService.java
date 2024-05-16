package com.prescriptionservice.prescriptionservice.service;

import com.prescriptionservice.prescriptionservice.dto.PatientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {
    PatientDto createUpdatePatient(PatientDto patientDto);
    PatientDto getPatient(Long id);
    Page<PatientDto> getPatients(Pageable pageable);
}
