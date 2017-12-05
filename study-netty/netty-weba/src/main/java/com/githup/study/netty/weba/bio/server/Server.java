package com.githup.study.netty.weba.bio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author longhr
 * @version 2017/12/4 0004
 */
public class Server {
    private static int PORT = 8379;
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("服务器端启动了....");
            //进行阻塞
            Socket socket = serverSocket.accept();
            //启动一个线程来处理客户端请求
            //new Thread(new ServerHandler(socket)).start();

            //改用线程池
            HandlerExecutorPool pool = new HandlerExecutorPool(50, 1000);
            while (true) {
                socket = serverSocket.accept();
                pool.execute(new ServerHandler(socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            serverSocket = null;
        }
    }
}
