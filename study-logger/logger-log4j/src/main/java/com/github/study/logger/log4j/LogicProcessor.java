package com.github.study.logger.log4j;

import org.apache.log4j.Logger;

/**
 * @author longhairen
 * @create 2017-12-27 23:03
 * @description
 **/
public class LogicProcessor {
    private static Logger log=Logger.getLogger(LogicProcessor.class);

    public void init(Configure conf){
        log.info("init logic processor using conf");
    }

    public void process(){
        log.info("process some logic");
        log.debug("process some detail logic");
    }
}
