package org.glamey.training.algorithm.leetcode.listnode;

/**
 * 合并两个有序的单向链表
 */
public class ListNodeMerger {

    public static ListNode mergeByLoopV2(ListNode n1, ListNode n2) {
        ListNode dummy = new ListNode(0), p = dummy;
        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                p.next = n1;
                n1 = n1.next;
            } else {
                p.next = n2;
                n2 = n2.next;
            }
            p = p.next;
        }

        if (n1 != null) p.next = n1;
        if (n2 != null) p.next = n2;

        return dummy.next;
    }


    public static ListNode mergeByLoop(ListNode n1, ListNode n2) {
        ListNode dummy = new ListNode(0), p = dummy, cur;

        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                cur = new ListNode(n1.val);
                n1 = n1.next;
            } else {
                cur = new ListNode(n2.val);
                n2 = n2.next;
            }
            p.next = cur;
            p = cur;
        }
        if (n1 != null) p.next = n1;
        if (n2 != null) p.next = n2;

        return dummy.next;
    }

    public static ListNode mergeByRecursive(ListNode n1, ListNode n2) {
        if (n1 == null) {
            return n2;
        }

        if (n2 == null) {
            return n1;
        }

        ListNode head;
        if (n1.val <= n2.val) {
            head = n1;
            head.next = mergeByRecursive(n1.next, n2);
        } else {
            head = n2;
            head.next = mergeByRecursive(n1, n2.next);
        }
        return head;
    }
}