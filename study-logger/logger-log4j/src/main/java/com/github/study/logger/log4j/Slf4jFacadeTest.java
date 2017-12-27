package com.github.study.logger.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author longhairen
 * @create 2017-12-27 23:32
 * @description
 **/
public class Slf4jFacadeTest {
    private static Logger logger = LoggerFactory.getLogger(Slf4jFacadeTest.class);

    public static void main(String[] args){
        if(logger.isDebugEnabled()){
            logger.debug("slf4j-log4j debug message");
        }
        if(logger.isInfoEnabled()){
            logger.debug("slf4j-log4j info message");
        }
        if(logger.isTraceEnabled()){
            logger.debug("slf4j-log4j trace message");
        }
    }
}
