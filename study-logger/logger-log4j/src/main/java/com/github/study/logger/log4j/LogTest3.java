package com.github.study.logger.log4j;

import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

/**
 * @author longhairen
 * @create 2017-12-27 23:04
 * @description
 **/
public class LogTest3 {
    @Test
    public void testLog(){
        BasicConfigurator.configure();

        Configure conf=new Configure();
        conf.config();

        LogicProcessor processor=new LogicProcessor();
        processor.init(conf);
        processor.process();
    }
}
