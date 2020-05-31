package org.glamey.training.codes.tree;


/**
 * 根据前序遍历和中序遍历，组建二叉树
 */
public class ReBuildTreeNode {

    public static void main(String[] args) {
        int[] preOrder = new int[]{1, 2, 4, 7, 3, 5, 6, 8};
        int[] inOrder = new int[]{4, 7, 2, 1, 5, 3, 8, 6};

        preOrder = new int[]{1, 2, 3, 4, 5, 6, 7};
        inOrder = new int[]{3, 2, 4, 1, 6, 5, 7};

        TreeNode root = rebuildTreeNode(preOrder, 0, preOrder.length - 1,
                inOrder, 0, inOrder.length - 1);

        System.out.println(TreeNodeBFS.bsf(root));
    }

    private static TreeNode rebuildTreeNode(int[] preOrder, int preL, int preR,
                                            int[] inOrder, int inL, int inR) {
        if (preL > preR || inL > inR || preOrder.length != inOrder.length) {
            return null;
        }

        int rootVal = preOrder[preL];
        TreeNode root = new TreeNode(rootVal);
        int pos = findValIndex(inOrder, inL, inR, rootVal);


        root.left = rebuildTreeNode(preOrder, preL + 1, preL + pos - inL,
                inOrder, inL, pos - 1);
        root.right = rebuildTreeNode(preOrder, preL + pos - inL + 1, preR,
                inOrder, pos + 1, inR);

        return root;
    }

    private static int findValIndex(int[] nums, int left, int right, int key) {
        if (left > right) {
            return -1;
        }
        for (int i = left; i <= right; i++) {
            if (nums[i] == key) {
                return i;
            }
        }
        return -1;
    }


}
