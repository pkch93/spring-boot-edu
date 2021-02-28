package edu.pkch.async.controller;

import edu.pkch.async.service.FrontServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FrontServerController {
    private static final Logger logger = LoggerFactory.getLogger(FrontServerController.class);
    private final FrontServerService frontServerService;

    public FrontServerController(FrontServerService frontServerService) {
        this.frontServerService = frontServerService;
    }

    @Async
    @GetMapping
    public ListenableFuture<String> hello() {
        logger.info("thread: {}", Thread.currentThread().getName());
        return frontServerService.hello();
    }
}
