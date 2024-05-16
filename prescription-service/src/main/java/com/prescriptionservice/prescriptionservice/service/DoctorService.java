package com.prescriptionservice.prescriptionservice.service;

import com.prescriptionservice.prescriptionservice.dto.DoctorDto;

public interface DoctorService {
    DoctorDto createUpdateDoctor(DoctorDto doctorDto);
    DoctorDto getDoctorInfo(Long id);
    void approveDoctor(Long id);
}
