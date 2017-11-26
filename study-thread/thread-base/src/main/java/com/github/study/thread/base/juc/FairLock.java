package com.github.study.thread.base.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author longhairen
 * @create 2017/9/26 0026 上午 10:28
 */
public class FairLock implements Runnable {

    public static ReentrantLock fairLock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                fairLock.lock();
                System.out.println(Thread.currentThread().getName() + " 获得了锁");
                Thread.sleep(1000);
            } catch (Exception e) {

            } finally {
                fairLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        FairLock fairLock = new FairLock();
        Thread t1 = new Thread(fairLock);
        Thread t2 = new Thread(fairLock);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }


}
