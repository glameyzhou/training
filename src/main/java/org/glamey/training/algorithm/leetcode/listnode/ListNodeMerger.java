package org.glamey.training.algorithm.leetcode.listnode;

/**
 * 合并两个有序的单向链表
 */
public class ListNodeMerger {

    public static ListNode mergeByLoop(ListNode n1, ListNode n2) {
        if (n1 == null) {
            return n2;
        }

        if (n2 == null) {
            return n1;
        }

        ListNode head;
        if (n1.val >= n2.val) {
            head = new ListNode(n2.val);
            n2 = n2.next;
        } else {
            head = new ListNode(n1.val);
            n1 = n1.next;
        }

        ListNode tmp = head;
        ListNode cur;
        while (n1 != null || n2 != null) {
            if (n1 != null && n2 == null) {
                cur = new ListNode(n1.val);
                n1 = n1.next;
            } else if (n1 == null && n2 != null) {
                cur = new ListNode(n2.val);
                n2 = n2.next;
            } else {
                if (n1.val >= n2.val) {
                    cur = new ListNode(n2.val);
                    n2 = n2.next;
                } else {
                    cur = new ListNode(n1.val);
                    n1 = n1.next;
                }
            }
            tmp.next = cur;
            tmp = cur;
        }
        return head;
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
