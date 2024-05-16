package com.prescriptionservice.prescriptionservice.controller;

import com.prescriptionservice.prescriptionservice.dto.PrescriptionCreateDto;
import com.prescriptionservice.prescriptionservice.dto.PrescriptionDto;
import com.prescriptionservice.prescriptionservice.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prescription")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    @GetMapping("/{id}")
    public PrescriptionDto getPrescription(@PathVariable Long id) {
        return prescriptionService.getPrescription(id);
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @PostMapping("/create")
    public PrescriptionCreateDto createPrescription(@RequestBody PrescriptionCreateDto prescriptionCreateDto) {
        return prescriptionService.createPrescription(prescriptionCreateDto);
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @PostMapping("/update")
    public PrescriptionCreateDto updatePrescription(@RequestBody PrescriptionCreateDto prescriptionCreateDto) {
        return prescriptionService.updatePrescription(prescriptionCreateDto);
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @PutMapping("/activate")
    public void activatePrescription(@Param("id") Long id) {
        prescriptionService.activatePrescription(id);
    }

    @PutMapping("/cancel")
    public void cancelPrescription(@Param("id") Long id) {
        prescriptionService.cancelPrescription(id);
    }

    @GetMapping("/my-prescriptions")
    public Page<PrescriptionDto> getMyPrescriptions(Pageable pageable) {
        return prescriptionService.getMyPrescriptions(pageable);
    }

    @GetMapping("/release-drug")
    public void releaseDrugPrescription(@Param("id") Long id) {
        prescriptionService.releaseDrugPrescription(id);
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @GetMapping("/all")
    public List<PrescriptionDto> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }
}
