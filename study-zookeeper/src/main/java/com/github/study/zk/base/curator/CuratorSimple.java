package com.github.study.zk.base.curator;

import com.sun.deploy.util.SessionState;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 *
 */
public class CuratorSimple {
    private static final String CONNECT_ADDRESS = "10.200.6.197:2181";
    private static final int SESSION_TIMEOUT = 5000;
    private static final int CONNECT_TIMEOUT = 5000;

    public static void main(String[] args) throws Exception {
        //静态工程方式创建客户端
//        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
//        CuratorFramework client = CuratorFrameworkFactory.newClient(CONNECT_ADDRESS, retryPolicy);
//        client.start();
//        client.create().forPath("/hello","hello curator".getBytes());

        //使用Fluent风格的Api创建会话
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(CONNECT_ADDRESS)
                .connectionTimeoutMs(CONNECT_TIMEOUT).sessionTimeoutMs(SESSION_TIMEOUT)
                .namespace("base")
                .retryPolicy(retryPolicy).build();
        client.start();
//        client.create().forPath("/hello", "hello curator".getBytes("utf-8"));
        if (client.checkExists().forPath("/hello") != null) {
            client.delete().deletingChildrenIfNeeded().forPath("/hello");
        }

        client.create().creatingParentsIfNeeded().forPath("/hello/world", "hello world".getBytes());

        Stat stat = client.checkExists().forPath("/hello/world");

        System.out.println(new String(client.getData().forPath("/hello/world")));
        client.setData().forPath("/hello/world", "change data".getBytes());
        System.out.println(new String(client.getData().forPath("/hello/world")));

        client.create().creatingParentsIfNeeded().forPath("/hello/wang/wang");

        List<String> childrenNode = client.getChildren().forPath("/hello");
        for (String string : childrenNode) {
            System.out.println(string);
        }

        client.close();
    }
}
