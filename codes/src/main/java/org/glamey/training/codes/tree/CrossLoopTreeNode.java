package org.glamey.training.codes.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 按照层级交叉遍历一棵树(之字型打印二叉树)
 * 3
 * 9  10
 * 11      19
 * <p>
 * 输出 [3, 10, 9, 11, 19]
 */
public class CrossLoopTreeNode {

    public static void main(String[] args) {
        TreeNode node11 = new TreeNode(11);
        TreeNode node9 = new TreeNode(9);
        node9.left = node11;

        TreeNode node19 = new TreeNode(19);
        TreeNode node10 = new TreeNode(10);
        node10.right = node19;

        TreeNode root = new TreeNode(3);
        root.left = node9;
        root.right = node10;

        List<Integer> list = crossLoopTreeNode(root);
        System.out.println(list);
    }

    private static List<Integer> crossLoopTreeNode(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (level % 2 == 0) {
                    list.addFirst(node.val);
                } else {
                    list.addLast(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            level++;
            ret.addAll(list);
        }
        return ret;
    }
}
