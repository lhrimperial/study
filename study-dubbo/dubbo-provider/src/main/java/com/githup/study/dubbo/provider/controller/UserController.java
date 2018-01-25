package com.githup.study.dubbo.provider.controller;

import com.github.study.domain.User;
import com.githup.study.dubbo.provider.service.ITestService;
import com.githup.study.dubbo.provider.service.IUserInfoServiceRef;
import com.handy.demo.dubbo.schema.domain.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author longhairen
 * @create 2017/8/11 0011 上午 9:16
 */

@Controller
@RequestMapping("/usre")
public class UserController {

    @Autowired
    public IUserInfoServiceRef userInfoServiceRef;

    @Autowired
    private ITestService testService;

    private People people;

    @ResponseBody
    @RequestMapping("/say")
    public String sayHello(){
        return testService.sayHello();
    }

    @ResponseBody
    @RequestMapping("/find")
    public User findUser(){
        return userInfoServiceRef.findUser();
    }

    @ResponseBody
    @RequestMapping("/p")
    public People findPeople(){
        return people;
    }

    public People getPeople() {
        return people;
    }

    @Resource
    public void setPeople(People people) {
        this.people = people;
    }
}
