package com.github.study.thread.base.distribute;

import com.github.study.thread.base.StringUtils;
import com.github.study.thread.base.distribute.lock.RedisDistributedLock;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author longhairen
 * @create 2017/9/5 0005 下午 2:51
 */
public class RedisDistributeLockTest {
    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 50; i++) {
            ThreadA threadA = new ThreadA(service);
            threadA.start();
        }
    }
}

class ThreadA extends Thread {
    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }
}

class Service {
    private static JedisPool pool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(200);
        // 设置最大空闲数
        config.setMaxIdle(8);
        // 设置最大等待时间
        config.setMaxWaitMillis(1000 * 100);
        // 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
        config.setTestOnBorrow(true);
        pool = new JedisPool(config, "10.112.3.145", 6379, 3000);
    }

    RedisDistributedLock lock = new RedisDistributedLock(pool);

    int n = 500;

    public void seckill() {
        // 返回锁的value值，供释放锁时候进行判断
        String indentifier = lock.lockWithTimeout("resource", 1000, 1000);
        if (StringUtils.isNotBlank(indentifier)) {
            System.out.println(Thread.currentThread().getName() + "获得了锁，value：" + indentifier);
            System.out.println(--n);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.releaseLock("resource", indentifier);
            System.out.println(Thread.currentThread().getName()+"释放了锁");
        }else {
            System.out.println(Thread.currentThread().getName() + "没有获得锁，结束退出");
        }
    }
}
