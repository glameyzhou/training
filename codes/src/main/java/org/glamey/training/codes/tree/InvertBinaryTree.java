package org.glamey.training.codes.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 翻转一棵二叉树。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * <p>
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * 输出：
 * <p>
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class InvertBinaryTree {


    public static void main(String[] args) {
        /**
         *      3
         *   9     20
         * 15          7
         *
         */
        TreeNode t15 = new TreeNode(15);
        TreeNode t7 = new TreeNode(7);
        TreeNode t9 = new TreeNode(9);
        TreeNode t20 = new TreeNode(20);
        TreeNode root = new TreeNode(3);
        t9.left = t15;
        t20.right = t7;
        root.left = t9;
        root.right = t20;

        System.out.println(TreeNodeBFS.bsf(root)); //输出结果：原状
        invertTreeNodByRecursion(root);
        System.out.println(TreeNodeBFS.bsf(root)); //输出结果：镜像

        System.out.println(TreeNodeBFS.bsf(invertTreeByRecursion(root))); //输出结果：原状
        System.out.println(TreeNodeBFS.bsf(invertByBfs(root))); //输出结果：镜像


    }

    /**
     * 在原树的基础上面修改
     */
    public static void invertTreeNodByRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        if (root.left != null) {
            invertTreeNodByRecursion(root.left);
        }
        if (root.right != null) {
            invertTreeNodByRecursion(root.right);
        }
    }


    /**
     * 递归遍历（生成新树）
     */
    public static TreeNode invertTreeByRecursion(TreeNode tree) {
        if (tree == null) {
            return null;
        }
        TreeNode left = invertTreeByRecursion(tree.left);
        TreeNode right = invertTreeByRecursion(tree.right);
        tree.left = right;
        tree.right = left;
        return tree;
    }

    /**
     * 广度遍历
     * <p>
     * 借助队列压入节点。
     * 后续对每次弹出的节点的左右孩子进行交换。
     */
    private static TreeNode invertByBfs(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }
}
