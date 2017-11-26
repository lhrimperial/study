package com.github.study.thread.base.basic;

import java.util.Map;

/**
 * @author longhr
 * @version 2017/11/24 0024
 */
public class VolatileUnSafe {
    public static volatile int race = 0;

    public static void increase() {
        race++;
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        //等待所有累加线程都结束
//        idea、jdk、eclispe中空main方法的线程数量不一样,因为Idea 多了个monitor ctrlbreak线程
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(race);
    }


    /**
     *

     public class com.github.study.thread.base.basic.VolatileUnSafe {
     public static volatile int race;

     public com.github.study.thread.base.basic.VolatileUnSafe();
     Code:
     0: aload_0
     1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     4: return

     public static void increase();
     Code:
     0: getstatic     #2                  // Field race:I
     3: iconst_1
     4: iadd
     5: putstatic     #2                  // Field race:I
     8: return

     public static void main(java.lang.String[]);
     Code:
     0: bipush        20
     2: anewarray     #4                  // class java/lang/Thread
     5: astore_1
     6: iconst_0
     7: istore_2
     8: iload_2
     9: bipush        20
     11: if_icmpge     43
     14: aload_1
     15: iload_2
     16: new           #4                  // class java/lang/Thread
     19: dup
     20: new           #5                  // class com/github/study/thread/base/basic/VolatileUnSafe$1
     23: dup
     24: invokespecial #6                  // Method com/github/study/thread/base/basic/VolatileUnSafe$1."<init>":()V
     27: invokespecial #7                  // Method java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
     30: aastore
     31: aload_1
     32: iload_2
     33: aaload
     34: invokevirtual #8                  // Method java/lang/Thread.start:()V
     37: iinc          2, 1
     40: goto          8
     43: invokestatic  #9                  // Method java/lang/Thread.activeCount:()I
     46: iconst_2
     47: if_icmple     56
     50: invokestatic  #10                 // Method java/lang/Thread.yield:()V
     53: goto          43
     56: getstatic     #11                 // Field java/lang/System.out:Ljava/io/PrintStream;
     59: getstatic     #2                  // Field race:I
     62: invokevirtual #12                 // Method java/io/PrintStream.println:(I)V
     65: return

     static {};
     Code:
     0: iconst_0
     1: putstatic     #2                  // Field race:I
     4: return
     }


     */
}
