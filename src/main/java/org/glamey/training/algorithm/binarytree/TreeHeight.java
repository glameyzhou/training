package org.glamey.training.algorithm.binarytree;

import java.util.List;

/**
 * 求二叉树的高度
 *
 * @author yang.zhou 2019.12.16.16
 */
public class TreeHeight {

    public static void main(String[] args) {
        BinaryNode<Integer> tree = BinaryNodeHelper.build();

        List<List<Integer>> bfs = LoopTree.bfs(tree);
        System.out.println(bfs);

        int height = getHeight(tree);
        System.out.println(height);


    }

    private static int getHeight(BinaryNode<Integer> tree) {
        if (tree == null) {
            return 0;
        }

        int leftHeight = getHeight(tree.left);
        int rightHeight = getHeight(tree.right);
        int height = Math.max(leftHeight, rightHeight) + 1;
        return height;
    }
}
