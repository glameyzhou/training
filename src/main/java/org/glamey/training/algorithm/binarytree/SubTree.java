package org.glamey.training.algorithm.binarytree;

import com.google.common.collect.Lists;

/**
 * @author yang.zhou 2019.11.25.18
 */
public class SubTree {

    /**
     * subTree 是否是 source的子树
     * <p>
     * null不为任何的子树
     * <p>
     * 从source的跟节点开始对比，还是递归来操作方便一些
     *
     * @param subTree
     * @param source
     * @return
     */
    public static boolean isSubTree(BinaryNode<Integer> subTree, BinaryNode<Integer> source) {
        if (subTree == null || source == null) {
            return false;
        }
        return isSubTreeProcess(subTree, source);
    }

    private static boolean isSubTreeProcess(BinaryNode<Integer> subTree, BinaryNode<Integer> source) {
        if (source == null) {
            return false;
        }

        if (isNodeEqual(subTree, source)) {
            return true;
        }
        return isSubTree(subTree, source.left) || isSubTree(subTree, source.right);
    }

    private static boolean isNodeEqual(BinaryNode<Integer> subTree, BinaryNode<Integer> source) {
        if (subTree == null) {
            return false;
        }

        if (source == null) {
            return true;
        }

        if (subTree.element == source.element) {
            return isNodeEqual(subTree.left, source.left) &&
                    isNodeEqual(subTree.right, source.right);
        }
        return false;
    }


    public static void main(String[] args) {

        BinarySearchTree<Integer> sourceBinaryTree = new BinarySearchTree<>(Lists.newArrayList(10, 6, 12, 2, 7, 11, 14));
        BinaryNode<Integer> source = sourceBinaryTree.getRoot();
        System.out.println(sourceBinaryTree.bfs());

        BinarySearchTree<Integer> subBinaryTree = new BinarySearchTree<>(Lists.newArrayList(10, 6, 12));
        BinaryNode<Integer> subTree = subBinaryTree.getRoot();
        System.out.println(sourceBinaryTree.bfs());

        boolean isSubTree = isSubTree(subTree, source);
        System.out.println(isSubTree);

    }
}
