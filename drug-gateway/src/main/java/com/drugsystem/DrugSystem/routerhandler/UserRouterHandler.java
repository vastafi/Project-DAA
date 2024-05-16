package com.drugsystem.DrugSystem.routerhandler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface UserRouterHandler {

    public Mono<ServerResponse> getCurrentUserPreferredName(ServerRequest serverRequest);

}
