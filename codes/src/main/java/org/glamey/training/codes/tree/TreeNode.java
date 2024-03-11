package org.glamey.training.codes.tree;


import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public static TreeNode createTree(int[] nums, int index) {
        TreeNode node = null;
        if (index < nums.length) {
            node = new TreeNode(nums[index]);
            node.left = createTree(nums, 2 * index + 1);
            node.right = createTree(nums, 2 * index + 2);
        }
        return node;
    }

    public static void printTreeBFS(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
        }
        StringBuffer buffer = new StringBuffer("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            buffer.append(node.val).append(",");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        buffer.deleteCharAt(buffer.length() - 1);
        buffer.append("]");
        System.out.println(buffer);
    }

    public static void main(String[] args) {
        TreeNode treeNode = createTree(new int[]{1, 2, 3, 4, 5}, 0);
        printTreeBFS(treeNode);
    }
}
