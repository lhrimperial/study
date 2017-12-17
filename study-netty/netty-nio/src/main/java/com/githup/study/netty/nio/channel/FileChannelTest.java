package com.githup.study.netty.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;

/**
 * @author longhairen
 * @create 2017-12-09 8:18
 * @description
 **/
public class FileChannelTest {
    public static void main(String[] args) throws Exception{
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\lhr\\Desktop\\test\\javaThread.pdf"));
        FileChannel fileChannel = fis.getChannel();

    }
}
