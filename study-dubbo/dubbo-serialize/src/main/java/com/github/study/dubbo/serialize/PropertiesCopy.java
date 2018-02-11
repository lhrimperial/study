package com.github.study.dubbo.serialize;

import com.github.study.dubbo.serialize.domain.Hello;
import com.github.study.dubbo.serialize.domain.User;
import com.github.study.dubbo.serialize.serializeutil.JavaSerializer;
import com.github.study.dubbo.serialize.serializeutil.ProtostuffUtil;
import com.github.study.dubbo.serialize.serializeutil.Serializer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PropertiesCopy {

    public static void main(String[] args){
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

        System.out.println(u);
        Long startTime = System.currentTimeMillis();
        byte[] toArray = ProtostuffUtil.serializer(u);
        Hello hello = ProtostuffUtil.deserializer(toArray, Hello.class);
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(hello);

        System.out.println("*********************************");

        startTime = System.currentTimeMillis();
        Serializer serializer = new JavaSerializer();
        toArray = serializer.serializer(u);
        User uu = serializer.deserializer(toArray, User.class);
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(uu);
    }
}
