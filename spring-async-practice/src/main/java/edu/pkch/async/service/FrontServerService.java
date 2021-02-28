package edu.pkch.async.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class FrontServerService {
    private static final Logger logger = LoggerFactory.getLogger(FrontServerService.class);

    public ListenableFuture<String> hello() {
        logger.info("thread: {}", Thread.currentThread().getName());
        return AsyncResult.forValue("hello");
    }
}
