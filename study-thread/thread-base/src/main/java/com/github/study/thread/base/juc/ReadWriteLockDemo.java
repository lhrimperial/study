package com.github.study.thread.base.juc;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author longhairen
 * @create 2017/9/27 0027 上午 10:09
 */
public class ReadWriteLockDemo {
    public static ReentrantLock lock = new ReentrantLock();
    public static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public static Lock readLock = readWriteLock.readLock();
    public static Lock writeLock = readWriteLock.writeLock();

    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handWrite(Lock lock, int index) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception{
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();

        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handleRead(readLock);
//                    demo.handleRead(lock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handWrite(writeLock, new Random().nextInt());
//                    demo.handWrite(lock, new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        long readStart = System.currentTimeMillis();
        Thread thread = null;
        for (int i = 0; i < 18; i++) {
            thread = new Thread(readRunnable);
            thread.start();
//            thread.join();
        }
        System.out.println("read time :" + (System.currentTimeMillis() - readStart));

        long writeStart = System.currentTimeMillis();
        for (int i = 0; i < 2; i++) {
            thread = new Thread(writeRunnable);
            thread.start();
//            thread.join();
        }
        System.out.println("write time :" + (System.currentTimeMillis() - writeStart));
    }
}
