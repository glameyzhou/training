package org.glamey.training.algorithm.tree;

import java.util.*;


/**
 * 二叉树任意两个节点的最小公共父节点
 * 限制：
 * 1、每个节点值均不同。
 * 2、两个节点均存在于父树中
 */
public class SmallestCommonParentNode {


    /**
     *       1
     *     2   3
     *   4   9  7
     *  5 6   10
     *
     *
     * @param args
     */

    public static void main(String[] args) {
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t4 = new TreeNode(4);
        t4.left = t5;
        t4.right = t6;



        TreeNode t9 = new TreeNode(9);
        TreeNode t10 = new TreeNode(10);
        t9.right = t10;

        TreeNode t7 = new TreeNode(7);
        TreeNode t3 = new TreeNode(3);
        t3.left = t9;
        t3.right = t7;

        TreeNode t2 = new TreeNode(2);
        TreeNode root = new TreeNode(1);
        root.left = t2;
        root.right = t3;

        TreeNode commonParentNode = getSmallestCommonParentNode(root, t4, t9);
        System.out.println(commonParentNode);


    }



    /**
     * 思路：
     * 1、遍历整个二叉树，将所有节点的值、对应的父节点保存在map中。
     * 2、a依次往上找，一直到父节点为空，并且保存p上面所有的节点值到set中。
     * 3、b依次网上找，如果在set中碰到，说明有公共父节点，从map中返回即可。
     *
     * @param root
     * @param rootA
     * @param rootB
     * @return
     */
    public static TreeNode getSmallestCommonParentNode(TreeNode root, TreeNode rootA, TreeNode rootB) {

        if (Objects.isNull(root) || Objects.isNull(rootA) || Objects.isNull(rootB)) {
            return null;
        }

        //存储当前节点值与父节点对象
        Map<Integer, TreeNode> parentMap = new HashMap<>();
        processTreeByDfs(root, parentMap);

        //已经访问过的节点值集合
        Set<Integer> accessed = new HashSet<>();
        while (rootA != null) {
            accessed.add(rootA.val);
            rootA = parentMap.get(rootA.val);
        }
        while (rootB != null) {
            if (accessed.contains(rootB.val)) {
                return rootB;
            }
            rootB = parentMap.get(rootB.val);
        }

        return null;
    }

    private static void processTreeByDfs(TreeNode root, Map<Integer, TreeNode> parentMap) {
        if (root.left != null) {
            parentMap.put(root.left.val, root);
            processTreeByDfs(root.left, parentMap);
        }
        if (root.right != null) {
            parentMap.put(root.right.val, root);
            processTreeByDfs(root.right, parentMap);
        }
    }
}
