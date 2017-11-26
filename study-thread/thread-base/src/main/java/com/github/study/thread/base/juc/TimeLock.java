package com.github.study.thread.base.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author longhairen
 * @create 2017/9/25 0025 下午 9:53
 */
public class TimeLock implements Runnable{

    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(6000);
            } else {
                System.out.println(Thread.currentThread().getName()+" get lock failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args){
        TimeLock lock = new TimeLock();
        Thread t1 = new Thread(lock);
        Thread t2 = new Thread(lock);
        t1.start();
        t2.start();

    }
}
