package com.prescriptionservice.prescriptionservice.repository;

import com.prescriptionservice.prescriptionservice.config.DataSource;
import com.prescriptionservice.prescriptionservice.domain.Doctor;
import com.prescriptionservice.prescriptionservice.enums.DataSourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Override
    @DataSource(DataSourceType.SLAVE)
    Optional<Doctor> findById(Long id);

    @Override
    @DataSource(DataSourceType.MASTER)
    Doctor save(Doctor doctor);
}