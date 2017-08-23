package org.glamey.training.algorithm.leetcode.linknode;

/**
 * 为了简化，统一使用Integer类型
 *
 * @author zhouyang.zhou. 2017.08.23.09.
 */
public class Node {
  int v;
  Node next;

  public Node(Integer v) {
    this.v = v;
  }

  @Override public String toString() {
    return "Node{" +
        "v=" + v +
        ", next=" + next +
        '}';
  }
}
