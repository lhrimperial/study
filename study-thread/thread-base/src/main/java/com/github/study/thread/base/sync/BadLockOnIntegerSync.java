package com.github.study.thread.base.sync;

/**
 * @author longhr
 * @version 2017/11/24 0024
 *
 * Integer 属于不变对象，一旦创建就不能再改变
 * 包装类型使用的是Integer.valueOf() new一个新的Integer对象
 *
 */
public class BadLockOnIntegerSync implements Runnable {

    public static Integer num = new Integer(0);
    public static BadLockOnIntegerSync sync = new BadLockOnIntegerSync();

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            synchronized (num) {
                num++;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(sync);
        Thread t2 = new Thread(sync);
        t1.start();t2.start();
        t1.join();t2.join();

        System.out.println(num);

//        Integer.valueOf(0);
    }
}
