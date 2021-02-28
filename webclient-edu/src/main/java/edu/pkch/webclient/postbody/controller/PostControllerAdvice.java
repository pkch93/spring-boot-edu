package edu.pkch.webclient.postbody.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class PostControllerAdvice {

    @ExceptionHandler(Exception.class)
    public Mono<Boolean> handleException(Throwable e) {
        log.error("error: {}", e.getMessage(), e);
        return Mono.just(false);
    }
}
