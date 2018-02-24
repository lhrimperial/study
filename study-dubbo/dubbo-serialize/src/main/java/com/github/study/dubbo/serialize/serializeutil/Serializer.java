package com.github.study.dubbo.serialize.serializeutil;

/**
 *
 */
public interface Serializer {

    /**
     * 序列化
     * @param obj
     * @param <T>
     * @return
     */
    <T> byte[] serializer(T obj);

    /**
     * 反序列化
     *
     * @param data
     * @param clazz
     * @return
     */
    <T> T deserializer(byte[] data, Class<T> clazz);
}
