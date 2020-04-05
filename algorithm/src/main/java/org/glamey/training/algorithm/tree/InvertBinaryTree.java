package org.glamey.training.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {
    /**
     * 递归遍历
     *
     * @param tree
     * @return
     */
    public static TreeNode invertTree(TreeNode tree) {
        if (tree == null) {
            return null;
        }

        TreeNode left = invertTree(tree.left);
        TreeNode right = invertTree(tree.right);
        tree.left = right;
        tree.right = left;
        return tree;
    }

    /**
     * 广度优先遍历
     *
     * @param root
     * @return
     */
    public static TreeNode bfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }
}
