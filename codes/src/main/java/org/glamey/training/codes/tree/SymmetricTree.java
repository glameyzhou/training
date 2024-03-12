package org.glamey.training.codes.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/symmetric-tree/description/
 * 是否为对称二叉树
 * <p>
 * 1、遍历方式
 * 2、递归方式
 *
 * @author zhouyang281
 * @date 2024-03-12
 */
public class SymmetricTree {


    /**
     * 迭代的方式实现
     *
     * @param root
     * @return
     */
    public static boolean isSymmetricByLoop(TreeNode root) {
        return loopCheck(root, root);
    }

    private static boolean loopCheck(TreeNode t1, TreeNode t2) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(t1);
        queue.offer(t2);
        while (!queue.isEmpty()) {
            t1 = queue.poll();
            t2 = queue.poll();
            if (t1 == null && t2 == null) {
                continue;
            }
            if ((t1 == null || t2 == null) || t1.val != t2.val) {
                return false;
            }
            queue.offer(t1.left);
            queue.offer(t2.right);

            queue.offer(t1.right);
            queue.offer(t2.left);
        }
        return true;
    }


    /**
     * 通过递归的形式判断是否为对称二叉树
     *
     * @param root
     * @return
     */
    public static boolean isSymmetricByRecursion(TreeNode root) {
        return recursionCheck(root, root);
    }

    private static boolean recursionCheck(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.val == b.val && recursionCheck(a.left, b.right) && recursionCheck(a.right, b.left);
    }
}
