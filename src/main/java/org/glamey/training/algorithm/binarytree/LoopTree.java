package org.glamey.training.algorithm.binarytree;

import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * https://www.jianshu.com/p/456af5480cee
 *
 * @author yang.zhou 2019.11.26.10
 */
public class LoopTree {

    public static void main(String[] args) {
        int hash = 0x61c88647;
        System.out.println(hash);
        BinaryNode<Integer> tree = BinaryNodeHelper.build();
        System.out.println(bfs(tree));

        System.out.println("\r\n-----[pre order]-----");
        preOrder(tree);
        System.out.println();
        preOrderStack(tree);

        System.out.println("\r\n-----[in order]-----");
        inOrder(tree);
        System.out.println();
        inOrderStack(tree);

        System.out.println("\r\n-----[post order]-----");
        postOrder(tree);
        System.out.println();
        postOrderStack(tree);
//
    }

    public static List<List<Integer>> bfs(BinaryNode<Integer> tree) {
        List<List<Integer>> list = Lists.newArrayList();
        if (tree == null) {
            return list;
        }

        Queue<BinaryNode<Integer>> queue = new LinkedList<>();
        queue.offer(tree);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subList = Lists.newArrayList();
            for (int i = 0; i < size; i++) {
                BinaryNode<Integer> node = queue.poll();
                subList.add(node.element);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(subList);
        }
        return list;
    }

    /**
     * 中序顺序：根->左->右
     *
     * @param tree
     */
    public static void preOrder(BinaryNode<Integer> tree) {
        if (tree == null) {
            return;
        }
        System.out.print(tree.element + " ");
        preOrder(tree.left);
        preOrder(tree.right);
    }


    public static void preOrderStack(BinaryNode<Integer> tree) {
        Stack<BinaryNode<Integer>> stack = new Stack<>();
        BinaryNode node = tree;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                System.out.print(node.element + " ");
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                BinaryNode<Integer> pop = stack.pop();
                node = pop.right;
            }
        }
    }

    /**
     * 中序顺序：左->根->右
     *
     * @param tree
     */
    public static void inOrder(BinaryNode<Integer> tree) {
        if (tree == null) {
            return;
        }
        inOrder(tree.left);
        System.out.print(tree.element + " ");
        inOrder(tree.right);
    }

    public static void inOrderStack(BinaryNode<Integer> tree) {
        Stack<BinaryNode<Integer>> stack = new Stack<>();
        BinaryNode<Integer> node = tree;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                BinaryNode<Integer> pop = stack.pop();
                System.out.print(pop.element + " ");
                node = pop.right;
            }
        }
    }

    /**
     * 中序顺序：左->右->根
     *
     * @param tree
     */
    public static void postOrder(BinaryNode<Integer> tree) {
        if (tree == null) {
            return;
        }
        postOrder(tree.left);
        postOrder(tree.right);
        System.out.print(tree.element + " ");
    }

    public static void postOrderStack(BinaryNode<Integer> tree) {
        Stack<BinaryNode<Integer>> stack = new Stack<>();
        BinaryNode<Integer> node = tree;
        BinaryNode<Integer> lastVisit = tree;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.peek();
            if (node.right == null || node.right == lastVisit) {
                System.out.print(node.element + " ");
                stack.pop();
                lastVisit = node;
                node = null;
            } else {
                node = node.right;
            }

        }
    }
}
