package com.github.study.netty.base.serialize;

/**
 * @author longhairen
 * @create 2018-01-17 23:05
 * @description
 **/
public class SerializerFactory {

    public static Serializer getSerializer(){
        return new HessianSerializer();
    }
}
