package com.prescriptionservice.prescriptionservice.domain.catalog;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prescription_status_catalog")
public class PrescriptionStatusCatalog {
    @Id
    private Long id;
    private String name;
}
