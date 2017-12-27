package com.github.study.logger.log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * @author longhairen
 * @create 2017-12-27 22:51
 * @description
 **/
public class Log4jTets {
    private static Logger logger = Logger.getLogger(Log4jTets.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        logger.info("hello world!");
    }
}
