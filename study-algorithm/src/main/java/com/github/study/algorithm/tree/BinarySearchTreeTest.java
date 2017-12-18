package com.github.study.algorithm.tree;

/**
 * @author longhairen
 * @create 2017-10-04 14:53
 * @description
 **/
public class BinarySearchTreeTest {
    public static void main(String[] args) {
        //test1();
        test2();

    }

    public static void test2(){
        //乱序插入到二叉排序树中
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>(null);
        binarySearchTree.insert(8);
        binarySearchTree.insert(6);
        binarySearchTree.insert(4);
        binarySearchTree.insert(16);
        binarySearchTree.insert(10);
        binarySearchTree.insert(12);
        binarySearchTree.insert(14);


    }

    public static void test1(){
        //乱序插入到二叉排序树中
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>(null);
        binarySearchTree.insert(8);
        binarySearchTree.insert(3);
        binarySearchTree.insert(1);
        binarySearchTree.insert(6);
        binarySearchTree.insert(4);
        binarySearchTree.insert(7);
        binarySearchTree.insert(10);
        binarySearchTree.insert(13);
        binarySearchTree.insert(14);

        //中序遍历
        binarySearchTree.iterateMediumOrder(binarySearchTree.getRoot());
        System.out.println("");
        //查找某个数据
        System.out.println(binarySearchTree.search(10).getValue());
        //删除某个数据对应的元素
        binarySearchTree.delete(6);
        //中序遍历删除后的二叉排序树
        binarySearchTree.iterateMediumOrder(binarySearchTree.getRoot());
    }
}
