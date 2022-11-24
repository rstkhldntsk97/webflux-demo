package io.reactive.webflux.demo.router;

import io.reactive.webflux.demo.handler.GreetingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {
        return RouterFunctions
                .route(RequestPredicates
                        .GET("/"), serverRequest ->
                        ServerResponse
                                .ok()
                                .body(BodyInserters.fromValue("Welcome " + serverRequest.queryParam("name").orElse("Nobody") + " to Spring WebFlux!")))
                .andRoute(RequestPredicates
                        .GET("/hello")
                        .and(RequestPredicates
                                .accept(MediaType.TEXT_PLAIN)), greetingHandler::hello);
    }

}
