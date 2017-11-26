package com.github.study.thread.base.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author longhairen
 * @create 2017/9/26 0026 上午 10:33
 */
public class UnFairLock implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " 获得了锁");
                Thread.sleep(500);
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        UnFairLock lock = new UnFairLock();
        Thread t1 = new Thread(lock);
        Thread t2 = new Thread(lock);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
