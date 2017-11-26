package com.github.study.thread.base.basic;

/**
 * @author longhairen
 * @create 2017/9/25 0025 上午 9:49
 */
public class VolatileAtomicity {
    static volatile int i;

    public static void main(String[] args) throws Exception{
        Thread[] threads = new Thread[10];
        for (int k = 0; k < 10; k++) {
            threads[k] = new Thread(new PlusTask());
            threads[k].start();
        }
        for (int k = 0; k < 10; k++) {
            threads[k].join();
        }
        System.out.println(i);
    }
    public static class PlusTask implements Runnable {
        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                i++;
            }
        }
    }
}
