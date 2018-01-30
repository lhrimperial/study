package com.github.study.thread.base.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class MulitThread1 {
    private ConcurrentHashMap concurrentHashMap;
    private int a=1, b=2;

    public void foo(){  // 线程1
        a=3;
        b=4;
    }

    public int getA(){ // 线程2
        return a;
    }
    public int getB(){ // 线程2
        return b;
    }
}
