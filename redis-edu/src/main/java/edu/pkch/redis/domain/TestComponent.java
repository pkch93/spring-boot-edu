package edu.pkch.redis.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TestComponent {

    @Autowired
    @Qualifier("person")
    private Test2 name;

//    @Resource(name = "redisTemplate")
//    private ValueOperations<String, String> valueOperations;

//    public void test() {
//
//        String hello = valueOperations.get("hello");
//        System.out.println(hello);
//    }
}
