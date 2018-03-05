package com.github.study.sboot.base;

import com.github.framework.starter.core.FrameBootApplication;
import com.github.study.sboot.base.event.MyApplicationStartedEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@FrameBootApplication(appName = "sboot")
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.addListeners(new MyApplicationStartedEventListener());
        app.run(args);
    }
}
