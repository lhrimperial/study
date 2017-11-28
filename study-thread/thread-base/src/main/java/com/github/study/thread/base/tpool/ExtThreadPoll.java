package com.github.study.thread.base.tpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author longhairen
 * @create 2017/9/29 0029 下午 1:35
 */
public class ExtThreadPoll {
    public static class MyTask implements Runnable {
        public String name;
        public MyTask(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + " Thread ID: " + Thread.currentThread().getId() + ", Task Name: " + this.name);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行： " + ((MyTask)r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成： " + ((MyTask)r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };

        for (int i = 0; i < 5; i++) {
            MyTask task = new MyTask("TASK-NAME-" + i);
            es.execute(task);
            es.submit(task);
            Thread.sleep(10);
        }
        es.shutdown();
    }
}
