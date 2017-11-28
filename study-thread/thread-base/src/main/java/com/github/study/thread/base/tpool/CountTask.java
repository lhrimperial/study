package com.github.study.thread.base.tpool;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author longhairen
 * @create 2017/9/29 0029 下午 7:02
 */
public class CountTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 10000;
    private long start;
    private long end;

    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCompute = (end - start) < THRESHOLD;
        if (canCompute) {
            for (long i = start; i < end; i++) {
                sum += i;
            }
            //System.out.println(sum);
        } else {
            long step = (start + end) / 1000;
            ArrayList<CountTask> subTask = new ArrayList<CountTask>();
            long pos = start;
            CountTask task = null;
            for (int i = 0; i < 1000; i++) {
                long lastOne = pos + step;
                if (lastOne > end) {
                    lastOne = end;
                }
                task = new CountTask(pos, lastOne);
                pos += step;
                subTask.add(task);
                task.fork();
            }
            for (CountTask task1 : subTask) {
                sum += task1.join();
            }
        }
        return sum;
    }

    public static void main(String[] args){
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(0, 2000000L);
        ForkJoinTask<Long> result = forkJoinPool.submit(task);
        try {
            long res = result.get();
            System.out.println("res is " + res);
            System.out.println("use time " + (System.currentTimeMillis() - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        start = System.currentTimeMillis();
       long sum = 0;
       for (int i = 0; i < 2000000L; i++) {
           sum += i;
       }
        System.out.println("res is " + sum);
        System.out.println("use time " + (System.currentTimeMillis() - start));

    }
}
