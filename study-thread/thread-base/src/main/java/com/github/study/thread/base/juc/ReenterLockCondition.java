package com.github.study.thread.base.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author longhairen
 * @create 2017/9/25 0025 下午 9:39
 * 重入锁和Condition在JDK中被广泛的使用，比如ArrayBlockingQueue中使用ReentrantLock对put方法做同步，
 * 同时生成两个绑定的Condition对象，notEmpty和notFull，当队列已满时使用notFull.await()等待队列有足够的空间，
 * 当队列有数据放入的时候通知take（）的线程使用notEmpty.signal()；
 * 如果队列为空则消费队列要等待一个非空的信号notEmpty.await()；
 * 当取走数据时通知put（）线程队列已有空闲空间notFull.signal()
 */
public class ReenterLockCondition implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " get lock!");
            condition.await();
            System.out.println(Thread.currentThread().getName() + " is going on!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " free lock!");
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception{
        ReenterLockCondition lockCondition = new ReenterLockCondition();
        Thread t1 = new Thread(lockCondition);
        t1.start();
        Thread.sleep(2000);
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " get lock!");
        condition.signal();
        System.out.println(Thread.currentThread().getName() + " free lock!");
        lock.unlock();
    }
}
