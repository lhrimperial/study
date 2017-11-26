package com.github.study.thread.base.basic;


/**
 * @author longhairen
 * @create 2017/9/25 0025 上午 10:08
 */
public class ThreadGroupName implements Runnable {

    private static volatile boolean exit = false;

    public static void main(String[] args) throws Exception{
        ThreadGroup tg = new ThreadGroup("PrintGroup");
        Thread t1 = new Thread(tg, new ThreadGroupName(), "T1");
        Thread t2 = new Thread(tg, new ThreadGroupName(), "T2");
        t1.start();
        t2.start();
        System.out.println(tg.activeCount());
        tg.list();

        Thread.sleep(5000);
        exit = true;
    }

    public void run() {
        String groupAndName = Thread.currentThread().getThreadGroup().getName() + "_" + Thread.currentThread().getName();
        while (!exit) {
            System.out.println("I am " + groupAndName);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
