package com.githup.study.dubbo.spi.jdk;


import com.githup.study.dubbo.spi.jdk.service.HelloService;

import java.util.ServiceLoader;

/**
 * @author longhairen
 * @create 2017-12-11 8:46
 * @description
 **/
public class JdkSpiTest {
    public static void main(String[] args) throws Exception{
        ServiceLoader<HelloService> helloServiceLoader=ServiceLoader.load(HelloService.class);
        for(HelloService item : helloServiceLoader){
            String temp = item.sayHello("andy");
            System.out.println(temp);
        }
    }
}
