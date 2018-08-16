package com.github.study.zk.base.zookeeper;


import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 *
 */
public class ZkClientBase {

    private static final String CONNECT_ADDRESS = "10.200.6.197:2181";
    private static final int SESSION_TIMEOUT = 10000;

    public static void main(String[] args) throws Exception{
        ZkClient zkClient = new ZkClient(CONNECT_ADDRESS, SESSION_TIMEOUT);
        //1.create和delete方法
        zkClient.createEphemeral("/temp"); //创建临时节点，会话失效后删除
        zkClient.createPersistent("/super/c1", true); //创建持久化节点，true表示如果父节点不存在则创建父节点
        Thread.sleep(1000);
        zkClient.delete("/temp"); //删除节点
        zkClient.deleteRecursive("/super"); //递归删除，如果该节点下有子节点，会把子节点也删除

        //2.设置path和data，并读取子节点和每个节点的内容
        zkClient.createPersistent("/super", "1234"); //创建并设置节点的值
        zkClient.createPersistent("/super/c1", "内容一");
        zkClient.createPersistent("/super/c2", "内容二");
        List<String> children = zkClient.getChildren("/super");
        for(String child : children) {
            System.out.print(child + "：");
            String childPath = "/super/" + child;
            String data = zkClient.readData(childPath); //读取指定节点的值
            System.out.println(data);
        }

        //3.更新和判断节点是否存在
        zkClient.writeData("/super/c1", "新内容"); //修改指定节点的值
        String cData = zkClient.readData("/super/c1");
        System.out.println(cData);
        System.out.println(zkClient.exists("/super/c1")); //判断指定节点是否存在

        zkClient.close();
    }

}
