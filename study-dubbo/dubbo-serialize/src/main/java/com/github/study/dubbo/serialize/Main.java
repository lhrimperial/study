package com.github.study.dubbo.serialize;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;

/**
 *
 */
public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ProtobufSerialize protoBuffSerialize = new ProtobufSerialize();
        protoBuffSerialize.start();

        System.err.println();
        System.err.println();

        ProtostuffSerialize protoBuffSerialize1 = new ProtostuffSerialize();
        protoBuffSerialize1.start();
        System.err.println();
        System.err.println();

        JavaSerialize javaSerialize = new JavaSerialize();
        javaSerialize.start();
        System.err.println();

        JacksonSerialize jsonSerialize = new JacksonSerialize();
        jsonSerialize.start();
        System.err.println();

        FastJsonSerialize fastJsonSerialize = new FastJsonSerialize();
        fastJsonSerialize.start();
    }
}
