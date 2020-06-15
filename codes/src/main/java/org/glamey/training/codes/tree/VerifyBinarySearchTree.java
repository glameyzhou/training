package org.glamey.training.codes.tree;


import java.util.ArrayList;
import java.util.List;

/**
 * 验证是否为二叉树的后序遍历
 */
public class VerifyBinarySearchTree {

    public static void main(String[] args) {
        TreeNode tree = generate();
        int[] postOrder = getPostOrder(tree);
        boolean verifyBSTPostOrder = verifyBSTPostOrder(postOrder);
        System.out.println(verifyBSTPostOrder);
    }

    private static TreeNode generate() {
        /**
         *        10
         *      8    11
         *    6   9     13
         */
        TreeNode t6 = new TreeNode(6);
        TreeNode t9 = new TreeNode(9);
        TreeNode t8 = new TreeNode(8);
        t8.left = t6;
        t8.right = t9;

        TreeNode t13 = new TreeNode(13);
        TreeNode t11 = new TreeNode(11);
        t11.right = t13;

        TreeNode root = new TreeNode(10);
        root.left = t8;
        root.right = t11;
        return root;
    }

    private static int[] getPostOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        getPostOrderByRecursion(root, list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void getPostOrderByRecursion(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        getPostOrderByRecursion(root.left, list);
        getPostOrderByRecursion(root.right, list);
        list.add(root.val);
    }

    private static boolean verifyBSTPostOrder(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        return process(nums, 0, nums.length - 1);
    }

    private static boolean process(int[] nums, int start, int end) {
        if (start >= end) {
            return true;
        }
        int rootVal = nums[end];
        //左树
        int i = start;
        while (nums[i] < rootVal) {
            i++;
        }

        //右树
        int j = end - 1;
        while (nums[j] > rootVal) {
            j++;
        }
        return process(nums, start, i - 1) && process(nums, j, end - 1);

    }
}
