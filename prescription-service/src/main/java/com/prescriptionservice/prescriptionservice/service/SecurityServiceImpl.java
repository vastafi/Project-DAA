package com.prescriptionservice.prescriptionservice.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityServiceImpl implements SecurityService{
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
    public String getCurrentUserEmail() {
        JwtAuthenticationToken jwtAuthenticationToken = jwtAuthenticationToken();
        Optional<Object> email = Optional.ofNullable(jwtAuthenticationToken.getTokenAttributes().get("email"));
        return (String) email.orElse(null);
    }
}
