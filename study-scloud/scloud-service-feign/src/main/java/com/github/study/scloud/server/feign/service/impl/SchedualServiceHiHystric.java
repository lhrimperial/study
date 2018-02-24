package com.github.study.scloud.server.feign.service.impl;

import com.github.study.scloud.server.feign.service.ISchedualServiceHi;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class SchedualServiceHiHystric implements ISchedualServiceHi {

    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}
