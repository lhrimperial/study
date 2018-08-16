package com.github.study.zk.base.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class ZookeeperWatcher implements Watcher {

    AtomicInteger seq = new AtomicInteger();
    private static final String CONNECT_ADDRESS = "10.200.6.197:2181";
    private static final int SESSION_TIMEOUT = 10000;
    private static final String PARENT_PATH = "/testWatcher";
    private static final String CHILDREN_PATH = "/testWatcher/children";
    private ZooKeeper zk = null;
    private CountDownLatch connectSemaphore = new CountDownLatch(1);

    public void releaseConnection() {
        if (this.zk != null) {
            try {
                this.zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void createConnection(String address, int timeout) {
        this.releaseConnection();
        try {
            zk = new ZooKeeper(address, timeout, this);
            System.out.println("开始连接Zookeeper服务器");
            connectSemaphore.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean ceratePath(String path, String data) {
        try {
            this.zk.exists(path, true);
            System.out.println("节点创建成功，path：" +
                    this.zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
                    + "，data：" + data);
            return true;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String readData(String path, boolean watch) {
        try {
            return new String(this.zk.getData(path, watch, null));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean writeData(String path, String data) {
        try {
            System.out.println("更新数据成功，path：" + path + "，state：" + this.zk.setData(path, data.getBytes(), -1));
            return true;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteNode(String path) {
        try {
            this.zk.delete(path, -1);
            System.out.println("节点删除成功，path：" + path);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Stat exists(String path, boolean watch) {
        try {
            return this.zk.exists(path, watch);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getChildren(String path, boolean watch) {
        try {
            return this.zk.getChildren(path, watch);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAllNode() {
        if (this.exists(CHILDREN_PATH, false) != null) {
            this.deleteNode(CHILDREN_PATH);
        }
        if (this.exists(PARENT_PATH, false) != null) {
            this.deleteNode(PARENT_PATH);
        }
    }

    @Override
    public void process(WatchedEvent event) {
        if (event == null) {
            return;
        }

        Event.KeeperState keeperState = event.getState();
        Event.EventType eventType = event.getType();
        // 受影响的path
        String path = event.getPath();

        String logPrefix = "【Watcher-" + this.seq.incrementAndGet() + "】";
        System.out.println(logPrefix + "收到Watcher通知");
        System.out.println(logPrefix + "连接状态:\t" + keeperState.toString());
        System.out.println(logPrefix + "事件类型:\t" + eventType.toString());
        if (Event.KeeperState.SyncConnected == keeperState) {
            // 成功连接上ZK服务器
            if (Event.EventType.None == eventType) {
                System.out.println(logPrefix + "成功连接上ZK服务器");
                connectSemaphore.countDown();
            }
            //创建节点
            else if (Event.EventType.NodeCreated == eventType) {
                System.out.println(logPrefix + "节点创建");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.exists(path, true);
            }
            //更新节点
            else if (Event.EventType.NodeDataChanged == eventType) {
                System.out.println(logPrefix + "节点数据更新");
                System.out.println("我看看走不走这里........");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(logPrefix + "数据内容: " + this.readData(PARENT_PATH, true));
            }
            //更新子节点
            else if (Event.EventType.NodeChildrenChanged == eventType) {
                System.out.println(logPrefix + "子节点变更");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(logPrefix + "子节点列表：" + this.getChildren(PARENT_PATH, true));
            }
            //删除节点
            else if (Event.EventType.NodeDeleted == eventType) {
                System.out.println(logPrefix + "节点 " + path + " 被删除");
            }
            else ;
        }
        else if (Event.KeeperState.Disconnected == keeperState) {
            System.out.println(logPrefix + "与ZK服务器断开连接");
        }
        else if (Event.KeeperState.AuthFailed == keeperState) {
            System.out.println(logPrefix + "权限检查失败");
        }
        else if (Event.KeeperState.Expired == keeperState) {
            System.out.println(logPrefix + "会话失效");
        }
        else ;

        System.out.println("--------------------------------------------");

    }

    /**
     * <B>方法名称：</B>测试zookeeper监控<BR>
     * <B>概要说明：</B>主要测试watch功能<BR>
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        //建立watcher
        ZookeeperWatcher zkWatch = new ZookeeperWatcher();
        //创建连接
        zkWatch.createConnection(CONNECT_ADDRESS, SESSION_TIMEOUT);
        //System.out.println(zkWatch.zk.toString());

        Thread.sleep(1000);

        // 清理节点
        zkWatch.deleteAllNode();

        if (zkWatch.ceratePath(PARENT_PATH, System.currentTimeMillis() + "")) {

            Thread.sleep(1000);


            // 读取数据，在操作节点数据之前先调用zookeeper的getData()方法是为了可以watch到对节点的操作。watch是一次性的，
            // 也就是说，如果第二次又重新调用了setData()方法，在此之前需要重新调用一次。
            System.out.println("---------------------- read parent ----------------------------");
            //zkWatch.readData(PARENT_PATH, true);

            /** 读取子节点，设置对子节点变化的watch，如果不写该方法，则在创建子节点是只会输出NodeCreated，而不会输出NodeChildrenChanged，
             也就是说创建子节点时没有watch。
             如果是递归的创建子节点，如path="/p/c1/c2"的话，getChildren(PARENT_PATH, ture)只会在创建c1时watch，输出c1的NodeChildrenChanged，
             而不会输出创建c2时的NodeChildrenChanged，如果watch到c2的NodeChildrenChanged，则需要再调用一次getChildren(String path, true)方法，
             其中path="/p/c1"
             */
            System.out.println("---------------------- read children path ----------------------------");
            zkWatch.getChildren(PARENT_PATH, true);

            // 更新数据
            zkWatch.writeData(PARENT_PATH, System.currentTimeMillis() + "");

            Thread.sleep(1000);

            // 创建子节点，同理如果想要watch到NodeChildrenChanged状态，需要调用getChildren(CHILDREN_PATH, true)
            zkWatch.readData(CHILDREN_PATH, true);
            zkWatch.ceratePath(CHILDREN_PATH, System.currentTimeMillis() + "");

            Thread.sleep(1000);

            zkWatch.writeData(CHILDREN_PATH, System.currentTimeMillis() + "");
        }

        Thread.sleep(50000);
        // 清理节点
        zkWatch.deleteAllNode();
        Thread.sleep(1000);
        zkWatch.releaseConnection();
    }
}
