package org.glamey.training.algorithm.binarytree;

/**
 * @author zhouyang.zhou, 2017.03.24.06.
 */
public class TreeNode<E> {
  private E data;
  private TreeNode<E> left;
  private TreeNode<E> right;

  public TreeNode(E element) {
    this.data = element;
    this.left = null;
    this.right = null;
  }

  public E getData() {
    return data;
  }

  public void setData(E data) {
    this.data = data;
  }

  public TreeNode<E> getLeft() {
    return left;
  }

  public void setLeft(TreeNode<E> left) {
    this.left = left;
  }

  public TreeNode<E> getRight() {
    return right;
  }

  public void setRight(TreeNode<E> right) {
    this.right = right;
  }
}
