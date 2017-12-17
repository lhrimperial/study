package com.githup.study.dubbo.spi.extention;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

/**
 * @author longhairen
 * @create 2017-12-11 17:33
 * @description
 **/
public class ExtensionTest {
    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        ExtensionLoader extensionLoader = ExtensionLoader
                .getExtensionLoader(MyInterface.class);
        MyInterface myFirstExtension = (MyInterface) extensionLoader
                .getAdaptiveExtension();
        System.out.println(myFirstExtension.sayHello("xxx",
                ExtensionType.defaults));
    }
}
