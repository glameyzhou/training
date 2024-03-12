package org.glamey.training.demo.easy;


import org.glamey.training.demo.ListNode;
import org.glamey.training.demo.ListNodeUtils;

/**
 * https://leetcode.cn/problems/merge-two-sorted-lists/
 * <p>
 * 合并两个有序递增的链表
 *
 * @author yang.zhou 2020.01.30.11
 */
public class MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode l1 = ListNodeUtils.create(new int[]{1, 3});
        ListNodeUtils.print(l1);
        ListNode l2 = ListNodeUtils.create(new int[]{2, 5, 7});
        ListNodeUtils.print(l2);

        ListNode mergeTwoLists = mergeTwoLists(l1, l2);
        ListNodeUtils.print(mergeTwoLists);

        ListNodeUtils.print(mergeTwoListByRecursion(l1, l2));

    }

    /**
     * 通过递归的方式来实现。逐步把问题拆解成最小单位
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoListByRecursion(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoListByRecursion(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListByRecursion(l1, l2.next);
            return l2;
        }
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
