package com.githup.study.dubbo.spi.extention;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * @author longhairen
 * @create 2017-12-11 17:32
 * @description
 **/
@SPI("defaults")
public interface MyInterface {

    String sayHello(String name, String type);
}

