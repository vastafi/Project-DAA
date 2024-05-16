package com.drugsystem.DrugSystem.routerhandler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserRouterHandlerImpl implements UserRouterHandler {

    private static String apply(SecurityContext securityContext) {
        if (securityContext.getAuthentication() instanceof OAuth2AuthenticationToken){
            Authentication authentication = securityContext.getAuthentication();
            String prefferedUserName = authentication.getName();
           return prefferedUserName;
        } else {
            throw new RuntimeException("Authentication is not OAuth2User");
        }
    }

    @Override
    public Mono<ServerResponse> getCurrentUserPreferredName(ServerRequest serverRequest) {
        return ReactiveSecurityContextHolder.getContext()
                .map(UserRouterHandlerImpl::apply)
                .flatMap(res -> ServerResponse.ok().bodyValue(res))
                .switchIfEmpty(ServerResponse.status(HttpStatus.UNAUTHORIZED).build());
    }
}
