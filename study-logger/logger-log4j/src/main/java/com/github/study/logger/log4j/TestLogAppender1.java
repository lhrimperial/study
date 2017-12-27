package com.github.study.logger.log4j;

import org.apache.log4j.*;
import org.junit.Test;

/**
 * @author longhairen
 * @create 2017-12-27 23:16
 * @description
 **/
public class TestLogAppender1 {
    @Test
    public void testLogAppender1(){
        Logger log1=Logger.getLogger("cd");
        log1.setLevel(Level.DEBUG);
        log1.addAppender(new ConsoleAppender(new SimpleLayout()));
        Logger log2=Logger.getLogger("cd.itcast.log");
        log2.info("log2 info");
        log2.debug("log2 debug");
    }

    @Test
    public void testLogAppender2(){
        BasicConfigurator.configure();
        Logger log1=Logger.getLogger("cd");
        log1.setLevel(Level.DEBUG);
        log1.addAppender(new ConsoleAppender(new SimpleLayout()));
        Logger log2=Logger.getLogger("cd.itcast.log");
        log2.info("log2 info");
        log2.debug("log2 debug");
    }

    @Test
    public void testPatternLayout(){
        Logger log=Logger.getLogger("cd");
        String pattern="%r [%t] %-5p %c - %m%n";
        log.addAppender(new ConsoleAppender(new PatternLayout(pattern)));
        Logger log2=Logger.getLogger("cd.itcast.log");
        log2.info("log2 info");
    }
}
