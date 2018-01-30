package com.githup.study.dubbo.source.action;

import com.githup.study.dubbo.source.dynamic.proxy.javassist.JavassistProxyFactory;
import com.githup.study.dubbo.source.dynamic.proxy.javassist.JavassistProxyFactory02;
import com.githup.study.dubbo.source.dynamic.service.impl.UserNointerfaceService;

/**
 *
 */
public class JavassistStaticProxyAction {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        System.out.println("*******************方式一*******************");
        UserNointerfaceService service = new UserNointerfaceService();
        JavassistProxyFactory<UserNointerfaceService> proxyFactory = new JavassistProxyFactory<UserNointerfaceService>();
        proxyFactory.setTarget(service);
        UserNointerfaceService proxy = proxyFactory.getProxy();
        proxy.sayHello();


        System.out.println("*******************方式二*******************");
        JavassistProxyFactory02 jpf02 = new JavassistProxyFactory02();
        UserNointerfaceService a2 = (UserNointerfaceService) jpf02.getProxy(UserNointerfaceService.class);
        a2.sayHello();
    }
}
