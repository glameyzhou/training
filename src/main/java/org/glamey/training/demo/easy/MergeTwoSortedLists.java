package org.glamey.training.demo.easy;


import org.glamey.training.demo.ListNode;
import org.glamey.training.demo.ListNodeUtils;

/**
 * @author yang.zhou 2020.01.30.11
 */
public class MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode l1 = ListNodeUtils.create(new int[]{1, 2, 4});
        ListNodeUtils.print(l1);
        ListNode l2 = ListNodeUtils.create(new int[]{1, 3, 4});
        ListNodeUtils.print(l2);

        ListNode mergeTwoLists = mergeTwoLists(l1, l2);
        ListNodeUtils.print(mergeTwoLists);

    }


    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1), tmp = dummy;
        while (l1 != null && l2 != null) {
            int val;
            if (l1.val <= l2.val) {
                val = l1.val;
                l1 = l1.next;
            } else {
                val = l2.val;
                l2 = l2.next;
            }
            ListNode node = new ListNode(val);
            tmp.next = node;
            tmp = node;
        }
        if (l1 != null) {
            tmp.next = l1;
        }

        if (l2 != null) {
            tmp.next = l2;
        }
        return dummy.next;
    }
}
