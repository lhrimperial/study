package com.github.study.zk.base.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 *
 */
public class ZookeeperBase {
    static final String CONNECT_ADDRESS = "10.200.6.197:2181";
    static final int SESSION_TIMEOUT = 2000;
    static final CountDownLatch connectSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception{
        ZooKeeper zooKeeper = new ZooKeeper(CONNECT_ADDRESS, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (Event.KeeperState.SyncConnected == event.getState()) {
                    connectSemaphore.countDown();
                }
            }
        });
        connectSemaphore.await();
        System.out.println("connect success");
    }

}
