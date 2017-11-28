package com.github.study.thread.base.distribute;


import com.github.study.thread.base.distribute.lock.ZkDistributedLock;

/**
 * @author longhairen
 * @create 2017/9/5 0005 下午 2:41
 */
public class ZkDistributeLockTest {
    static int n = 500;

    public static void secskill() {
        System.out.println(--n);
    }

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            public void run() {
                ZkDistributedLock lock = null;
                try {
                    lock = new ZkDistributedLock("10.112.3.145:2181", "test1");
                    lock.lock();
                    secskill();
                    System.out.println(Thread.currentThread().getName() + "正在运行");
                } finally {
                    if (lock != null) {
                        lock.unlock();
                    }
                }
            }
        };

        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
    }
}
