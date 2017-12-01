package com.githup.study.dubbo.source.test;

import com.githup.study.dubbo.source.proxy.JavassistProxyFactory;
import com.githup.study.dubbo.source.service.impl.UserNointerfaceService;

/**
 * @author longhr
 * @version 2017/12/1 0001
 */
public class JavassistTest {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        System.out.println("*******************方式一*******************");
        UserNointerfaceService service = new UserNointerfaceService();
        JavassistProxyFactory<UserNointerfaceService> proxyFactory = new JavassistProxyFactory<UserNointerfaceService>();
        proxyFactory.setTarget(service);
        UserNointerfaceService proxy = proxyFactory.getProxy();
        proxy.sayHello();
        Class clazz = null;
        ClassLoader classLoader = null;
    }
}
