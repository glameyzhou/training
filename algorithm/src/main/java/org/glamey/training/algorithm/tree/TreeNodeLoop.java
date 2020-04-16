package org.glamey.training.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNodeLoop {

    /**
     * 二叉树广度遍历，按照层次遍历
     *
     * @param root
     * @return
     */
    public static String bsf(TreeNode root) {
        if (root == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                builder.append(node.val).append(", ");
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            builder.deleteCharAt(builder.length() - 1);
            builder.append("\r\n");
        }
        return builder.toString();
    }
}
