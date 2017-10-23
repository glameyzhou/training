package org.glamey.training.algorithm.binarytree;

import java.util.LinkedList;

/**
 * @author zhouyang.zhou. 2017.10.12.15.
 */
public class MaxNode {

  public TreeNode maxNode(TreeNode root) {

    if(root == null) {
      return root;
    }
    TreeNode maxNode = null, tmpNode;
    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
    queue.offer(root);
    int len;
    while (!queue.isEmpty()) {
      len = queue.size();
      for(int i = 0; i < len; i ++) {
        tmpNode = queue.pop();
        maxNode = maxNode == null ? tmpNode : (maxNode.data >= tmpNode.data ? maxNode : tmpNode);

        if (tmpNode.left != null) {
          queue.offer(tmpNode.left);
        }

        if (tmpNode.right != null) {
          queue.offer(tmpNode.right);
        }
      }
    }

    return maxNode;
  }

  class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;
  }
}
