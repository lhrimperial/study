package com.github.study.thread.base.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author longhairen
 * @create 2017/9/27 0027 上午 9:58
 */
public class SemaphoreDemo implements Runnable {
    final Semaphore temp = new Semaphore(5);

    @Override
    public void run() {
        try {
            temp.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ":done!");
            temp.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        ExecutorService exec = Executors.newFixedThreadPool(20);
        SemaphoreDemo semaDemo = new SemaphoreDemo();
        for (int i = 0; i < 20; i++) {
            exec.submit(semaDemo);
        }
    }
}
