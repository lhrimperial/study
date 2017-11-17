package com.github.study.thread.base.basic;

import java.util.concurrent.*;

/**
 * @author longhr
 * @version 2017/11/17 0017
 *
 * 线程初始化过程：
 * 1. new Thread()过程中会为线程设置线程组、执行目标、线程名、线程栈大小
 *    默认和主线程一个组；Thread 实现了Runnable，本身即为一个执行目标
 * 2. 真正创建线程是在start()中，这里使用了本地调用，通过C代码初始化线程需要的系统资源。
 *    此时start()的这个线程处于就绪状态，当得到CPU的时间片后就会执行其中的run()方法。
 * 3. 线程从创建到最终的消亡，要经历若干个状态。
 *    一般来说，线程包括以下这几个状态：创建(new)、就绪(runnable)、运行(running)、阻塞(blocked)、time waiting、waiting、消亡（dead）。
 */
public class NewThreadWays {

    public static void main(String[] args){
        newThreadFirstWay();
    }

    /**
     * 方式一：继承Thread，重写run()方法
     */
    public static void newThreadFirstWay() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("extends Thread and overwrite run()");
            }
        };
        thread.start();
    }

    /**
     * 方式二：实现Runnable，重写run()方法
     */
    public static void newThreadSecondWay() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("implements Runnable and override run()");
            }
        });
        thread.start();
    }

    /**
     * 方式三：通过线程池创建线程
     */
    public static void newThreadThirdWay() {
//        ExecutorService service = Executors.newFixedThreadPool(5);
        ExecutorService service = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        service.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("ThreadPoolExecutor execute");
            }
        });
        service.shutdown();
    }


}
