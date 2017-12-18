package com.github.study.algorithm.tree;

/**
 * @author longhairen
 * @create 2017-10-04 13:10
 * @description
 **/
public class BinaryTree<T extends Comparable> {
    protected BinaryTreeNode<T> mRoot;   //根节点

    public BinaryTree() {
    }

    public BinaryTree(BinaryTreeNode<T> root) {
        mRoot = root;
    }

    public BinaryTreeNode<T> getRoot() {
        return mRoot;
    }

    public void setRoot(BinaryTreeNode<T> root) {
        mRoot = root;
    }

    /**
     * 二叉树的添左加元素
     * @param child
     */
    public void insertAsLeftChild(BinaryTreeNode<T> child){
        checkTreeEmpty();
        mRoot.setLeftChild(child);
    }

    /**
     * 二叉树的添加右元素
     * @param child
     */
    public void insertAsRightChild(BinaryTreeNode<T> child){
        checkTreeEmpty();
        mRoot.setRightChild(child);
    }

    private void checkTreeEmpty() {
        if (mRoot == null){
            throw new IllegalStateException("Can't insert to a null tree! Did you forget set value for root?");
        }
    }

    /**
     * 二叉树的删除元素
     * @param node
     */
    public void deleteNode(BinaryTreeNode<T> node){
        checkTreeEmpty();
        if (node == null){  //递归出口
            return;
        }
        deleteNode(node.getLeftChild());
        deleteNode(node.getRightChild());
        node = null;
    }

    /**
     * 二叉树的清空
     */
    public void clear(){
        if (mRoot != null){
            deleteNode(mRoot);
        }
    }

    /**
     * 获取树的高度 ，特殊的获得节点高度
     * @return
     */
    public int getTreeHeight(){
        return getHeight(mRoot);
    }
    /**
     * 获得指定节点的度
     * @param node
     * @return
     */
    public int getHeight(BinaryTreeNode<T> node){
        if (node == null){      //递归出口
            return 0;
        }
        int leftChildHeight = getHeight(node.getLeftChild());
        int rightChildHeight = getHeight(node.getRightChild());

        int max = Math.max(leftChildHeight, rightChildHeight);

        return max + 1; //加上自己本身
    }

    /**
     * 获得二叉树的节点数
     * @return
     */
    public int getSize(){
        return getChildSize(mRoot);
    }

    /**
     * 获得指定节点的子节点个数
     * @param node
     * @return
     */
    public int getChildSize(BinaryTreeNode<T> node){
        if (node == null){
            return 0;
        }
        int leftChildSize = getChildSize(node.getLeftChild());
        int rightChildSize = getChildSize(node.getRightChild());

        return leftChildSize + rightChildSize + 1;
    }

    /**
     * 获得指定节点的父亲节点
     * @param node
     * @return
     */
    public BinaryTreeNode<T> getParent(BinaryTreeNode<T> node) {
        if (mRoot == null || mRoot == node) {   //如果是空树，或者这个节点就是根节点，返回空
            return null;
        } else {
            return getParent(mRoot, node);  //否则递归查找 父亲节点
        }
    }

    /**
     * 递归对比 节点的孩子节点 与 指定节点 是否一致
     *
     * @param subTree 子二叉树根节点
     * @param node    指定节点
     * @return
     */
    public BinaryTreeNode<T> getParent(BinaryTreeNode<T> subTree, BinaryTreeNode<T> node) {
        if (subTree == null) {       //如果子树为空，则没有父亲节点，递归出口 1
            return null;
        }
        //正好这个根节点的左右孩子之一与目标节点一致
        if (subTree.getLeftChild() == node || subTree.getRightChild() == node) {    //递归出口 2
            return subTree;
        }
        //需要遍历这个节点的左右子树
        BinaryTreeNode<T> parent;
        if ((parent = getParent(subTree.getLeftChild(), node)) != null) { //左子树节点就是指定节点，返回
            return parent;
        } else {
            return getParent(subTree.getRightChild(), node);    //从右子树找找看
        }

    }

    /**
     * 先序遍历
     * @param node
     */
    public void iterateFirstOrder(BinaryTreeNode<T> node){
        if (node == null){
            return;
        }
        operate(node);
        iterateFirstOrder(node.getLeftChild());
        iterateFirstOrder(node.getRightChild());
    }

    /**
     * 模拟操作
     * @param node
     */
    public T operate(BinaryTreeNode<T> node){
        if (node == null){
            return null;
        }
        System.out.print(node.getValue() + "\t") ;
        return node.getValue();
    }

    /**
     * 中序遍历
     * @param node
     */
    public void iterateMediumOrder(BinaryTreeNode<T> node){
        if (node == null){
            return;
        }
        iterateMediumOrder(node.getLeftChild());
        operate(node);
        iterateMediumOrder(node.getRightChild());
    }

    /**
     * 后序遍历
     * @param node
     */
    public void iterateLastOrder(BinaryTreeNode<T> node){
        if (node == null){
            return;
        }
        iterateLastOrder(node.getLeftChild());
        iterateLastOrder(node.getRightChild());
        operate(node);
    }
}
