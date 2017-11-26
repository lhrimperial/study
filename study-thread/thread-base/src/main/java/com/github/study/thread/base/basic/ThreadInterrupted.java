package com.github.study.thread.base.basic;

/**
 * @author longhairen
 * @create 2017-09-24 11:12
 * @description
 **/
public class ThreadInterrupted {

    public static void main(String[] args) throws Exception{
//        interrupt1();
        interrupt2();
    }

    public static void interrupt2() throws Exception{
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted!");
                        break;
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted when sleep!");
                        //Thread.sleep()方法由于中断而抛出异常，此时，他会清除中断标记，所以在异常处理中，再次设置中断标记。
                        Thread.currentThread().interrupt();
                    }
                    System.out.println("Thread yield");
                    Thread.yield();
                }
            }
        });
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }

    public static void interrupt1() throws Exception{
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted");
                        break;
                    }
                    System.out.println("Thread yield");
                    Thread.yield();
                }
            }
        };

        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
