package com.github.study.thread.base.basic;

/**
 * @author longhairen
 * @create 2017-09-24 16:04
 * @description
 *
 *
 **/
public class ThreadWaitNotify {
    final static Object object = new Object();

    public static class Thread1 extends Thread{

        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ": T1 start!");
                try {
                    System.out.println(System.currentTimeMillis() + ": T1 wait for object!");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ": T1 end!");
            }
        }
    }

    public static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ": T2 start!");
                object.notify();
                System.out.println(System.currentTimeMillis() + ": T2 end!");

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Thread1 T1 = new Thread1();
        Thread2 T2 = new Thread2();
        T1.start();
//        Thread.sleep(10);
        T2.start();
    }
}
