package com.githup.study.web.simple;

import com.alibaba.fastjson.JSON;
import com.githup.study.web.simple.domain.UserInfo;
import com.githup.study.web.simple.service.IUserInfoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 */
public class SpringSourceRead {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        IUserInfoService userInfoService = (IUserInfoService) context.getBean("userInfoService");
        UserInfo userInfo = userInfoService.findUserInfo(1);
        System.out.println(JSON.toJSONString(userInfo));
    }
}
