package com.prescriptionservice.prescriptionservice.repository;

import com.prescriptionservice.prescriptionservice.config.DataSource;
import com.prescriptionservice.prescriptionservice.domain.Prescription;
import com.prescriptionservice.prescriptionservice.enums.DataSourceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long>, JpaSpecificationExecutor<Prescription> {
    @DataSource(DataSourceType.SLAVE)
    @Query("SELECT p FROM Prescription p WHERE p.patient.userFk = :userFk")
    Page<Prescription> findAllByUserFk(String userFk, Pageable pageable);

    @Override
    @DataSource(DataSourceType.SLAVE)
    List<Prescription> findAll();

    @Override
    @DataSource(DataSourceType.SLAVE)
    Optional<Prescription> findById(Long id);

    @Override
    @DataSource(DataSourceType.MASTER)
    Prescription save(Prescription prescription);
}