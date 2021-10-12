package org.glamey.training.codes.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 广度优先遍历
 */
public class TreeNodeBFS {

    public static void main(String[] args) {

        /**
         *      4
         *    3   5
         *  1        10
         */
        TreeNode root = new TreeNode(4);
        TreeNode t1 = new TreeNode(1);
        TreeNode t3 = new TreeNode(3);
        TreeNode t10 = new TreeNode(10);
        TreeNode t5 = new TreeNode(5);
        t3.left = t1;
        t5.right = t10;
        root.left = t3;
        root.right = t5;

        System.out.println(bsf(root));
        System.out.println(inOrderByStack(root));
        System.out.println(inOrderByRecursion(root));
    }

    public static String inOrderByRecursion(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        _inOrderByRecursion(root, list);
        return list.stream().map(Object::toString).collect(Collectors.joining(","));
    }

    private static void _inOrderByRecursion(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        _inOrderByRecursion(root.left, list);
        list.add(root.val);
        _inOrderByRecursion(root.right, list);
    }

    /**
     * 中序遍历(左根右)
     */
    public static String inOrderByStack(TreeNode root) {
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
            builder.append(root.val).append(",");
            root = root.right;
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    /**
     * 广度优先遍历
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
                builder.append(node.val).append(",");
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            builder.deleteCharAt(builder.length() - 1);
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString();
    }
}
