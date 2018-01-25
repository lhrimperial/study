package com.githup.study.dubbo.provider.service.impl;

import com.githup.study.dubbo.provider.service.ITestService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class TestService implements ITestService {

    @Override
    public String sayHello() {
        return "hello \n";
    }
}
