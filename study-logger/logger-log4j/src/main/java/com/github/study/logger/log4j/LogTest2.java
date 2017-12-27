package com.github.study.logger.log4j;

import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

/**
 * @author longhairen
 * @create 2017-12-27 22:57
 * @description
 **/
public class LogTest2 {

    @Test
    public void testLog2(){
        BasicConfigurator.configure();
        Configure conf=new Configure();
        conf.config();
    }
}
