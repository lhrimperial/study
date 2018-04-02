package com.github.study.dubbo.serialize;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import sun.java2d.opengl.WGLSurfaceData;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

    }
    public static void testSerialize() throws IOException, ClassNotFoundException {
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
