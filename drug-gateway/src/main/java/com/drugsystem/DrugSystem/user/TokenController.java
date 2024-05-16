package com.drugsystem.DrugSystem.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class TokenController {

    @GetMapping("/authentication/token-details")
    public Mono<Map<String, Object>> getTokenDetails(@AuthenticationPrincipal Mono<OAuth2AuthenticatedPrincipal> principal) {
        return principal
                .map(OAuth2AuthenticatedPrincipal::getAttributes);
    }
}