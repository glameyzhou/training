package org.glamey.training.codes.leetcode;

/**
 * 单向连表对象
 *
 * @author zhouyang.zhou. 2017.07.18.21.
 */
@Deprecated
public class LinkedNode<T> {

  T value;
  LinkedNode<T> next;

  public LinkedNode() {
  }

  public LinkedNode(T value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
