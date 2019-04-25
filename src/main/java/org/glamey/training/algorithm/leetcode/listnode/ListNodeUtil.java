package org.glamey.training.algorithm.leetcode.listnode;

/**
 * 单向链表工具类：创建、输出
 */
public class ListNodeUtil {

    public static ListNode create(int[] arrs) {
        if (arrs == null) {
            return null;
        }

        ListNode head = new ListNode(arrs[0]);
        ListNode tmp = head;
        for (int i = 1; i < arrs.length; i++) {
            ListNode curNode = new ListNode(arrs[i]);
            tmp.next = curNode;
            tmp = curNode;
        }
        return head;
    }


    public static void print(ListNode head) {
        ListNode tmp = head;
        while (tmp != null) {
            System.out.println(tmp.val);
            tmp = tmp.next;
        }
    }


    public static int getNodeLength(ListNode head) {
        ListNode tmp = head;
        int len = 0;
        while (tmp != null) {
            tmp = tmp.next;
            len++;
        }
        return len;
    }
}
