package com.githup.study.dubbo.source.test;

import com.githup.study.dubbo.source.proxy.CglibProxy;
import com.githup.study.dubbo.source.service.IUserService;
import com.githup.study.dubbo.source.service.impl.UserServiceImpl;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author longhr
 * @version 2017/11/30 0030
 */
public class CglibProxyTest {

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(cglibProxy);

        IUserService o = (IUserService)enhancer.create();
        o.getName(1);
        o.getAge(1);
    }
}
