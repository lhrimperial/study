package com.github.study.thread.base.sync;

/**
 * @author longhr
 * @version 2017/11/24 0024
 */
public class IntegerTest {
    public static Integer num = 0;
    public static void increase(){
        num++;
    }
    public static void main(String[] args){
        IntegerTest.increase();
        System.out.println(num);
    }
}
