package com.github.study.sboot.base.controller;

import com.github.study.sboot.base.configure.PersonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class HelloController {

    @Autowired
    private PersonConfig person;

    @RequestMapping("/person")
    public PersonConfig getPerson(){
        return person;
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }


}
