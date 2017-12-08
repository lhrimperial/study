package com.githup.study.web.simple.controller;

import com.githup.study.web.simple.domain.UserInfo;
import com.githup.study.web.simple.service.IUserInfoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author longhairen
 * @create 2017/8/29 0029 下午 12:40
 */
public class BeanTest {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        IUserInfoService userInfoService = (IUserInfoService) context.getBean("userInfoService");
        UserInfo userInfo = userInfoService.findUserInfo(1);
    }
}
