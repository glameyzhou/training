package org.glamey.training.algorithm.tree;

import java.util.Stack;

/**
 * 剑指offer
 * 搜索二叉树中的第K大节点
 */
public class KthTreeNode {


    public static void main(String[] args) {


        /**
         *          5
         *     3        7
         *   2   4    6   8
         */
        TreeNode n2 = new TreeNode(2);
        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3);
        n3.left = n2;
        n3.right = n4;

        TreeNode n6 = new TreeNode(6);
        TreeNode n8 = new TreeNode(8);
        TreeNode n7 = new TreeNode(7);
        n7.left = n6;
        n7.right = n8;


        TreeNode root = new TreeNode(5);
        root.left = n3;
        root.right = n7;

        System.out.println(TreeNodeLoop.inOrder(root));



        System.out.println(TreeNodeLoop.bsf(findKthTreeNodeByStack(root, 3)));
        System.out.println(TreeNodeLoop.bsf(findKthTreeNodeByRecursion(root, 3)));
    }

    /**
     * 栈的方式实现
     * @param root
     * @param k
     * @return
     */
    private static TreeNode findKthTreeNodeByStack(TreeNode root, int k) {
        if (root == null || k <= 0) {
            return null;
        }
        int count = 0;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            count ++;
            if (count == k) {
                return root;
            }
            root = root.right;
        }
        return null;
    }


    /**
     * 中序的递归实现
     *
     * @param root
     * @param k
     * @return
     */

    static int count = 0;
    private static TreeNode findKthTreeNodeByRecursion(TreeNode root, int k) {
        if (root == null || k <= 0) {
            return null;
        }
        TreeNode node = findKthTreeNodeByRecursion(root.left, k);
        if (node != null) {
            return node;
        }
        count++;
        if (count == k) {
            return root;
        }
        node = findKthTreeNodeByRecursion(root.right, k);
        if (node != null) {
            return node;
        }
        return null;
    }
}
