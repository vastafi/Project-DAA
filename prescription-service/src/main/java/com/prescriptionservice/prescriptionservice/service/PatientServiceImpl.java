package com.prescriptionservice.prescriptionservice.service;

import com.prescriptionservice.prescriptionservice.domain.Patient;
import com.prescriptionservice.prescriptionservice.dto.PatientDto;
import com.prescriptionservice.prescriptionservice.mapper.PatientMapper;
import com.prescriptionservice.prescriptionservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final SecurityService securityService;

    @Override
    public PatientDto createUpdatePatient(PatientDto patientDto) {
        Patient patient = patientMapper.toEntity(patientDto);
        patient.setUserFk(securityService.getCurrentUserId());
        return patientMapper.toDto(patientRepository.save(patient));
    }

    @Override
    public PatientDto getPatient(Long id) {
        return patientMapper.toDto(patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found")));
    }

    @Override
    public Page<PatientDto> getPatients(Pageable pageable) {
        Page<Patient> patients = patientRepository.findAll(pageable);
        return new PageImpl<>(patientMapper.toDto(patients.getContent()), pageable, patients.getTotalElements());
    }
}
