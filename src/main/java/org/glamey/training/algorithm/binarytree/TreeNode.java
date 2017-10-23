package org.glamey.training.algorithm.binarytree;

/**
 * @author zhouyang.zhou, 2017.03.24.06.
 */
public class TreeNode<E> {
  E data;
  TreeNode<E> left;
  TreeNode<E> right;

  public TreeNode(E element) {
    this.data = element;
    this.left = null;
    this.right = null;
  }
}
