package com.githup.study.netty.nio.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 *
 */
public class BufferFillDrain {

    private static int index = 0;
    private static String[] strings = {
            "A random string value",
            "The product of an infinite number of monkeys",
            "Hey hey we're the Monkees",
            "Opening act for the Monkees: Jimi Hendrix",
            "'Scuse me while I kiss this fly", // Sorry Jimi ;-)
            "Help Me! Help Me!",
    };

    public static void main(String[] argv) throws Exception {
//        test1();
        test2();
    }

    public static void test2() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 'h').put((byte) 'e').put((byte) 'l').put((byte) 'l').put((byte) 'o');
        System.out.println("flip before");
        System.out.println("position:"+buffer.position());
        System.out.println("limit:"+buffer.limit());
        buffer.flip();
        System.out.println("flip after");
        System.out.println("position:"+buffer.position());
        System.out.println("limit:"+buffer.limit());
        System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());
        System.out.println("get after");
        System.out.println("position:"+buffer.position());
        System.out.println("limit:"+buffer.limit());
        buffer.compact();
        System.out.println("compact after");
        System.out.println("position:"+buffer.position());
        System.out.println("limit:"+buffer.limit());
        System.out.println(buffer);
    }

    public static void test1() {
        CharBuffer buffer = CharBuffer.allocate(100);
        while (fillBuffer(buffer)) {
            buffer.flip();
            drainBuffer(buffer);
            buffer.clear();
        }
    }

    private static void drainBuffer(CharBuffer buffer) {
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
        System.out.println("");
    }

    private static boolean fillBuffer(CharBuffer buffer) {
        if (index >= strings.length) {
            return (false);
        }
        String string = strings[index++];
        for (int i = 0; i < string.length(); i++) {
            buffer.put(string.charAt(i));
        }

        return (true);
    }


}
