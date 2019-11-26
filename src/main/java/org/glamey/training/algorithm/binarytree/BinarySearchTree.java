package org.glamey.training.algorithm.binarytree;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉搜索树工具包
 * <p>
 * BinarySearchTree
 *
 * @author yang.zhou 2019.11.14.10
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    private BinaryNode<T> root;

    public BinaryNode<T> getRoot() {
        return root;
    }

    public BinarySearchTree() {
    }


    /**
     * 构建一棵二叉树
     *
     * @param array
     */
    public BinarySearchTree(T[] array) {
        if (array != null) {
            for (T t : array) {
                insert(t);
            }
        }
    }

    /**
     * 构建一棵二叉树
     *
     * @param array
     */
    public BinarySearchTree(List<T> array) {
        if (!array.isEmpty()) {
            for (T t : array) {
                insert(t);
            }
        }
    }

    public void makeEmpty() {
        this.root = null;
    }

    /**
     * 重置一棵二叉树
     *
     * @param tree
     */
    public void make(BinaryNode<T> tree) {
        this.root = tree;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T t) {
        return contains(t, root);
    }

    private boolean contains(T t, BinaryNode<T> node) {
        if (node == null) {
            return false;
        }

        int compare = t.compareTo(node.element);
        if (compare > 0) {
            return contains(t, node.right);
        } else if (compare < 0) {
            return contains(t, node.left);
        } else {
            return true;
        }
    }


    public T findMax() {
        if (isEmpty()) {
            return null;
        }
        return findMax(root).element;
    }

    private BinaryNode<T> findMax(BinaryNode<T> node) {
        if (node == null) {
            return null;
        }

        while (node.right != null) {
            node = node.right;
        }

        return node;
    }


    public T findMin() {
        if (isEmpty()) {
            return null;
        }

        return findMin(root).element;
    }

    private BinaryNode<T> findMin(BinaryNode<T> node) {
        if (node == null) {
            return null;
        }

        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void insert(T t) {
        root = insert(t, root);
    }

    private BinaryNode<T> insert(T t, BinaryNode<T> node) {
        if (node == null) {
            return new BinaryNode<>(t, null, null);
        }

        int compare = t.compareTo(node.element);
        if (compare > 0) {
            node.right = insert(t, node.right);
        } else {
            node.left = insert(t, node.left);
        }
        return node;
    }


    /**
     * 对于删除，最复杂的是该将被删除的节点A具有两个儿子的情况，一般是该节点的右子树最小的节点B代替节点A，然后在B原来的位置删除掉节点B。
     * <p>
     * 最终会造成left比right深
     *
     * @param t
     */
    public void remove(T t) {
        root = remove(t, root);
    }

    private BinaryNode<T> remove(T t, BinaryNode<T> node) {
        if (node == null) {
            return null;
        }
        int compare = t.compareTo(node.element);
        if (compare > 0) {
            node.right = remove(t, node.right);
        } else if (compare < 0) {
            node.left = remove(t, node.left);
        } else if (node.left != null && node.right != null) {
            // 使用的策略是删除有节点
            node.element = findMin(node.right).element;
            node.right = remove(node.element, node.right);
        } else {
            node = node.left != null ? node.left : node.right;
        }

        return node;
    }

    /**
     * 广度遍历优先 BFS
     * <p>
     *
     * @return
     */
    public List<List<T>> bfs() {
        if (isEmpty()) {
            return new ArrayList<>();
        }
        List<List<T>> list = new ArrayList<>();
        List<T> subList;
        Queue<BinaryNode<T>> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            subList = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                BinaryNode<T> node = q.poll();
                subList.add(node.element);
                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
            }
            list.add(subList);
        }
        return list;
    }

    /**
     * 深度遍历优先 DFS
     * <p>
     * 前序遍历
     * <p>
     * (1) 访问根结点；
     * (2) 先序遍历左子树；
     * (3) 先序遍历右子树。
     *
     * @return
     */
    public void dfs_preOrder() {
        preOrder(root);
    }

    private void preOrder(BinaryNode<T> node) {
        if (node != null) {
            System.out.println(node.element);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * (1) 中序遍历左子树；
     * (2) 访问根结点；
     * (3) 中序遍历右子树。
     */
    public void dfs_inOrder() {
        inOrder(root);
    }

    private void inOrder(BinaryNode<T> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.element);
            inOrder(node.right);
        }
    }

    /**
     * (1) 后序遍历左子树；
     * (2) 后序遍历右子树；
     * (3) 访问根结点。
     */
    public void dfs_postOrder() {
        postOrder(root);
    }

    private void postOrder(BinaryNode<T> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.element);
        }
    }


    /**
     * 10
     * 6       12
     * 2  7    11  14
     *
     * @param args
     */
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(Lists.newArrayList(10, 6, 12, 2, 7, 11, 14));
        // 广度遍历优先 BFS
        System.out.println(tree.bfs()); //[[10], [6, 12], [2, 7, 11, 14]]

        tree.remove(6);
        System.out.println(tree.bfs());//[[10], [7, 12], [2, 11, 14]]


/*
        // DFS 深度遍历优先
        tree.dfs_preOrder();
        tree.dfs_inOrder();
        tree.dfs_postOrder();


        System.out.println(tree.contains(5)); //false
        System.out.println(tree.contains(6)); //true


        System.out.println("tree max -> " + tree.findMax()); // 14
        System.out.println("tree min -> " + tree.findMin()); // 2

        tree.remove(6);
        System.out.println(tree.bfs()); //[[10], [7, 12], [2, 11, 14]]*/

    }
}
