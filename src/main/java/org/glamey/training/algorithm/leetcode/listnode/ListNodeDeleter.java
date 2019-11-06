package org.glamey.training.algorithm.leetcode.listnode;

public class ListNodeDeleter {

    public static ListNode deleteFirstPresent(ListNode node, int value) {
        if (node == null) {
            return null;
        }

        //头节点就是待删除的数据
        if (node.val == value) {
            return node.next;
        }

        //因为删除节点需要知道上一个node，因此我们判断下个节点是否为指定值即可。
        ListNode tmp = node;
        while (tmp.next != null && tmp.next.val != value) {
            tmp = tmp.next;
        }
        //tmp.next有可能为空，需要判断
        while (tmp.next != null && tmp.next.val == value) {
            tmp.next = tmp.next.next;
        }
        return node;
    }


    public static ListNode deleteAllPresent(ListNode node, int value) {
        if (node == null) {
            return null;
        }

        ListNode tmp = node;

        //头节点就是待删除的数据
        if (tmp.val == value) {
            node = tmp.next;
        }

        //删除链表中的value值，无论出现多少次
        while (tmp.next != null) {
            if (tmp.next.val == value) {
                tmp.next = tmp.next.next;
            } else {
                tmp = tmp.next;
            }

        }
        return node;
    }


    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/submissions/
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        if (n <= 0) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy, q = dummy;

        for (int i = 0; i <= n; i++) {
            p = p.next;
        }

        while (p != null) {
            p = p.next;
            q = q.next;
        }

        q.next = q.next.next;
        return dummy.next;
    }
}
