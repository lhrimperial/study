package com.githup.study.dubbo.source.action;

import com.githup.study.dubbo.source.dynamic.proxy.jdk.MyInvocationHandler;
import com.githup.study.dubbo.source.dynamic.service.IUserService;
import com.githup.study.dubbo.source.dynamic.service.impl.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/*
 * loader:类加载器
 * interfaces:目标对象实现的接口
 * h:InvocationHandler的实现类
 *
 * Proxy.newProxyInstance(c.getClassLoader(), c.getInterfaces(), new MyInvocationHandler(_obj));
 * 该方法是重新生成了一个对象，为什么要重新生成？你要使用代理呀，注意c.getInterfaces()这句话，这是非常有意思的一句话，
 * 是说查找到该类的所有接口，然后实现接口的所有方法，当然了，方法都是空的，由谁具体负责接管呢？是new MyInvocationHandler(_Obj)这个对象，于是清楚了：
 * 一个类的动态代理类是这样的一个类，由InvocationHandler的实现类实现所有的方法，由其invoke方法接管所有方法的实现
 */
public class JdkProxyAction {

    public static void main(String[] args) {
        IUserService userService = new UserServiceImpl();
        InvocationHandler invocationHandler = new MyInvocationHandler(userService);
        IUserService userServiceProxy = (IUserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(), invocationHandler);
        System.out.println(userServiceProxy.getName(1));
        System.out.println(userServiceProxy.getAge(1));

        ProxyGeneratorUtils.writeProxyClassToHardDisk("study-dubbo/dubbo-source/out/$Proxy11.class");
    }
}
