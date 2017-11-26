package com.github.study.thread.base.basic;

/**
 * @author longhr
 * @version 2017/11/24 0024
 */
public class DaemonDemo {

    public static class DaemonT extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("i am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Thread thread = new DaemonT();
        thread.setDaemon(true);
        thread.start();

        Thread.sleep(5000);
    }
}
