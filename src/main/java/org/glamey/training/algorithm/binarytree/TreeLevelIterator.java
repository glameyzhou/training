package org.glamey.training.algorithm.binarytree;

import com.google.common.collect.Lists;

import java.util.LinkedList;

/**
 *
 * 二叉树广度优先遍历
 *
 *            0
 *       1          2
 *
 *    3    4            5
 *  6        7       8
 *        9
 *
 * @author zhouyang.zhou. 2017.10.11.16.
 */
public class TreeLevelIterator {

  public static void main(String[] args) {
    //TreeNode<Integer> root = create();
    //processIterator(root);
    System.out.println(addDigits(38));
    System.out.println(addDigits(10));
    System.out.println(addDigits(10));

  }

  public static int addDigits(int num) {
    if(num < 0) {
      throw new IllegalArgumentException("args is illedgal," + num);
    }

    int result, tmp = 0;
    do {
      String source = String.valueOf(num);
      char[] chars = source.toCharArray();
      for (char c : chars) {
        tmp += Integer.parseInt(String.valueOf(c));
      }
      num = tmp;
      result = tmp;
      tmp = 0;
    } while (result >= 10);

    return result;
  }

  private static void processIterator(TreeNode<Integer> root) {
    StringBuilder builder = new StringBuilder();
    TreeNode<Integer> tmpNode;
    LinkedList<TreeNode<Integer>> queue = Lists.newLinkedList();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int len = queue.size();
      for (int i = 0; i < len; i++) {
        tmpNode = queue.pop();
        builder.append(tmpNode.data).append(",");

        if(tmpNode.left != null) {
          queue.offer(tmpNode.left);
        }

        if(tmpNode.right != null) {
          queue.offer(tmpNode.right);
        }
      }
      builder.deleteCharAt(builder.length() - 1);
      builder.append("\n");
      System.out.println(builder.toString());
      builder.setLength(0);
    }
  }

  private static TreeNode<Integer> create() {
    TreeNode<Integer> root = new TreeNode<>(0);
    TreeNode<Integer> node1 = new TreeNode<>(1);
    TreeNode<Integer> node2 = new TreeNode<>(2);
    TreeNode<Integer> node3 = new TreeNode<>(3);
    TreeNode<Integer> node4 = new TreeNode<>(4);
    TreeNode<Integer> node5 = new TreeNode<>(5);
    TreeNode<Integer> node6 = new TreeNode<>(6);
    TreeNode<Integer> node7 = new TreeNode<>(7);
    TreeNode<Integer> node8 = new TreeNode<>(8);
    TreeNode<Integer> node9 = new TreeNode<>(9);

    node7.left = node9;
    node3.left = node6;
    node4.right = node7;
    node5.left = node8;
    node1.left = node3;
    node1.right = node4;
    node2.right = node5;
    root.left = node1;
    root.right = node2;

    return root;
  }
}
