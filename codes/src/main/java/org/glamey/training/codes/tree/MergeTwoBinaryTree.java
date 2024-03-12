package org.glamey.training.codes.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * <p>
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为NULL 的节点将直接作为新二叉树的节点。
 * <p>
 * 示例1:
 * <p>
 * 输入:
 * Tree 1                     Tree 2
 * 1                         2
 * / \                       / \
 * 3   2                     1   3
 * /                           \   \
 * 5                             4   7
 * 输出:
 * 合并后的树:
 * 3
 * / \
 * 4   5
 * / \   \
 * 5   4   7
 * 注意:合并必须从两个树的根节点开始。
 * <p>
 * 通过次数48,260提交次数63,452
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-binary-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeTwoBinaryTree {


    /**
     * 递归方式
     * 以t1为基准进行递归
     */
    private static TreeNode mergeTreesByRecursion(TreeNode t1, TreeNode t2) {
        if (t1 == null || t2 == null) {
            return t1 == null ? t2 : t1;
        }
        t1.val += t2.val;
        t1.left = mergeTreesByRecursion(t1.left, t2.left);
        t2.right = mergeTreesByRecursion(t1.right, t2.right);
        return t1;
    }

    /**
     * 广度遍历
     * 以t1为基准：通过队列实现：压入t1 t2
     * 弹出t1 t2，计算 t1.val += t2.val
     * 如果t1 t2 left均不为空，压入队列，如果t1.left == null，赋值为t2.left
     * 同理查看t1 t2的right
     */
    private static TreeNode mergeTreesByBfs(TreeNode t1, TreeNode t2) {
        if (t1 == null || t2 == null) {
            return t1 == null ? t2 : t1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(t1);
        queue.offer(t2);
        while (!queue.isEmpty()) {
            TreeNode n1 = queue.poll();
            TreeNode n2 = queue.poll();
            n1.val += n2.val;
            if (n1.left != null && n2.left != null) {
                queue.offer(n1.left);
                queue.offer(n2.left);
            } else if (n1.left == null) {
                n1.left = n2.left;
            }

            if (n1.right != null && n2.right != null) {
                queue.offer(n1.right);
                queue.offer(n2.right);
            } else if (n1.right == null) {
                n1.right = n2.right;
            }
        }
        return t1;
    }
}
