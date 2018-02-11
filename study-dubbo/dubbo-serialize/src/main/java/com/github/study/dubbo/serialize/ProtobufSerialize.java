package com.github.study.dubbo.serialize;


import com.github.study.dubbo.serialize.domain.Person;
import com.github.study.dubbo.serialize.protobuf.UserFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ProtobufSerialize {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        new ProtobufSerialize().start();
    }

    public void start() throws IOException, ClassNotFoundException {
        UserFactory.User.Builder builder = UserFactory.User.newBuilder();

        UserFactory.User.Builder f1 = UserFactory.User.newBuilder();
        f1.setUserName("李四");
        f1.setPassWord("123456");
        f1.setUserInfo("李四是一个很牛逼的人");

        UserFactory.User.Builder f2 = UserFactory.User.newBuilder();
        f2.setUserName("王五");
        f2.setPassWord("123456");
        f2.setUserInfo("王五是一个很牛逼的人");

        builder.setUserName("张三");
        builder.setPassWord("123456");
        builder.setUserInfo("张三是一个很牛逼的人");
        builder.addFriends(f1);
        builder.addFriends(f2);

        UserFactory.User user =  builder.build();

        Long t1 = System.currentTimeMillis();
        byte[] byteArray = null;
        for(int i = 0; i<10; i++) {
            byteArray = user.toByteArray();
        }
        System.out.println("protobuf serialize: " +(System.currentTimeMillis() - t1) + "ms; 总大小：" + byteArray.length );

        Long t2 = System.currentTimeMillis();
        UserFactory.User readUser = UserFactory.User.parseFrom(byteArray);
        System.out.println("protobuf deserialize: " + (System.currentTimeMillis() - t2) + "ms; User: " + readUser);
    }

}
