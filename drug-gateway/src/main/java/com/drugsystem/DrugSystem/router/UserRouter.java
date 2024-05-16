package com.drugsystem.DrugSystem.router;

import com.drugsystem.DrugSystem.routerhandler.UserRouterHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> getUserRoute(UserRouterHandler userRouterHandler) {

        return RouterFunctions
                .route(GET("/authentication/user").and(accept(MediaType.APPLICATION_JSON)),
                        userRouterHandler::getCurrentUserPreferredName);
    }
}
