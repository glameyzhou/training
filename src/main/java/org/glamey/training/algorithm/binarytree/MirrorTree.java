package org.glamey.training.algorithm.binarytree;

import com.google.common.collect.Lists;

/**
 * 二叉树的镜像
 *
 * @author yang.zhou 2019.11.26.09
 */
public class MirrorTree {


    public static BinaryNode<Integer> mirrorProcess(BinaryNode<Integer> tree) {
        if (tree == null) {
            return null;
        }
        if (tree.left != null && tree.right != null) {
            BinaryNode<Integer> leftTree = mirrorProcess(tree.left);
            BinaryNode<Integer> rightTree = mirrorProcess(tree.right);
            tree.left = rightTree;
            tree.right = leftTree;
        }

        /*// 去掉无效的重复递归
        BinaryNode<Integer> leftTree = tree.left != null ? mirrorProcess(tree.left) : null;
        BinaryNode<Integer> rightTree = tree.right != null ? mirrorProcess(tree.right) : null;
        tree.left = rightTree;
        tree.right = leftTree;
        */
        return tree;
    }


    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>(Lists.newArrayList(10, 6, 12, 2, 7, 11, 14));
        BinaryNode<Integer> tree = binarySearchTree.getRoot();
        System.out.println(binarySearchTree.bfs());

        BinaryNode<Integer> mirrorTree = mirrorProcess(tree);
        binarySearchTree.make(mirrorTree);
        System.out.println(binarySearchTree.bfs());


    }
}
