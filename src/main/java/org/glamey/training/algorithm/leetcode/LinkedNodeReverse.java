package org.glamey.training.algorithm.leetcode;

/**
 * 单向链表翻转
 *
 * @author zhouyang.zhou. 2017.07.18.21.
 */
public class LinkedNodeReverse {

    private LinkedNode<Integer> head, tail;

    public LinkedNodeReverse(int[] nums) {

        head = new LinkedNode<>();
        tail = head;
        for (int i = 0; i < nums.length; i++) {
            tail.next = new LinkedNode<>();
            tail.next.value = nums[i];
            tail = tail.next;
        }
        head = head.next;
    }

    public LinkedNode<Integer> reverseByLoop(LinkedNode<Integer> head) {
        if (head == null || head.next == null) {
            return null;
        }
        LinkedNode<Integer> pre = null, next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public LinkedNode<Integer> reverseByRecursion(LinkedNode<Integer> head) {
        if (head == null || head.next == null) {
            return null;
        }
        LinkedNode<Integer> node = reverseByRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    public void show(LinkedNode<Integer> head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        LinkedNodeReverse demo = new LinkedNodeReverse(new int[]{1, 2, 3, 4, 5});
        System.out.println("------show nodes----->");
        LinkedNode<Integer> head = demo.head;
        demo.show(head);

        System.out.println("------show loop nodes----->");
        LinkedNode<Integer> reverseByLoop = demo.reverseByLoop(head);
        demo.show(reverseByLoop);

        System.out.println("------show recursion nodes----->");
        LinkedNode<Integer> reverseByRecursion = demo.reverseByRecursion(head);
        demo.show(reverseByRecursion);
    }

}


