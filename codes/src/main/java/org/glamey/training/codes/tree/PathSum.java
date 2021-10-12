package org.glamey.training.codes.tree;

import java.util.LinkedList;

/**
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \      \
 * 7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum
 */
public class PathSum {

    /**
     * 递归形式
     * 时间复杂度：O(n)
     * 空间复杂度：O(height) 递归时候的栈深
     */
    public static boolean hasPathSumByRecursion(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if (root.val == sum && root.left == null && root.right == null) {
            return true;
        }
        return hasPathSumByRecursion(root.left, sum - root.left.val)
                || hasPathSumByRecursion(root.right, sum - root.right.val);
    }


    /**
     * 时间复杂度：和递归方法相同是 O(N)O(N)。
     * 空间复杂度：当树不平衡的最坏情况下是 O(N)O(N) 。在最好情况（树是平衡的）下是 O(log N)
     */
    public static boolean hasPathSumByLoop(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        LinkedList<Integer> stackSum = new LinkedList<>();
        stackSum.add(sum - root.val);

        TreeNode node;
        int curSum;
        while (!queue.isEmpty()) {
            node = queue.pollLast();
            curSum = stackSum.pollLast();

            if ((node.right == null) && (node.left == null) && (curSum == 0)) {
                return true;
            }

            if (node.right != null) {
                queue.add(node.right);
                stackSum.add(curSum - node.right.val);
            }

            if (node.left != null) {
                queue.add(node.left);
                stackSum.add(curSum - node.left.val);
            }
        }
        return false;
    }
}
