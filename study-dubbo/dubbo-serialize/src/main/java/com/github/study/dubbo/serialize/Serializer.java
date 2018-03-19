package com.github.study.dubbo.serialize;

import com.github.study.dubbo.serialize.domain.Person;

/**
 *
 */
public interface Serializer<T> {
    public String name();

    public byte[] serialize(Person obj) throws Exception ;

    public Person deserialize(byte[] data, Class<Person> type) throws Exception ;
}
