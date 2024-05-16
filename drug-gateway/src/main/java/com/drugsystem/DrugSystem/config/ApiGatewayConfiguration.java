package com.drugsystem.DrugSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiGatewayConfiguration {

    @Autowired
    private TokenRelayGatewayFilterFactory filterFactory;

    @Bean
    public RouteLocator customRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                //api
                .route(r -> r.path("/prescription-service/**")
                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri("lb://prescription-service"))
                .route(r -> r.path("/stock-service/**")
                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri("lb://stock-service"))
                .build();
    }
}
