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

    public static boolean eq(ListNode n1, ListNode n2) {
        int len1 = getNodeLength(n1), len2 = getNodeLength(n2);
        if (len1 == len2 && len1 == 0) {
            return true;
        }

        if (len1 != len2) {
            return false;
        }

        while (n1 != null) {
            if (n1.val != n2.val) {
                return false;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        return true;
    }
}
