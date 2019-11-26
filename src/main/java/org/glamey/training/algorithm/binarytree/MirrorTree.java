package org.glamey.training.algorithm.binarytree;

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
        BinaryNode<Integer> tree = BinaryNodeHelper.build();
        System.out.println(LoopTree.bfs(tree));

        BinaryNode<Integer> mirrorTree = mirrorProcess(tree);
        System.out.println(LoopTree.bfs(mirrorTree));


    }
}
