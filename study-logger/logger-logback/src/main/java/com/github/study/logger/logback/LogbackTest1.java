package com.github.study.logger.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 */
public class LogbackTest1 {
    private Logger logger = LoggerFactory.getLogger(LogbackTest1.class);

    public void say(String name) {
        logger.info(name);
        System.out.println("hello " + name);
    }
}
