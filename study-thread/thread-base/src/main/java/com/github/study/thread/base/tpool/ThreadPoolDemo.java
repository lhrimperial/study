package com.github.study.thread.base.tpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author longhairen
 * @create 2017/9/29 0029 上午 9:10
 */
public class ThreadPoolDemo {

    public static class MyTask implements Runnable {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + " : Thread Id: " + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        MyTask myTask = new MyTask();
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            service.submit(myTask);
        }
        service.shutdown();
    }

}
