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
        boolean sameTree = isSameTree(subTree, source);
        if (sameTree) {
            return true;
        }
        return isSubTree(subTree, source.left) || isSubTree(subTree, source.right);
    }

    private static boolean isSameTree(BinaryNode<Integer> subTree, BinaryNode<Integer> source) {
        if (source == null || subTree == null) {
            return true;
        }

        if (subTree.element == source.element) {
            return isSameTree(subTree.left, source.left) && isSameTree(subTree.right, source.right);
        }
        return false;
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

        BinaryNode<Integer> sourceTree = BinaryNodeHelper.build(Lists.newArrayList(10, 6, 12, 2, 7, 11, 14));
        System.out.println(LoopTree.bfs(sourceTree));

        BinaryNode<Integer> subTree = BinaryNodeHelper.build(Lists.newArrayList(6, 2, 7));
        System.out.println(LoopTree.bfs(subTree));

        boolean isSubTree = isSubTree(subTree, sourceTree);
        System.out.println(isSubTree);

    }
}
