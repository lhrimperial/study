package com.github.study.sboot.base.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;

import javax.annotation.PostConstruct;

/**
 * @author longhairen
 * @create 2017-12-13 12:33
 * @description
 **/
@Configuration
public class DynamicConfig {
    public static final String DYNAMIC_CONFIG_NAME = "dynamic_config";

    @Autowired
    AbstractEnvironment environment;

    @PostConstruct
    public void init() {
        environment.getPropertySources().addFirst(new DynamicPropertySource(DYNAMIC_CONFIG_NAME));
    }

}
