package com.github.study.netty.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 *
 */
public class NIOClient {
    private static final String host = "127.0.0.1";
    private static final int port = 8080;

    public static void main(String[] args){
        new NIOClient().connect(host, port);
    }

    private void connect(String host, int port) {
        SocketChannel sc = null;

        try {
            sc = SocketChannel.open();
            sc.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
