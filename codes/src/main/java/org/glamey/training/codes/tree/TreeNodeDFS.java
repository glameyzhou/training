package org.glamey.training.codes.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 前序遍历：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 * 中序遍历：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 * 后序遍历：https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 */
public class TreeNodeDFS {

    /**
     * 中序遍历-栈
     */
    public List<Integer> inOrderByStack(TreeNode root) {
        LinkedList<Integer> ret = new LinkedList<>();
        if (root == null) {
            return ret;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        while (curNode != null || !stack.isEmpty()) {
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            curNode = stack.pop();
            ret.add(curNode.val);
            curNode = curNode.right;
        }
        return ret;
    }

    /**
     * 中序遍历-递归
     */
    public List<Integer> inOrderByRecursion(TreeNode node) {
        LinkedList<Integer> ret = new LinkedList<>();
        doInOrderByRecursion(node, ret);
        return ret;
    }

    private void doInOrderByRecursion(TreeNode root, LinkedList<Integer> ret) {
        if (root == null) {
            return;
        }
        doInOrderByRecursion(root.left, ret);
        ret.add(root.val);
        doInOrderByRecursion(root.right, ret);
    }


    /**
     * 前序遍历-栈
     * <p>
     * 先把
     */
    public List<Integer> preOderByStack(TreeNode root) {
        LinkedList<Integer> ret = new LinkedList<>();
        if (root == null) {
            return ret;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            ret.add(node.val);

            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return ret;
    }

    /**
     * 前序遍历-递归
     */
    public List<Integer> preOrderByRecursion(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        doPreOrderByRecursion(root, list);
        return list;
    }

    private void doPreOrderByRecursion(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        doPreOrderByRecursion(root.left, list);
        doPreOrderByRecursion(root.right, list);
    }


    /**
     * 层次广度遍历
     */
    public static List<Integer> bsf(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                list.add(poll.val);

                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
        return list;
    }
}
