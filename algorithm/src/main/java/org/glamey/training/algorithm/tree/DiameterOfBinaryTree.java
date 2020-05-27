package org.glamey.training.algorithm.tree;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 *  
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 *  
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 *
 * 通过次数48,216提交次数96,540
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
 */
public class DiameterOfBinaryTree {

    public static void main(String[] args) {
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t2 = new TreeNode(2);
        t2.left = t4;
        t2.right = t5;

        TreeNode t1 = new TreeNode(1);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;

        diameterOfBinaryTree(t1);

        System.out.println(maxDiameter);

    }


    private static int maxDiameter = 0;

    /**
     * 时间复杂度：O(n) 遍历了整个二叉树
     * 空间复杂度：O(height)，height=树高度
     * 由于递归函数在递归过程中需要为每一层递归函数分配栈空间，所以这里需要额外的空间且该空间取决于递归的深度，
     * 而递归的深度显然为二叉树的高度，并且每次递归调用的函数里又只用了常数个变量，所以所需空间复杂度为 O(Height)。
     *
     *             1
     *            / \
     *           2   3
     *          / \
     *         4   5
     * @param root
     * @return
     */
    public static int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return maxDiameter - 1;
    }

    private static int dfs(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = dfs(root.left);
        int rightDepth = dfs(root.right);
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
