package org.glamey.training.codes.listnode;

/**
 * 单向链表排序。
 * 时间复杂度O(NlogN)
 */
public class SortListNode {

    public static void main(String[] args) {
        ListNode head = ListNodeUtil.create(new int[]{2, 9, 10, 6, 30, 8});
        ListNode sorted = sort(head);
        ListNodeUtil.print(sorted);

    }

    private static ListNode sort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode middleNode = findMiddleNode(head);
        ListNode rightNode = sort(middleNode.next);
        middleNode.next = null;
        ListNode leftNode = sort(head);
        return merge(leftNode, rightNode);
    }

    private static ListNode merge(ListNode leftNode, ListNode rightNode) {
        if (leftNode == null) {
            return rightNode;
        }
        if (rightNode == null) {
            return leftNode;
        }
        ListNode dumpy = new ListNode(-1), tmp = dumpy;
        while (leftNode != null && rightNode != null) {
            ListNode node;
            if (leftNode.val <= rightNode.val) {
                node = new ListNode(leftNode.val);
                leftNode = leftNode.next;
            } else {
                node = new ListNode(rightNode.val);
                rightNode = rightNode.next;
            }
            tmp.next = node;
            tmp = node;
        }
        if (leftNode != null) {
            tmp.next = leftNode;
        }
        if (rightNode != null) {
            tmp.next = rightNode;
        }
        return dumpy.next;
    }

    private static ListNode findMiddleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
