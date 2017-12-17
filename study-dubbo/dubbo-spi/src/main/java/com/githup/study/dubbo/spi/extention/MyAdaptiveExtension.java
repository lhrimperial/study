package com.githup.study.dubbo.spi.extention;

import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.ExtensionLoader;

/**
 * @author longhairen
 * @create 2017-12-11 17:33
 * @description
 **/

public class MyAdaptiveExtension implements MyInterface {
    @SuppressWarnings("rawtypes")
    @Override
    public String sayHello(String name, String type) {
        ExtensionLoader extensionLoader = ExtensionLoader
                .getExtensionLoader(MyInterface.class);
        MyInterface extension = (MyInterface) extensionLoader
                .getDefaultExtension();
        if (ExtensionType.defaults.equals(type)) {
            extension = (MyInterface) extensionLoader
                    .getExtension("defaults");
        }
        if (ExtensionType.other.equals(type)) {
            extension = (MyInterface) extensionLoader
                    .getExtension("other");
        }
        return extension.sayHello(name, type);
    }
}
