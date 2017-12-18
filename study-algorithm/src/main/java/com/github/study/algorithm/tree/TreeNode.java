package com.github.study.algorithm.tree;

/**
 * @author longhairen
 * @create 2017-10-04 11:30
 * @description
 **/
public class TreeNode<T> {
    T value;
    TreeNode<T> leftChild;
    TreeNode<T> rightChild;

    TreeNode() {

    }

    TreeNode(T value) {
        this.value = value;
    }

    /**
     * 增加左子节点
     * @param value
     */
    public void addLeftChild(T value) {
        this.leftChild = new TreeNode<T>(value);
    }

    /**
     * 增加右子节点
     * @param value
     */
    public void addRightChild(T value) {
        this.rightChild = new TreeNode<T>(value);
    }

    /* (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    * 重载equal方法
    */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof TreeNode)){
            return false;
        }
        return this.value.equals(((TreeNode<?>)obj).value);
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     * 重载hashCode方法
     */
    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
    @Override
    public String toString(){
        return this.value==null?"":this.value.toString();
    }
}
