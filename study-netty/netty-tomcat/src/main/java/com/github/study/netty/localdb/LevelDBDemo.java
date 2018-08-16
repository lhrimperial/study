package com.github.study.netty.localdb;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBFactory;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.impl.Iq80DBFactory;

import java.io.File;

/**
 *
 */
public class LevelDBDemo {

    public static void main(String[] args) throws Exception{
        test1();
    }

    public static void test1() throws Exception{
        String path = "/data/leveldb";
        DBFactory factory = new Iq80DBFactory();
        Options options = new Options();
        options.createIfMissing(true);
        DB db = factory.open(new File(path), options);

        db.put(Iq80DBFactory.bytes("key01"), Iq80DBFactory.bytes(String.valueOf(System.currentTimeMillis())));
        String value = Iq80DBFactory.asString(db.get(Iq80DBFactory.bytes("key01")));
        System.out.println(value);
        db.delete(Iq80DBFactory.bytes("key01"));
    }
}
