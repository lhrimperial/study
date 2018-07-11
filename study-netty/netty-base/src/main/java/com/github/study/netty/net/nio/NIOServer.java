package com.github.study.netty.net.nio;

import com.sun.corba.se.spi.activation.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class NIOServer {
    private int port;
    private Selector selector;
    private ExecutorService service = Executors.newFixedThreadPool(3);

    public static void main(String[] args){
        new NIOServer(8080).start();
    }

    public NIOServer(int port) {
        this.port = port;
    }

    public void start() {
        init();
        while (true) {
            try {
                selector.select();
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    if (key.isValid()) {
                        if (key.isAcceptable()) {
                            access(key);
                        } else {
                            service.submit(new NioServerHandler(key));
                        }
                    }
                    keys.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void access(SelectionKey key) {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        System.out.println("access " + Thread.currentThread().getName() + "......");

        try {
            SocketChannel sc = ssc.accept();
            sc.configureBlocking(false);
            sc.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE | SelectionKey.OP_CONNECT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ssc.bind(new InetSocketAddress(port));
            selector = Selector.open();

            ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("server started success......");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class NioServerHandler implements Runnable {
        private SelectionKey selectionKey;
        private String threadName = Thread.currentThread().getName();

        public NioServerHandler(SelectionKey key) {
            this.selectionKey = key;
        }

        @Override
        public void run() {
            if (selectionKey.isAcceptable()) {
                connect();
            }
            if (selectionKey.isReadable()) {
                read();
            }
            if (selectionKey.isWritable()) {
                write();
            }
        }
        private void read() {
            System.out.println(threadName + " read something ....");
        }

        private void write() {
            System.out.println(threadName + " write something ....");
        }

        private void connect() {
            System.out.println(threadName + " connect ....");
        }
    }

}
