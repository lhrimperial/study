package com.github.study.netty.net.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 */
public class BIOClient extends AbstractNetwork {

    private static final String host = "127.0.0.1";
    private static final int port = 8080;

    public void connect(String host, int port) {
        BufferedReader reader = null;
        PrintWriter writer = null;
        Socket socket = null;
        try {
            socket = new Socket(host, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("客户端请求服务器。。。。");

            while (true) {
                writer.println("Hello Server, I am " + socket.getInetAddress());
                String response = reader.readLine();
                System.out.println("server return message : " + response);

                Thread.sleep(3000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(reader, writer, socket);
        }
    }

    public static void main(String[] args){
        new BIOClient().connect(host, port);
    }

}
