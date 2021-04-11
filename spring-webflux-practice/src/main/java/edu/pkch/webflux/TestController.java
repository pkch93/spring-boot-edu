package edu.pkch.webflux;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class TestController {

    @GetMapping("/cold")
    public Flux<Integer> cold() {
        return Flux.just(1, 2, 3);
    }

    @GetMapping(value = "/hot", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> hot() {
        return Flux.create(sink -> {
            while (true) {
                sink.next(stopOneSecond());
            }
        });
    }

    private int stopOneSecond() {
        try {
            Thread.sleep(1000);
            return 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 1;
        }
    }
}
