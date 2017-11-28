package com.github.study.thread.base.tpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author longhairen
 * @create 2017/9/29 0029 上午 9:15
 * scheduleAtFixedRate：任务开始于给定延时，后续的任务按照给定周期进行
 * scheduleWithFixedDelay：任务开始于给定延时，后续任务将按照给定延时执行，即上一个任务结束时间到下一个任务开始时间的时间差
 *
 */
public class ScheduledExecutorServiceDemo {
    public static void main(String[] args){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        service.scheduleWithFixedDelay(new Runnable() {
        //service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println(System.currentTimeMillis()/1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 2, TimeUnit.SECONDS);
    }
}
