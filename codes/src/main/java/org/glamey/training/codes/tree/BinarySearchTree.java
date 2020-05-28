package org.glamey.training.codes.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 9
 * 5      20
 * 15  30
 *
 * @author yang.zhou 2020.01.17.15
 */
public class BinarySearchTree {

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        IntStream.of(9, 5, 20, 15, 30).forEach(binarySearchTree::insert);
        System.out.println(binarySearchTree.toString());

        System.out.println("---");
        TreeNode search = binarySearchTree.search(20);
        System.out.println(binarySearchTree.toString(search));
    }


    private static TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    /**
     * search
     *
     * @param key
     * @return
     */
    public TreeNode search(int key) {
        TreeNode current = root;
        while (current != null && current.val != key) {
            if (key < current.val) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current;
    }

    /**
     * insert
     *
     * @param key
     * @return
     */
    public TreeNode insert(int key) {
        TreeNode node = new TreeNode(key), current = root, parent;
        if (current == null) {
            root = node;
            return node;
        }

        while (true) {
            parent = current;
            if (key < current.val) {
                current = current.left;
                if (current == null) {
                    parent.left = node;
                    return node;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = node;
                    return node;
                }
            }
        }
    }

    @Override
    public String toString() {
        return toString(root);
    }

    /**
     * bst 广度优先遍历
     *
     * @param node
     * @return
     */
    public String toString(TreeNode node) {
        if (node == null) {
            return "";
        }
        List<String> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = queue.poll();
                builder.append(n.val).append(",");
                if (n.left != null) queue.offer(n.left);
                if (n.right != null) queue.offer(n.right);
            }
            list.add(builder.deleteCharAt(builder.length() - 1).toString());
        }
        return list.stream().collect(Collectors.joining(System.getProperty("line.separator")));
    }
}
