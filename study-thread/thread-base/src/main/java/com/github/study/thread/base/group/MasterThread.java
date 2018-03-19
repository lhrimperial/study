package com.github.study.thread.base.group;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 */
public class MasterThread {

    public static void main(String[] args) throws Exception{
        int tcount = 5;
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(tcount);

        for (int i = 0; i < tcount; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
