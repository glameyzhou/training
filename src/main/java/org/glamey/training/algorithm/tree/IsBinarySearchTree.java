package org.glamey.training.algorithm.tree;

/**
 * 是否为二叉搜索树
 * <p>
 * <p>
 * 4
 * 2     3
 * 1
 */
public class IsBinarySearchTree {
    public boolean isFirstCompare;
    public int preNodeVal = Integer.MIN_VALUE;

    public boolean isBinarySearchTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isBinarySearchTree(root.left)
                && compare(root.val)
                && isBinarySearchTree(root.right);
    }

    private boolean compare(int val) {
        if (isFirstCompare) {
            preNodeVal = val;
            isFirstCompare = false;
            return true;
        }

        if (preNodeVal > val) {
            return false;
        }
        preNodeVal = val;
        return true;
    }
}
