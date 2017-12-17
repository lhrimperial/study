package com.githup.study.netty.nio.channel;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @author longhairen
 * @create 2017-12-10 11:37
 * @description
 **/
public class ConnectAsync {
    public static void main(String[] argv) throws Exception {
        String host = "localhost";
        int port = 80;
        if (argv.length == 2) {
            host = argv[0];
            port = Integer.parseInt(argv[1]);
        }
        InetSocketAddress addr = new InetSocketAddress(host, port);
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        System.out.println("initiating connection");
        sc.connect(addr);
        while (!sc.finishConnect()) {
            doSomethingUseful();
        }
        System.out.println("connection established");
// Do something with the connected socket
// The SocketChannel is still nonblocking
        sc.close();
    }

    private static void doSomethingUseful() {
        System.out.println("doing something useless");
    }
}
