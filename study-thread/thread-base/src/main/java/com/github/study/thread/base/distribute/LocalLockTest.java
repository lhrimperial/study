package com.github.study.thread.base.distribute;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author longhairen
 * @create 2017/9/6 0006 下午 4:12
 */
public class LocalLockTest {
    public static int commSource = 0;
    public static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is running");
                increase();
            }
        };
        for (int i = 0; i < 5; i++) {
            new Thread(runnable).start();
        }
    }

    public static void increase(){
        System.out.println(Thread.currentThread().getName() + " getting lock");
        lock.lock();
        try {
            System.out.println(++commSource);
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            lock.unlock();
        }
    }
}
