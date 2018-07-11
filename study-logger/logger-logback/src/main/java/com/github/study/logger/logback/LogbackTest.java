package com.github.study.logger.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author longhairen
 * @create 2017-12-27 23:54
 * @description
 **/
public class LogbackTest {
    private static Logger logger = LoggerFactory.getLogger(LogbackTest.class);
    public static void main(String[] args) {
        LogbackTest1 test = new LogbackTest1();
        test.say("andy");
        logger.info("hello world!");
    }

}
