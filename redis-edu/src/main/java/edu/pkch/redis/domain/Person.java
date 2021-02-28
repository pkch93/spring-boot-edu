package edu.pkch.redis.domain;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Person {

    private final Test2 name = new Name(this);
}
