package com.prescriptionservice.prescriptionservice.controller;

import com.prescriptionservice.prescriptionservice.dto.DoctorDto;
import com.prescriptionservice.prescriptionservice.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @GetMapping("/{id}")
    public DoctorDto getDoctor(@PathVariable Long id) {
        return doctorService.getDoctorInfo(id);
    }

    @PostMapping("/create-update")
    public DoctorDto createUpdateDoctor(@RequestBody DoctorDto doctorDto) {
        return doctorService.createUpdateDoctor(doctorDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/approve")
    public void approveDoctor(@RequestParam Long id) {
        doctorService.approveDoctor(id);
    }
}
