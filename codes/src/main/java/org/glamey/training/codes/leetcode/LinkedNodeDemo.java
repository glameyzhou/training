package org.glamey.training.codes.leetcode;

/**
 * 单向链表翻转
 *
 * @author zhouyang.zhou. 2017.07.18.21.
 */
@Deprecated
public class LinkedNodeDemo {

    private LinkedNode<Integer> head, tail;

    public LinkedNodeDemo(int[] nums) {
        generateLinkedNode(nums);
    }

    private void generateLinkedNode(int[] nums) {
    /*head = new LinkedNode<>();
    tail = head;
    for (int i = 0; i < nums.length; i++) {
        tail.next = new LinkedNode<>();
        tail.next.value = nums[i];
        tail = tail.next;
    }
    head = head.next;*/
        int len = nums.length;
        if (len == 0) {
            return;
        }
        head = new LinkedNode<>(nums[0]);
        tail = head;
        for (int i = 1; i < nums.length; i++) {
            tail = (tail.next = new LinkedNode<>(nums[i]));
        }
    }

    public LinkedNode<Integer> reverseByLoop(LinkedNode<Integer> head) {
        if (head == null || head.next == null) {
            return null;
        }
        LinkedNode<Integer> pre = null, next;
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

    public void display(LinkedNode<Integer> head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        LinkedNodeDemo demo = new LinkedNodeDemo(new int[] {1, 2, 3, 4, 5});
        System.out.println("------display nodes----->");
        LinkedNode<Integer> head = demo.head;
        demo.display(head);

        System.out.println("------display loop nodes----->");
        LinkedNode<Integer> reverseByLoop = demo.reverseByLoop(head);
        demo.display(reverseByLoop);

        System.out.println("------display recursion nodes----->");
        LinkedNode<Integer> reverseByRecursion = demo.reverseByRecursion(head);
        demo.display(reverseByRecursion);
    }
}


