package com.github.study.thread.base.map;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class ListDemo {
    public static void main(String[] args){
        List list1 = new ArrayList();
        List list2 = new LinkedList();

        BlockingQueue blockingDeque1 = new LinkedBlockingDeque();
        blockingDeque1 = new ArrayBlockingQueue(1);

        ReentrantLock reentrantLock = null;
    }
}
