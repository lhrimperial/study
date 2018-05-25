package com.github.study.transaction.sharding.aspect;

import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class Processor1 {

    public void say(String name) {
        System.out.println("hello " + name);
    }
}
