package com.githup.study.dubbo.provider.service.impl;

import com.github.study.service.IHelloService;

/**
 * @author longhairen
 * @create 2017/8/11 0011 上午 9:02
 */
public class HelloService implements IHelloService {
    @Override
    public void sayHello(String name) {
        System.out.println("hello " + name);
    }
}
