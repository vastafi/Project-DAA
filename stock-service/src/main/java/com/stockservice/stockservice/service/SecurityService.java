package com.stockservice.stockservice.service;

import com.stockservice.stockservice.domain.Pharmacy;

public interface SecurityService {
    String getCurrentUserId();
    Pharmacy getCurrentPharmacy();
}
