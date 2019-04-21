package org.glamey.training.algorithm.leetcode.listnode;

public class ListNodeFinder {


    /**
     * 找到第index个元素
     *
     * @param node
     * @param index
     * @return
     */
    public static ListNode findByIndex(ListNode node, int index) {
        if (node == null) {
            return null;
        }

        int len = 0;
        ListNode tmp = node;
        while (tmp != null) {
            len++;
            if (index == len) {
                return tmp;
            }
            tmp = tmp.next;
        }

        if (index > len) {
            return null;
        }

        return tmp;
    }


    /**
     * 查看倒数第index个元素
     * <p>
     * 思路：使用步长的方式来解决。
     *
     * @param node
     * @param reverseIndex
     * @return
     */
    public static ListNode findByReverseIndex(ListNode node, int reverseIndex) {
        if (node == null) {
            return null;
        }

        int len = getNodeLen(node);
        if (len < reverseIndex) {
            return null;
        }

        ListNode fast = node;
        ListNode slow = node;
        for (int i = 0; i < reverseIndex; i++) {
            fast = fast.next;
        }

        while (fast != null && slow != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    private static int getNodeLen(ListNode node) {
        if (node == null) {
            return 0;
        }
        int len = 0;
        ListNode tmp = node;
        while (tmp != null) {
            len++;
            tmp = tmp.next;
        }
        return len;
    }
}
