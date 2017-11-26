package com.github.study.thread.base.juc;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author longhairen
 * @create 2017-11-26 17:14
 * @description
 **/
public class UnsafeTest {

    public static Unsafe getUnsafe() {
        Field f = null;
        Unsafe unsafe = null;
        try {
            f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return unsafe;
    }
}
