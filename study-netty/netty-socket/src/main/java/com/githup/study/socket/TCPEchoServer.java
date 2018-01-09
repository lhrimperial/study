package com.githup.study.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author longhairen
 * @create 2018-01-01 13:52
 * @description
 **/
public class TCPEchoServer {

    public static void main(String[] args) throws Exception{
        int port = 7;

        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        int len = -1;
        byte[] receive = new byte[1024];
        while ((len = is.read(receive)) != -1) {
            System.out.println(new String(receive));
        }

        byte[] data = "connet success".getBytes();
        os.write(data);

        socket.close();

    }
}
