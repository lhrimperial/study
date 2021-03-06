package com.githup.study.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author longhairen
 * @create 2017-12-10 9:12
 * @description
 **/
public class TCPEchoClient {

    public static void main(String[] args) throws IOException {
        String serverHost = "127.0.0.1";
        int serverPort = 7;

        Socket socket = new Socket(serverHost, serverPort);

        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        byte[] data = "hello".getBytes();
        os.write(data);

        int len = -1;
        byte[] receive = new byte[1024];
        while ((len = is.read(receive)) != -1) {
            System.out.println(new String(receive));
        }

        socket.close();
     }
}
