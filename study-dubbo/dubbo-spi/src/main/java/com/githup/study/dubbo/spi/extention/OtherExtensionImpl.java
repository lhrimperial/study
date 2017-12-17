package com.githup.study.dubbo.spi.extention;

/**
 * @author longhairen
 * @create 2017-12-11 17:33
 * @description
 **/
public class OtherExtensionImpl implements MyInterface {

    @Override
    public String sayHello(String name, String type) {
        return this.getClass().getName() + "  " + name + "  " + type;
    }
}
