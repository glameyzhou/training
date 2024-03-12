package org.glamey.training.codes.tree;


/**
 * 给定两个非空二叉树 s 和 t，检验s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 * <p>
 * 示例 1:
 * 给定的树 s:
 * <p>
 * 3
 * / \
 * 4   5
 * / \
 * 1   2
 * 给定的树 t：
 * <p>
 * 4
 * / \
 * 1   2
 * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 * <p>
 * 示例 2:
 * 给定的树 s：
 * <p>
 * 3
 * / \
 * 4   5
 * / \
 * 1   2
 * /
 * 0
 * 给定的树 t：
 * <p>
 * 4
 * / \
 * 1   2
 * 返回 false。
 * <p>
 * 通过次数40,442提交次数86,515
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsSubTree {

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

        System.out.println(isSubTree(root, t20));
        System.out.println(isSubTree(root, t7));
        System.out.println(isSubTree(root, t9));
        System.out.println(isSubTree(root, null));
        System.out.println(isSubTree(null, t9));
        System.out.println(isSubTree(null, null));
    }


    public static boolean isSubTree(TreeNode root, TreeNode subTree) {
        if (root == null || subTree == null) {
            return false;
        }
        return isSubTreeProcess(root, subTree) || isSubTree(root.left, subTree) || isSubTree(root.right, subTree);
    }

    /**
     * 查看两棵树的各个节点是否一致
     */
    private static boolean isSubTreeProcess(TreeNode root, TreeNode subTree) {
        if (root == null || subTree == null) {
            return true;
        }
        if (root == null || subTree == null) {
            return false;
        }
        if (root.val != subTree.val) {
            return false;
        }
        return isSubTreeProcess(root.left, subTree.left) && isSubTreeProcess(root.right, subTree.right);
    }
}
