package com.githup.study.dubbo.spi.jdk.service.impl;


import com.githup.study.dubbo.spi.jdk.service.HelloService;

/**
 * @author longhairen
 * @create 2017-12-11 8:45
 * @description
 **/
public class DefaultHelloService implements HelloService {
    public String sayHello(String name) {
        return "hello " + name + "DefaultHelloService ";
    }
}
