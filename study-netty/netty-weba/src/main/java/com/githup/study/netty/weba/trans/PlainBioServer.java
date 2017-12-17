package com.githup.study.netty.weba.trans;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author longhairen
 * @create 2017-12-15 21:17
 * @description
 **/
public class PlainBioServer {

    public static void main(String[] args) throws IOException{
        PlainBioServer plainBioServer = new PlainBioServer();
        plainBioServer.server(8080);
    }
    public void server(int port) throws IOException{
        final ServerSocket serverSocket = new ServerSocket(port);
        try {
            for (;;) {
                final Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted client connection from " + clientSocket);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OutputStream out = null;
                        try {
                            out = clientSocket.getOutputStream();
                            out.write("Hi\r\n".getBytes(Charset.forName("UTF-8")));
                            out.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (clientSocket != null) {
                                try {
                                    clientSocket.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }).start();
            }
        } catch (IOException e) {

        } finally {

        }
    }
}
