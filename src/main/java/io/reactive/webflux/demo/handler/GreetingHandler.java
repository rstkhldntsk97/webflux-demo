package io.reactive.webflux.demo.handler;

import io.reactive.webflux.demo.model.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        Long start = request.queryParam("start").map(Long::valueOf).orElse(0L);
        Long count = request.queryParam("count").map(Long::valueOf).orElse(3L);
        Flux<Message> data = Flux
                .just("Hello reactive", "More than one", "Third post", "Fourth post", "Fifth post", "Sixth post")
                .skip(start)
                .take(count)
                .map(Message::new);

        return ServerResponse.ok()
                .body(data, Message.class);
    }

}
