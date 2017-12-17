package com.github.study.sboot.base.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author longhairen
 * @create 2017-12-12 21:17
 * @description
 * spring boot启动开始时执行的事件
 **/
public class MyApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    private Logger logger = LoggerFactory.getLogger(MyApplicationStartedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        SpringApplication app = event.getSpringApplication();
        logger.info("==MyApplicationStartedEventListener==");
    }
}
