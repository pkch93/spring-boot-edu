package edu.pkch.web.controller;

import edu.pkch.starter.Tester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostApiController {

    private final Tester tester;

    public PostApiController(Tester tester) {
        this.tester = tester;
    }

    @GetMapping
    public String hello() {
        return String.format("hello! tester %s", tester.getName());
    }
}
