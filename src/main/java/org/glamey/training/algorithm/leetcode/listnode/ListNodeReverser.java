package org.glamey.training.algorithm.leetcode.listnode;

/**
 * 单向链表反转
 */
public class ListNodeReverser {

    public static ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode p = head, cur = head.next, tmp;
        while (cur != null) {
            tmp = cur.next;
            cur.next = p;
            p = cur;
            cur = tmp;
        }
        head.next = null;
        return p;
    }

}
