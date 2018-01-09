package com.githup.study.netty.weba.io.bio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author longhr
 * @version 2017/12/4 0004
 */
public class Client {
    private static int PORT = 8379;
    private static String IP = "127.0.0.1";

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 10; i++) {
            service.execute(new Sender());
//        }
        service.shutdown();
    }

    public static class Sender extends Thread {
        @Override
        public void run() {
            BufferedReader bufferedReader = null;
            PrintWriter printWriter = null;
            Socket socket = null;
            try {
                socket = new Socket(IP, PORT);
                printWriter = new PrintWriter(socket.getOutputStream(), true);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                printWriter.println("客户端请求了服务器....");
                String response = bufferedReader.readLine();
                System.out.println("Client：" + response);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(bufferedReader != null){
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(printWriter != null){
                    try {
                        printWriter.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if(socket != null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    socket = null;
                }
            }
        }
    }
}
