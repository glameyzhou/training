package org.glamey.training.codes.listnode;

/**
 * https://leetcode.cn/problems/LGjMqU/description/
 * 重排链表
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0 → L1 → … → Ln-1 → Ln
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 *
 * @author zhouyang281
 * @date 2024-03-04
 */
public class ReorderListNode {

    public static void main(String[] args) {
        ListNode listNode = ListNodeUtil.create(new int[]{1, 2, 3, 4});
        reorderList(listNode);
        ListNodeUtil.print(listNode);
    }

    public static void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = findMiddleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseListNode(l2);
        mergeListNode(l1, l2);
    }

    public static ListNode findMiddleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static ListNode reverseListNode(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            // 3, 4
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public static void mergeListNode(ListNode l1, ListNode l2) {
        // 1,2
        // 4,3
        ListNode l1Tmp, l2Tmp;
        while (l1 != null && l2 != null) {
            l1Tmp = l1.next;
            l2Tmp = l2.next;

            l1.next = l2;
            l1 = l1Tmp;

            l2.next = l1;
            l2 = l2Tmp;
        }
    }
}
