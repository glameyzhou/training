package org.glamey.training.algorithm.leetcode.linknode;

/**
 * 链表创建、反转、是否存在环，环的长度
 *
 * @author zhouyang.zhou. 2017.08.23.09.
 */
public class LinkNode {

  public static void main(String[] args) {
    LinkNode demo = new LinkNode();
    demo.test();
  }

  private void test() {
    create(5);
    printNode(head);

    add(6);
    add(3);
    add(7);
    printNode(head);

    boolean cyclic = hasCyclic();
    //Node revert = revert();
    //printNode(revert);
  }

  private boolean hasCyclic() {

    return false;
  }

  private Node head;
  private int len;

  private Node revert() {
    if (head == null) {
      return head;
    }

    Node pNode = head, curNode = head.next, tmpNode;
    while (curNode != null) {
      tmpNode = curNode.next;
      curNode.next = pNode;
      pNode = curNode;
      curNode = tmpNode;
    }
    head.next = null;
    return pNode;
  }

  private void printNode(Node head) {
    System.out.printf("-------[print nodes(size = %d)]-------\n", len());
    while (head != null) {
      System.out.println(head.v);
      head = head.next;
    }
  }

  private int len() {
    return len;
  }

  private Node create(int number) {
    head = new Node(1);
    ++len;
    Node pNode = head, tmpNode;
    for (int i = 2; i <= number; i++) {
      tmpNode = new Node(i);
      pNode.next = tmpNode;
      pNode = tmpNode;
      ++len;
    }
    return head;
  }

  private void create(int[] numbers) {
    head = new Node(numbers[0]);
    ++len;
    Node pNode = head, tmpNode;
    for (int i = 1; i < numbers.length; i++) {
      tmpNode = new Node(numbers[i]);
      pNode.next = tmpNode;
      pNode = tmpNode;
      ++len;
    }
  }

  private boolean add(Integer val) {
    if (head == null) {
      head = new Node(val);
      ++len;
      return true;
    }

    Node curNode = new Node(val);
    if (head.next == null) {
      head.next = curNode;
      ++len;
      return true;
    }

    Node pNode = head;
    while (pNode.next != null) {
      pNode = pNode.next;
    }
    pNode.next = curNode;
    ++len;
    return true;
  }
}
