package com.prescriptionservice.prescriptionservice.service;

import com.prescriptionservice.prescriptionservice.domain.Doctor;
import com.prescriptionservice.prescriptionservice.dto.DoctorDto;
import com.prescriptionservice.prescriptionservice.mapper.DoctorMapper;
import com.prescriptionservice.prescriptionservice.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final SecurityService securityService;

    @Override
    public DoctorDto createUpdateDoctor(DoctorDto doctorDto) {
        Doctor doctor = doctorMapper.toEntity(doctorDto);
        doctor.setUserFk(securityService.getCurrentUserId());
        return doctorMapper.toDto(doctorRepository.save(doctor));
    }

    @Override
    public DoctorDto getDoctorInfo(Long id) {
        return doctorMapper.toDto(doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found")));
    }

    @Override
    public void approveDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        doctor.setDoctorApprovedFlag(true);
        doctorRepository.save(doctor);
    }
}
