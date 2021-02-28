package edu.pkch.redis.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SpringRedisEduApplicationTest {

    @Autowired
    private TestComponent testComponent;

    @Test
    void contextLoad(ApplicationContext applicationContext) {
        assertTrue(applicationContext.containsBean("redisTemplate"));
    }
}