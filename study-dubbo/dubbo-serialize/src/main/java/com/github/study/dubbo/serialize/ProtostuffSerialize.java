package com.github.study.dubbo.serialize;

import com.github.study.dubbo.serialize.domain.User;
import com.github.study.dubbo.serialize.serializeutil.ProtostuffUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ProtostuffSerialize {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        new JavaSerialize().start();
    }

    public void start() throws IOException, ClassNotFoundException {
        User u = new User();
        List<User> friends = new ArrayList<>();
        u.setUserName("张三");
        u.setPassWord("123456");
        u.setUserInfo("张三是一个很牛逼的人");
        u.setFriends(friends);

        User f1 = new User();
        f1.setUserName("李四");
        f1.setPassWord("123456");
        f1.setUserInfo("李四是一个很牛逼的人");

        User f2 = new User();
        f2.setUserName("王五");
        f2.setPassWord("123456");
        f2.setUserInfo("王五是一个很牛逼的人");

        friends.add(f1);
        friends.add(f2);

        Long t1 = System.currentTimeMillis();
        byte[] toArray = null;
        for(int i = 0; i<10; i++) {
            toArray = ProtostuffUtil.serializer(u);
        }
        System.out.println("protostuff serialize: " +(System.currentTimeMillis() - t1) + "ms; 总大小：" + toArray.length );

        Long t2 = System.currentTimeMillis();
        User readUser = ProtostuffUtil.deserializer(toArray, User.class);
        System.out.println("protostuff deserialize: " + (System.currentTimeMillis() - t2) + "ms; User: " + readUser);
    }
}
