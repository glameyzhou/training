package org.glamey.training.algorithm.leetcode;

import org.glamey.training.algorithm.leetcode.listnode.ListNode;
import org.glamey.training.algorithm.leetcode.listnode.ListNodeUtil;

/**
 * 求 两个单向链表的交集
 */
public class IntersectionOfTwoLinkedLists {

    public static void main(String[] args) {
        ListNode head1 = ListNodeUtil.create(new int[]{1, 2, 3, 4, 5, 6});
        ListNode head2 = ListNodeUtil.create(new int[]{1, 3, 4, 6, 7});

        ListNode intersection = intersection(head1, head2);
        ListNodeUtil.print(intersection);
    }

    private static ListNode intersection(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        ListNode dumpy = new ListNode(-1), tmp = dumpy;
        while (head1 != null && head2 != null) {
            if (head1.val == head2.val) {
                ListNode node = new ListNode(head1.val);
                head1 = head1.next;
                head2 = head2.next;
                tmp.next = node;
                tmp = node;
            } else if (head1.val > head2.val) {
                head2 = head2.next;
            } else {
                head1 = head1.next;
            }
        }
        return dumpy.next;
    }
}
