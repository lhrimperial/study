package com.github.study.netty.rpc.consumer;

import com.github.study.netty.rpc.provider.Provider;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 *
 */
public class Consumer {
    public static void main(String[] args) throws NoSuchMethodException, IOException, ClassNotFoundException {
        //获取服务提供者的接口名，一般RPC框架都是暴露服务提供者的接口定义
        String providerInterface = Provider.class.getName();

        //需要远程执行的方法，其实就是消费者调用生产者的方法
        Method method = Provider.class.getMethod("printMsg", java.lang.String.class);

        //需要传递的参数
        Object[] rpcArgs = {"Hello RPC!"};

        Socket consumer = new Socket("127.0.0.1", 8899);

        //将方法名称和参数 传递给服务生产者
        ObjectOutputStream output = new ObjectOutputStream(consumer.getOutputStream());
        output.writeUTF(providerInterface);
        output.writeUTF(method.getName());
        output.writeObject(method.getParameterTypes());
        output.writeObject(rpcArgs);

        //从生产者读取返回的结果

        ObjectInputStream input = new ObjectInputStream(consumer.getInputStream());
        Object result = input.readObject();

        System.out.println(result.toString());

    }
}
