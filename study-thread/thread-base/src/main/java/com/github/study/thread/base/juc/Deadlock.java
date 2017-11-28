package com.github.study.thread.base.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author longhr
 * @version 2017/11/28 0028
 */
public class Deadlock implements Runnable {

    int model;
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    public Deadlock(int model) {
        this.model = model;
    }
    @Override
    public void run(){
        while (true) {
            try {
                if (model == 1) {
                    lock1.lock();
                    System.out.println(Thread.currentThread().getName() + " get lock1 in model 1");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock2.lock();
                    System.out.println(Thread.currentThread().getName() + " get lock2 in model 1");
                } else {
                    lock2.lock();
                    System.out.println(Thread.currentThread().getName() + " get lock2 in model 2");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock1.lock();
                    System.out.println(Thread.currentThread().getName() + " get lock1 in model 2");
                }
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(new Deadlock(1));
        Thread t2 = new Thread(new Deadlock(2));
        t1.start();t2.start();
        t1.join();t2.join();
    }
}
