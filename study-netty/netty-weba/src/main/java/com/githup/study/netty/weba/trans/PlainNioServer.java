package com.githup.study.netty.weba.trans;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author longhairen
 * @create 2017-12-15 21:25
 * @description
 **/
public class PlainNioServer {

    public void server(int port) throws IOException{
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);

        ServerSocket serverSocket = socketChannel.socket();
        InetSocketAddress inetAddress = new InetSocketAddress(port);
        serverSocket.bind(inetAddress);

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        final ByteBuffer byteBuffer = ByteBuffer.wrap("Hi\r\n".getBytes(Charset.forName("UTF-8")));

        for (;;) {
            selector.select();

            Set<SelectionKey> selectionKeys = selector.keys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();

                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel clientChannel = serverChannel.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ,
                            byteBuffer.duplicate());
                    System.out.println("Accepted connection from " + clientChannel);

                    if (selectionKey.isWritable()) {
                        SocketChannel client =
                                (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                        while (buffer.hasRemaining()) {
                            if (client.write(buffer) == 0) {
                                break;
                            }
                        }
                        client.close();
                    }
                }
            }
        }

    }
}
