package com.githup.study.dubbo.consumer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author longhairen
 * @create 2017-12-11 10:09
 * @description
 **/
@RestController
public class HelloController {


    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

}
