package com.drugsystem.DrugSystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;



@Service
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GatewayWebFilter implements WebFilter {

    @Value("${gateway-context}")
    private String gatewayContext;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        path = path.replace(gatewayContext, "");


        ServerHttpRequest changedRequest = exchange.getRequest().mutate().path(path).build();
        return chain.filter(exchange.mutate().request(changedRequest).build());
    }

}



