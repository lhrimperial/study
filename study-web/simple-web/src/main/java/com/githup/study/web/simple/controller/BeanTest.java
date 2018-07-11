package com.githup.study.web.simple.controller;

import com.githup.study.web.simple.domain.UserInfo;
import com.githup.study.web.simple.service.IUserInfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ConfigurableWebEnvironment;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

/**
 * @author longhairen
 * @create 2017/8/29 0029 下午 12:40
 */
public class BeanTest {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        IUserInfoService userInfoService = (IUserInfoService) context.getBean("userInfoService");
        UserInfo userInfo = userInfoService.findUserInfo(1);


        ApplicationContext fileContext = new FileSystemXmlApplicationContext("C:\\Users\\Administrator\\Desktop\\spring.xml");
    }




}
