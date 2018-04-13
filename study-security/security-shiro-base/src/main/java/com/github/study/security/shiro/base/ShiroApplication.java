package com.github.study.security.shiro.base;

import com.github.framework.starter.core.ApplicationContexts;
import com.github.framework.starter.core.FrameBootApplication;
import org.springframework.boot.SpringApplication;

/**
 *
 */
@FrameBootApplication(appName = "shiro")
public class ShiroApplication {

    public static void main(String[] args){
        ApplicationContexts.setProfileIfNotExists("dev");
        SpringApplication.run(ShiroApplication.class);
    }
}
