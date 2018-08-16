package com.github.study.zk.base.before;

/**
 *
 */
public class Test {
    public static void main(String[] args){
        String s = "12345";
        System.out.println(s);
        change(s);
        System.out.println(s);
    }

    public static void change(String s) {
        System.out.println(s);
        s =  s + "abc";
        System.out.println(s);
    }

    public static void test() {
        Integer a = 1;
        System.out.println(a);
    }
}
