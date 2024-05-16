package com.prescriptionservice.prescriptionservice.controller;

import com.prescriptionservice.prescriptionservice.dto.PatientDto;
import com.prescriptionservice.prescriptionservice.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    @GetMapping("/{id}")
    public PatientDto getPatient(@PathVariable Long id) {
        return patientService.getPatient(id);
    }

    @PostMapping("/create-update")
    public PatientDto createUpdatePatient(@RequestBody PatientDto patientDto) {
        return patientService.createUpdatePatient(patientDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR')")
    @GetMapping("/all")
    public Page<PatientDto> getPatients(Pageable pageable) {
        return patientService.getPatients(pageable);
    }
}
