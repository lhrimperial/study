package com.github.study.netty.localdb;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;

/**
 *
 */
public class MapDBDemo {

    public static void main(String[] args){
        mapTest();
    }

    public static void mapTest() {
        //fluet式    无则创建，有则打开，需密码校验
        DB db = DBMaker.fileDB(new File("/data/mapdb.db")).closeOnJvmShutdown().make();
		/*
		db.getHashMap(name);
		db.getHashSet(name);
		db.getQueue(name);
		db.getTreeMap(name);
		db.getTreeSet(name);
		db.getStack(name);*/
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        map.put("something", "here");
        map.put(1, "test1");
        map.put(2, "test2");
        //提交持久化
        db.commit();

        System.out.println(map.get("something"));

        map.put(3, "test3");
        // map.keySet() is  [1,2,3]
        //回滚
//        db.rollback();
        // map.keySet() is  [1,2]
        System.out.println(map);
        db.close();
    }

    public static void setTest() {

    }

    public static void queueTest() {

    }
}
