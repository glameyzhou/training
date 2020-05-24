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
    public TreeNode getSmallestCommonParentNode(TreeNode root, TreeNode rootA, TreeNode rootB) {

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

    private void processTreeByDfs(TreeNode root, Map<Integer, TreeNode> parentMap) {
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
