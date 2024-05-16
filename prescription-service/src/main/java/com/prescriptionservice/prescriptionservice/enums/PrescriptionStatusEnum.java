package com.prescriptionservice.prescriptionservice.enums;

import java.util.Arrays;

public enum PrescriptionStatusEnum {
    DRAFT(1L) {
        @Override
        public boolean isTransitionAllowed(PrescriptionStatusEnum newStatus) {
            return newStatus == ACTIVE || newStatus == DRAFT;
        }

    },
    ACTIVE(2L) {
        @Override
        public boolean isTransitionAllowed(PrescriptionStatusEnum newStatus) {
            return newStatus == CANCELLED || newStatus == ACTIVE;
        }

    },
    CANCELLED(3L) {
        @Override
        public boolean isTransitionAllowed(PrescriptionStatusEnum newStatus) {
            return false;
        }
    };

    private final Long id;

    PrescriptionStatusEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static PrescriptionStatusEnum getEnumById(Long id) {
        return Arrays.stream(PrescriptionStatusEnum.values())
                .filter(e -> e.getId().equals(id)).findFirst()
                .orElse(null);
    }

    public abstract boolean isTransitionAllowed(PrescriptionStatusEnum newStatus);
}
