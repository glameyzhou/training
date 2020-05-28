package org.glamey.training.codes.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 广度遍历
 */
public class TreeNodeLoop {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.right = new TreeNode(5);
        TreeNode left = new TreeNode(3);
        left.left = new TreeNode(1);
        root.left = left;

        String bsf = bsf(root);
        System.out.println(bsf);
    }

    /**
     * 中序遍历
     *
     * @param root
     * @return
     */
    public static String inOrder(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            builder.append(root.val).append(", ");
            root = root.right;
        }
        return builder.deleteCharAt(builder.length() - 2).toString();
    }

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
            builder.deleteCharAt(builder.length() - 2);
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString();
    }
}
