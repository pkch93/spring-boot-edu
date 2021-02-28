package edu.pkch.webclient.postbody.controller;


import edu.pkch.webclient.postbody.PostRequest;
import edu.pkch.webclient.postbody.PostResponse;
import edu.pkch.webclient.postbody.request.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/v1/post-requests")
public class PostController {

    @PostMapping("/interface")
    public Mono<PostResponse> interfaceParameter(@RequestBody PostRequest postRequest) {
        return Mono.just(PostResponse.builder()
                .success(true)
                .build());
    }

    @PostMapping("/a")
    public Mono<PostResponse> aPostRequestParameter(@RequestBody APostRequest aPostRequest) {
        return Mono.just(PostResponse.builder()
                .success(true)
                .build());
    }

    @PostMapping("/b")
    public Mono<PostResponse> bPostRequestParameter(@RequestBody BPostRequest<String> bPostRequest) {
        return Mono.just(PostResponse.builder()
                .success(true)
                .build());
    }

    @PostMapping("/c")
    public Mono<PostResponse> cPostRequestParameter(@RequestBody CPostRequest cPostRequest) {
        return Mono.just(PostResponse.builder()
                .success(true)
                .build());
    }

    @PostMapping("/d")
    public Mono<PostResponse> dPostRequestParameter(@RequestBody DPostRequest dPostRequest) {
        return Mono.just(PostResponse.builder()
                .success(true)
                .build());
    }

    @PostMapping("/d2")
    public Mono<PostResponse> dPostRequestParameter(@RequestBody D2PostRequest d2PostRequest) {
        return Mono.just(PostResponse.builder()
                .success(true)
                .build());
    }

    @PostMapping("/e")
    public Mono<PostResponse> ePostRequestParameter(@RequestBody EPostRequest ePostRequest) {
        log.info("postRequest: {}", ePostRequest);
        return Mono.just(PostResponse.builder()
                .success(true)
                .build());
    }

    @PostMapping("/f")
    public Mono<PostResponse> fPostRequestParameter(@RequestBody FPostRequest fPostRequest) {
        log.info("postRequest: {}", fPostRequest);
        return Mono.just(PostResponse.builder()
                .success(true)
                .build());
    }
}
