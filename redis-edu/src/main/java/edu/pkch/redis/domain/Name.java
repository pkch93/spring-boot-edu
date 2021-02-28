package edu.pkch.redis.domain;

import org.springframework.stereotype.Component;

public class Name extends AbstractName implements Test2 {

    public Name(Person person) {
        super(person);
    }
}
