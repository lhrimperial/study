package com.github.study.netty.base;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author longhairen
 * @create 2018-01-06 23:06
 * @description
 **/
public class NIOServer {

    public static void startServer(int port) throws Exception{
        //打开服务端ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //绑定一个端口
        serverSocketChannel.bind(new InetSocketAddress(port));

        //打开select
        Selector selector = Selector.open();
        //注册连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            // select()阻塞直到注册的某个事件就绪并会更新SelectionKey的状态
            int readyChannel = selector.select();
            if (readyChannel < 0) {
                continue;
            }
            //得到就绪的key集合，key中保存有就绪的事件以及对应的Channel通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    //处理accept事件
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    // 注意此处新增关心事件OP_READ
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    // 处理read事件
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    byteBuffer.clear();
                    socketChannel.read(byteBuffer);
                    byteBuffer.flip();
                    socketChannel.write(byteBuffer);
                }
            }

        }
    }
}
