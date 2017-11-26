package com.github.study.thread.base.basic;

/**
 * @author longhairen
 * @create 2017-09-24 16:58
 * @description
 **/
public class GoodSuspend {

    public static Object object = new Object();

    public static class ChangeObjectThread extends Thread {
        volatile boolean suspendme = false;

        public void suspendMe(){
            suspendme = true;
        }

        public void resumeMe(){
            suspendme = false;
            synchronized (this) {
                notify();
            }
        }
        @Override
        public void run(){
            while (true) {
                synchronized (this) {
                    while (suspendme) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                synchronized (object) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("in ChangeObjectThread!");
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread {
        @Override
        public void run(){
            while (true) {
                synchronized (object) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("in ReadObjectThread!****************");
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        ChangeObjectThread t1 = new ChangeObjectThread();
        ReadObjectThread t2 = new ReadObjectThread();

        t1.start();
        t2.start();
        Thread.sleep(100);
        t1.suspendMe();
        System.out.println("suspend t1");
        Thread.sleep(5000);
        System.out.println("resume t1");
        t1.resumeMe();

    }
}
