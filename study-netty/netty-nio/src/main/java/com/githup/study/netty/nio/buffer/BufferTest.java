package com.githup.study.netty.nio.buffer;


import java.io.FileInputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

/**
 *
 */
public class BufferTest {
    public static void main(String[] args) throws Exception{
        Buffer buffer = null;
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        FileInputStream fis = new FileInputStream("");
        FileChannel channel = fis.getChannel();
        channel.read(byteBuffer);

        ByteOrder byteOrder = null;
    }
}
