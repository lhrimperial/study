package com.githup.study.netty.weba.io.bio.server;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author longhr
 * @version 2017/12/4 0004
 */
public class HandlerExecutorPool {
    private ExecutorService executor;

    public HandlerExecutorPool(int maxSize, int queueSize) {
        this.executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxSize, 120L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(Runtime.getRuntime().availableProcessors()));
    }

    public void execute(Runnable task) {
        executor.execute(task);
    }
}
