package org.glamey.training.algorithm.leetcode.linknode;

/**
 * 链表创建、反转、是否有交点、交点长度
 *
 * @author zhouyang.zhou. 2017.08.23.09.
 */
public class Demo {

  public static void main(String[] args) {
    Node head = create(5);
    printNode(head);

    Node revertHead = revert(head);
    printNode(revertHead);
  }

  private static Node revert(Node head) {
    if(head == null) {
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

  private static void printNode(Node head) {
    System.out.println("-------[print nodes]-------");
    while (head != null) {
      System.out.println(head.v);
      head = head.next;
    }
  }

  private static Node create(int number) {
    Node head = new Node(1);
    Node pNode = head, tmpNode;
    for (int i = 2; i <= number; i++) {
      tmpNode = new Node(i);
      pNode.next = tmpNode;
      pNode = tmpNode;
    }
    return head;
  }
}
