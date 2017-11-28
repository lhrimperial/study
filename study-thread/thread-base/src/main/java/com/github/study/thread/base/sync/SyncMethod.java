package com.github.study.thread.base.sync;

/**
 * @author longhr
 * @version 2017/11/27 0027
 *
 * -help 输出 javap 的帮助信息。
　　-l 输出行及局部变量表。
　　-b 确保与 JDK 1.1 javap 的向后兼容性。
　　-public 只显示 public 类及成员。
　　-protected 只显示 protected 和 public 类及成员。
　　-package 只显示包、protected 和 public 类及成员。这是缺省设置。
　　-private 显示所有类和成员。
　　-J[flag] 直接将 flag 传给运行时系统。
　　-s 输出内部类型签名。
　　-c 输出类中各方法的未解析的代码，即构成 Java 字节码的指令。
　　-verbose 输出堆栈大小、各方法的 locals 及 args 数,以及class文件的编译版本
　　-classpath[路径] 指定 javap 用来查找类的路径。如果设置了该选项，则它将覆盖缺省值或 CLASSPATH 环境变量。目录用冒号分隔。
　 -bootclasspath[路径] 指定加载自举类所用的路径。缺省情况下，自举类是实现核心 Java 平台的类，位于 jrelib下面。
　　-extdirs[dirs] 覆盖搜索安装方式扩展的位置。扩展的缺省位置是 jrelibext。
 */
public class SyncMethod {

    public int i;

    public synchronized void syncTask(){
        i++;
    }

    public void syncTask1(){
        i++;
    }
}
