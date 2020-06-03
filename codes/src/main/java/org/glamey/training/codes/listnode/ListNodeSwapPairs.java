package org.glamey.training.codes.listnode;

/**
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * @author yang.zhou 2019.11.22.11
 */
public class ListNodeSwapPairs {

    public static void main(String[] args) {
        ListNode head = ListNodeUtil.create(new int[]{1, 2, 3, 4, 5});
        ListNode swapPairs = swapPairs(head);
        ListNodeUtil.print(swapPairs);
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode a = head, b = head.next;
        a.next = swapPairs(b.next);
        b.next = a;
        return b;
    }
}
