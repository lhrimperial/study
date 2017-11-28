package com.github.study.thread.base.sync;


/**
 * @author longhr
 * @version 2017/11/27 0027
 */
public class SyncBlock {
    public static final Object obj = new Object();

    public void run() {
        synchronized(obj){
            System.out.println("hello");
        }
    }
}
