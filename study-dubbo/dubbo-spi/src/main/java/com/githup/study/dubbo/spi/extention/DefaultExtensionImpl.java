package com.githup.study.dubbo.spi.extention;

/**
 * @author longhairen
 * @create 2017-12-11 17:32
 * @description
 **/
public class DefaultExtensionImpl implements MyInterface {

    @Override
    public String sayHello(String name, String type) {
        return this.getClass().getName() + "  " + name + "  " + type;
    }
}
