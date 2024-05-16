package com.prescriptionservice.prescriptionservice.repository;

import com.prescriptionservice.prescriptionservice.config.DataSource;
import com.prescriptionservice.prescriptionservice.domain.Patient;
import com.prescriptionservice.prescriptionservice.enums.DataSourceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {
    @Override
    @DataSource(DataSourceType.SLAVE)
    Optional<Patient> findById(Long id);

    @Override
    @DataSource(DataSourceType.MASTER)
    Patient save(Patient patient);

    @Override
    @DataSource(DataSourceType.SLAVE)
    Page<Patient> findAll(Pageable pageable);
}