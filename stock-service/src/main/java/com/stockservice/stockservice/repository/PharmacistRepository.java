package com.stockservice.stockservice.repository;

import com.stockservice.stockservice.domain.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {
    Pharmacist findByUserFk(String userFk);
}