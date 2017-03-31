package org.glamey.training.algorithm.binarytree;

import com.google.common.collect.Lists;
import java.security.Signature;
import java.util.List;

/**
 * @author zhouyang.zhou, 2017.03.24.06.
 */
public class TreeDemo {

  private TreeNode<Integer> root;
  private List<TreeNode<Integer>> nodes;

  public TreeNode<Integer> getRoot() {
    return root;
  }

  public void createTreeNode(int[] arrs) {
    this.nodes = Lists.newArrayList();
    for (int arr : arrs) {
      nodes.add(new TreeNode<>(arr));
    }
    for (int j = 0; j < nodes.size() / 2; j++) {
      //left
      nodes.get(j).setLeft(nodes.get(j * 2 + 1));
      //right
      nodes.get(j).setRight(nodes.get(j * 2 + 2));
    }

    this.root = nodes.get(0);
  }

  public void preOrder(TreeNode<Integer> node) {
    if(node == null) {
      return;
    }
    visit(node.getData());
    preOrder(node.getLeft());
    preOrder(node.getRight());
  }

  public void inOrder(TreeNode<Integer> node) {
    if(node == null) {
      return;
    }

    inOrder(node.getLeft());
    visit(node.getData());
    inOrder(node.getRight());
  }

  public void postOrder(TreeNode<Integer> node) {
    if(node == null) {
      return;
    }

    postOrder(node.getLeft());
    postOrder(node.getRight());
    visit(node.getData());
  }

  public int size() {
    return size(this.root);
  }

  private int size(TreeNode<Integer> node) {
    if(node == null) {
      return 0;
    }
    return 1 + size(node.getLeft()) + size(node.getRight());
  }

  public int height() {
    return height(this.root);
  }

  private int height(TreeNode<Integer> node) {
    if(node == null) {
      return 0;
    }

    int leftHeight = height(node.getLeft());
    int rightHeight = height(node.getRight());
    return leftHeight >= rightHeight ? leftHeight + 1 : rightHeight + 1;
  }

  private void visit(Integer data) {
    System.out.println(data);
  }

  /**
   *      1
   *    2   3
   *  4  5 6 7
   * preOrder 1,2,3,4,5,6,7
   * inOrder  4,2,5,1,3,7
   * postOrder 4,5,2,6,7,3,1
   * @param args
   */
  public static void main(String[] args) {
    TreeDemo demo = new TreeDemo();
    int[] arrs = {1, 2, 3, 4, 5, 6, 7};
    demo.createTreeNode(arrs);

    TreeNode<Integer> root = demo.getRoot();
    System.out.println(root);

    System.out.println("pre order.......");
    demo.preOrder(root);


    System.out.println("in order.......");
    demo.inOrder(root);

    System.out.println("post order.......");
    demo.postOrder(root);

    System.out.println("------------------>size=" + demo.size());
    System.out.println("------------------>height=" + demo.height());
  }
}
