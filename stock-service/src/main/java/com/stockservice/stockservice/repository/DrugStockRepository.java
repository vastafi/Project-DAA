package com.stockservice.stockservice.repository;

import com.stockservice.stockservice.domain.DrugStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugStockRepository extends JpaRepository<DrugStock, Long>, JpaSpecificationExecutor<DrugStock> {
    Page<DrugStock> findByNameContaining(String name, Pageable pageable);
    List<DrugStock> findByNameContaining(String name);
    @Query("SELECT d FROM DrugStock d WHERE d.name LIKE %:name% and d.quantity > :quantity")
    List<DrugStock> findByNameAndQuantity(String name, Double quantity);
    @Query("SELECT d FROM DrugStock d WHERE d.activeSubstance LIKE %:activeSubstance% and d.quantity > :quantity")
    List<DrugStock> findByActiveSubstanceAndQuantity(String activeSubstance, Double quantity);
}