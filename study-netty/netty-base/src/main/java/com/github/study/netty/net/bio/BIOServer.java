package com.github.study.netty.net.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class BIOServer {
    private int port;
    private ExecutorService service = Executors.newFixedThreadPool(3);

    public BIOServer(int port) {
        this.port = port;
    }

    public void start() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("server started....");
            while (true) {
                socket = serverSocket.accept();
                service.submit(new ServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ServerHandler extends AbstractNetwork implements Runnable {

        private Socket socket;

        public ServerHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader reader = null;
            PrintWriter writer = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream(), true);

                System.out.println(socket.toString());

                String threadName = Thread.currentThread().getName();
                String requestName = socket.getInetAddress().getHostName();

                String readInfo = null;
                while (true) {
                    readInfo = reader.readLine();
                    if (readInfo == null) {
                        break;
                    }
                    System.out.println("接收到客户端消息：" + readInfo + ",处理线程：" + threadName);

                    writer.println(requestName + " 你的请求已处理，处理线程：" + threadName);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                close(reader, writer, socket);
            }
        }
    }

    public static void main(String[] args){
        new BIOServer(8080).start();
    }

}
