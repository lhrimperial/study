package com.github.study.thread.base.tpool;

import java.util.concurrent.*;

/**
 * @author longhairen
 * @create 2017/9/29 0029 下午 12:33
 */
public class RejectThreadPollDemo {
    public static class Mytask implements Runnable {
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
        Mytask mytask = new Mytask();
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(10),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString() + " is discard");
                    }
                });

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            es.submit(mytask);
            Thread.sleep(10);
        }
    }
}
