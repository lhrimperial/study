package com.github.study.thread.base;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class ObjectMethod implements Runnable{

    CallObject callObject = new CallObject();
    private static ExecutorService service = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws Exception{
        for (int i=0;i<10000000;i++){
            service.execute(new ObjectMethod());
        }
        service.shutdown();
    }

    @Override
    public void run() {
        callObject.callMethod();
    }

    public static class CallObject {
        public void callMethod() {
            System.out.println("hello " + Thread.currentThread().getName());
        }
    }

}
