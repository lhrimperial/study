package com.github.study.thread.base.tpool;

import java.util.concurrent.*;

/**
 * @author longhairen
 * @create 2017/9/29 0029 下午 12:44
 */
public class ThreadFactoryDemo {
    public static class MyTask implements Runnable {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + " : Thread ID : " + Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        MyTask myTask = new MyTask();
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setDaemon(true);
                        System.out.println("create " + t);
                        return t;
                    }
                });
        for (int i = 0; i < 5; i++) {
            es.submit(myTask);
        }
        Thread.sleep(2000);
    }
}
