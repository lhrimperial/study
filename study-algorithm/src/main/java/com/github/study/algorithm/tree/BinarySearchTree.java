package com.github.study.algorithm.tree;

/**
 * @author longhairen
 * @create 2017-10-04 13:19
 * @description
 **/
public class BinarySearchTree<T extends Comparable> extends BinaryTree{

    public BinarySearchTree(BinaryTreeNode<T> root) {
        mRoot = root;
    }

    /**
     * 在整个树中查找某个数据
     *
     * @param data
     * @return
     */
    public BinaryTreeNode<T> search(T data) {
        return search(mRoot, data);
    }

    /**
     * 在指定二叉排序树中查找数据
     *
     * @param node
     * @param data
     * @return
     */
    public BinaryTreeNode<T> search(BinaryTreeNode<T> node, T data) {
        if (node == null || node.getValue().compareTo(data) == 0) {    //节点为空或者相等，直接返回该节点
            return node;
        }
        if (node.getValue().compareTo(data) > 0) {    //比节点小，就从左子树里递归查找
            return search(node.getLeftChild(), data);
        } else {        //否则从右子树
            return search(node.getRightChild(), data);
        }
    }

    /**
     * 插入到整个树中
     *
     * @param data
     */
    public void insert(T data) {
        if (mRoot == null) {     //如果当前是空树，新建一个
            mRoot = new BinaryTreeNode<T>();
            mRoot.setValue(data);
            return;
        }

        searchAndInsert(null, mRoot, data);     //根节点的父亲为 null

    }

    /**
     * 两步走：查找、插入
     *
     * @param parent 要绑定的父节点
     * @param node   当前比较节点
     * @param data   数据
     */
    private BinaryTreeNode<T> searchAndInsert(BinaryTreeNode<T>parent, BinaryTreeNode<T> node, T data) {
        if (node == null) {  //当前比较节点为 空，说明之前没有这个数据，直接新建、插入
            node = new BinaryTreeNode<T>();
            node.setValue(data);
            if (parent != null) {    //父节点不为空，绑定关系
                if (parent.getValue().compareTo(data) > 0) {
                    parent.setLeftChild(node);
                } else {
                    parent.setRightChild(node);
                }
            }
            return node;
        }
        //对比的节点不为空
        if (node.getValue() == data) {    //已经有了，不用插入了
            return node;
        } else if (node.getValue().compareTo(data) > 0) {   //比节点小，从左子树里查找、插入
            return searchAndInsert(node, node.getLeftChild(), data);
        } else {
            return searchAndInsert(node, node.getRightChild(), data);
        }
    }

    /**
     * 在整个树中 查找指定数据节点的父亲节点
     *
     * @param data
     * @return
     */
    public BinaryTreeNode<T> searchParent(T data) {
        return searchParent(null, mRoot, data);
    }

    /**
     * 在指定节点下 查找指定数据节点的父亲节点
     *
     * @param parent 当前比较节点的父节点
     * @param node   当前比较的节点
     * @param data   查找的数据
     * @return
     */
    public BinaryTreeNode<T> searchParent(BinaryTreeNode<T> parent, BinaryTreeNode<T> node, T data) {
        if (node == null) { //比较的节点为空返回空
            return null;
        }
        if (node.getValue() == data) {    //找到了目标节点，返回父节点
            return parent;
        } else if (node.getValue().compareTo(data) > 0) {   //数据比当前节点小，左子树中递归查找
            return searchParent(node, node.getLeftChild(), data);
        } else {
            return searchParent(node, node.getRightChild(), data);
        }
    }

    /**
     * 删除指定数据的节点
     *
     * @param data
     */
    public void delete(T data) {
        if (mRoot == null || mRoot.getValue() == data) {  //根节点为空或者要删除的就是根节点，直接删掉
            mRoot = null;
            return;
        }
        //在删除之前需要找到它的父亲
        BinaryTreeNode<T> parent = searchParent(data);
        if (parent == null) {        //如果父节点为空，说明这个树是空树，没法删
            return;
        }

        //接下来该找要删除的节点了
        BinaryTreeNode<T> deleteNode = search(parent, data);
        if (deleteNode == null) {    //树中找不到要删除的节点
            return;
        }
        //删除节点有 4 种情况
        //1.左右子树都为空，说明是叶子节点，直接删除
        if (deleteNode.getLeftChild() == null && deleteNode.getRightChild() == null) {
            //删除节点
            deleteNode = null;
            //重置父节点的孩子状态，告诉他你以后没有这个儿子了
            if (parent.getLeftChild() != null && parent.getLeftChild().getValue() == data) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }
            return;
        } else if (deleteNode.getLeftChild() != null && deleteNode.getRightChild() == null) {
            //2.要删除的节点只有左子树，左子树要继承位置
            if (parent.getLeftChild() != null && parent.getLeftChild().getValue() == data) {
                parent.setLeftChild(deleteNode.getLeftChild());
            } else {
                parent.setRightChild(deleteNode.getLeftChild());
            }
            deleteNode = null;
            return;
        } else if (deleteNode.getRightChild() != null && deleteNode.getRightChild() == null) {
            //3.要删除的节点只有右子树，右子树要继承位置
            if (parent.getLeftChild() != null && parent.getLeftChild().getValue() == data) {
                parent.setLeftChild(deleteNode.getRightChild());
            } else {
                parent.setRightChild(deleteNode.getRightChild());
            }

            deleteNode = null;
        } else {
            //4.要删除的节点儿女双全，既有左子树又有右子树，需要选一个合适的节点继承，这里使用右子树中最左节点
            BinaryTreeNode<T> copyOfDeleteNode = deleteNode;   //要删除节点的副本，指向继承节点的父节点
            BinaryTreeNode<T> heresNode = deleteNode.getRightChild(); //要继承位置的节点，初始为要删除节点的右子树的树根
            //右子树没有左孩子了，他就是最小的，直接上位
            if (heresNode.getLeftChild() == null) {
                //上位后，兄弟变成了孩子
                heresNode.setLeftChild(deleteNode.getLeftChild());
            } else {
                //右子树有左孩子，循环找到最左的，即最小的
                while (heresNode.getLeftChild() != null) {
                    copyOfDeleteNode = heresNode;       //copyOfDeleteNode 指向继承节点的父节点
                    heresNode = heresNode.getLeftChild();
                }
                //找到了继承节点，继承节点的右子树（如果有的话）要上移一位
                copyOfDeleteNode.setLeftChild(heresNode.getRightChild());
                //继承节点先继承家业，把自己的左右孩子变成要删除节点的孩子
                heresNode.setLeftChild(deleteNode.getLeftChild());
                heresNode.setRightChild(deleteNode.getRightChild());
            }
            //最后就是确认位置，让要删除节点的父节点认识新儿子
            if (parent.getLeftChild() != null && parent.getLeftChild().getValue() == data) {
                parent.setLeftChild(heresNode);
            } else {
                parent.setRightChild(heresNode);
            }
        }
    }
}
