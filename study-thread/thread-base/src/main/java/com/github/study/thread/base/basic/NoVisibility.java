package com.github.study.thread.base.basic;

/**
 * @author longhairen
 * @create 2017/9/25 0025 上午 10:00
 */
public class NoVisibility {
    /**
     * 不加volatile关键字，ReaderThread读不到主线程的修改
     */
    private static volatile boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run(){
            while (!ready) {

            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws Exception{
        new ReaderThread().start();
        Thread.sleep(1000);
        number = 32;
        ready = true;
        Thread.sleep(2000);
    }
}
