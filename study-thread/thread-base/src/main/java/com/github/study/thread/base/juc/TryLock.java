package com.github.study.thread.base.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author longhairen
 * @create 2017/9/26 0026 上午 9:06
 */
public class TryLock implements Runnable {

    public static ReentrantLock lock1 = new ReentrantLock(true);
    public static ReentrantLock lock2 = new ReentrantLock(true);
    int lock;

    public TryLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        if (lock == 1) {
            while (true) {
                if (lock1.tryLock()) {
                    System.out.println(Thread.currentThread().getName() + " get lock1");
                    try {
                        Thread.sleep(1000);
                        boolean innerLock = lock2.tryLock();
                        System.out.println(Thread.currentThread().getName() + " get lock2 " + innerLock);
                        if (innerLock) {
                            try {
                                System.out.println(Thread.currentThread().getName() + " My Job done!");
                                break;
                            } finally {
                                lock2.unlock();
                            }
                        }
                    } catch (Exception e) {

                    } finally {
                        System.out.println(Thread.currentThread().getName() + " unlock lock1");
                        lock1.unlock();
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            while (true) {
                if (lock2.tryLock()) {
                    System.out.println(Thread.currentThread().getName() + " get lock2");
                    try {
                        Thread.sleep(1000);
                        boolean innerLock = lock1.tryLock();
                        System.out.println(Thread.currentThread().getName() + " get lock1 " + innerLock);
                        if (innerLock) {
                            try {
                                System.out.println(Thread.currentThread().getName() + " My Job done!");
                                break;
                            } finally {
                                lock1.unlock();
                            }
                        }
                    } catch (Exception e) {

                    } finally {
                        System.out.println(Thread.currentThread().getName() + " unlock lock2");
                        lock2.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        TryLock lock1 = new TryLock(1);
        TryLock lock2 = new TryLock(2);
        Thread t1 = new Thread(lock1);
        Thread t2 = new Thread(lock2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
