package io.reactive.webflux.demo.controller;

import io.reactive.webflux.demo.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/controller")
public record DemoController() {

    @GetMapping
    public Flux<Message> listMessage(@RequestParam(defaultValue = "0") Long start, @RequestParam(defaultValue = "3") Long count) {
        return Flux
                .just("Hello reactive", "More than one", "Third post", "Fourth post", "Fifth post", "Sixth post")
                .skip(start)
                .take(count)
                .map(Message::new);
    }

}
