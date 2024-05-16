package com.stockservice.stockservice.service;

import com.stockservice.stockservice.config.JwtAuthConverter;
import com.stockservice.stockservice.domain.Pharmacy;
import com.stockservice.stockservice.repository.PharmacistRepository;
import com.stockservice.stockservice.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService{
    private final PharmacistRepository pharmacistRepository;

    private JwtAuthenticationToken jwtAuthenticationToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new RuntimeException("authentication is null");
        }

        if (authentication instanceof JwtAuthenticationToken ) {
            JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
            return jwtAuthenticationToken;
        } else {
            throw new RuntimeException("authentication is not a JwtAuthenticationToken");
        }
    }
    @Override
    public String getCurrentUserId() {
        JwtAuthenticationToken jwtAuthenticationToken = jwtAuthenticationToken();
        Optional<Object> subId = Optional.ofNullable(jwtAuthenticationToken.getTokenAttributes().get("sub"));
        return (String) subId.orElse(null);
    }

    @Override
    public Pharmacy getCurrentPharmacy() {
        return pharmacistRepository.findByUserFk(getCurrentUserId()).getPharmacy();
    }
}
