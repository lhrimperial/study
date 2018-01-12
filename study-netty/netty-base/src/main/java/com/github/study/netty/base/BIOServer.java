package com.github.study.netty.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author longhr
 * @version 2017/12/4 0004
 */
public class BIOServer {
    private static int PORT = 8379;
    static ExecutorService service = Executors.newCachedThreadPool();
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("服务器端启动了....");
            //进行阻塞
            //Socket socket = serverSocket.accept();
            //启动一个线程来处理客户端请求
            //new Thread(new ServerHandler(socket)).start();
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                service.submit(new ServerHandler(socket));
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

    static class ServerHandler implements Runnable {

        private Socket socket;

        public ServerHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader bufferedReader = null;
            PrintWriter printWriter = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                printWriter = new PrintWriter(socket.getOutputStream(), true);
                while (true) {
                    String info = bufferedReader.readLine();
                    if (info == null)
                        break;
                    System.out.println("客户端发送的消息：" + info + "线程" + Thread.currentThread().getName() + "处理了消息");
                    printWriter.println(Thread.currentThread().getName() + "线程 服务器端响应了客户端请求....");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (printWriter != null) {
                    try {
                        printWriter.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                socket = null;
            }
        }
    }
}
