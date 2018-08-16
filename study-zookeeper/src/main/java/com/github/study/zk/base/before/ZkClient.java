package com.github.study.zk.base.before;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 *
 */
public class ZkClient {
    private static final int SESSION_TIMEOUT = 5000;

    protected String hosts;
    protected int timeOut = SESSION_TIMEOUT;
    protected ZooKeeper zk;
    private CountDownLatch connectedSignal = new CountDownLatch(1);

    public ZkClient(String hosts) {
        this.hosts = hosts;
    }

    public ZkClient(String hosts, int timeOut) {
        this.hosts = hosts;
        this.timeOut = timeOut;
    }

    /**
     * ZooKeeper(String connectString, int sessionTimeout, Watcher watcher) throws IOException
     * 1. connectString：zookeeper server列表, 以逗号隔开. ZooKeeper对象初始化后, 将从server列表中选择一个server,
     * 并尝试与其建立连接. 如果连接建立失败, 则会从列表的剩余项中选择一个server, 并再次尝试建立连接.
     * 2. sessionTimeout：指定连接的超时时间.
     * 3. watcher：事件回调接口.
     * @throws Exception
     */
    public void connect() throws Exception {
        zk = new ZooKeeper(hosts, timeOut, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                //连接建立, 回调process接口时, 其event.getState()为KeeperState.SyncConnected
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    //唤醒wait在connect方法上的线程
                    connectedSignal.countDown();
                }
            }
        });
        //等待连接完成
        connectedSignal.await();
    }

    /**
     * 创建节点
     * String create(String path, byte[] data, List acl, CreateMode createMode);
     * path：znode的路径.
     * data：与znode关联的数据.
     * acl：指定权限信息, 如果不想指定权限, 可以传入Ids.OPEN_ACL_UNSAFE.
     * createMode：指定znode类型. CreateMode是一个枚举类, 从中选择一个成员传入即可；
     */
    public void create(String nodePath, byte[] data) throws Exception {
        zk.create(nodePath, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    /**
     * List getChildren(String path, boolean watch);
     * ZooKeeper对象的getChildren方法用于获取子node列表.
     * watch参数用于指定是否监听path node的子node的增加和删除事件, 以及path node本身的删除事件.
     * @param path
     * @param watch
     * @return
     */
    public List<String> getChildren(String path, boolean watch) throws Exception {
        return zk.getChildren(path, watch);
    }

    /**
     * ZooKeeper对象的exists方法用于判断指定znode是否存在.
     * Stat exists(String path, boolean watch);
     * watch参数用于指定是否监听path node的创建, 删除事件, 以及数据更新事件. 如果该node存在, 则返回该node的状态信息, 否则返回null.
     */
    public boolean exists(String path, boolean watch) throws Exception {
        Stat stat = zk.exists(path, watch);
        return stat != null;
    }

    /**
     * ZooKeeper对象的getData方法用于获取node关联的数据.
     * byte[] getData(String path, boolean watch, Stat stat);
     * watch参数用于指定是否监听path node的删除事件, 以及数据更新事件, 注意, 不监听path node的创建事件,
     * 因为如果path node不存在, 该方法将抛出KeeperException.NoNodeException异常.
     * stat参数是个传出参数, getData方法会将path node的状态信息设置到该参数中.
     */
    public byte[] getData(String path, boolean watch, Stat stat) throws Exception {
        return zk.getData(path, watch, stat);
    }

    /**
     * ZooKeeper对象的setData方法用于更新node关联的数据.
     * Stat setData(final String path, byte data[], int version);
     * data为待更新的数据.
     * version参数指定要更新的数据的版本, 如果version和真实的版本不同, 更新操作将失败. 指定version为-1则忽略版本检查.
     * 返回path node的状态信息.
     */
    public void setData(String path, String value) throws Exception{
        zk.setData(path, value.getBytes(), -1);
    }

    /**
     * ZooKeeper对象的delete方法用于删除znode.
     * void delete(final String path, int version);
     */
    public void delete(String path) throws Exception{
        zk.delete(path, -1);
    }


}
