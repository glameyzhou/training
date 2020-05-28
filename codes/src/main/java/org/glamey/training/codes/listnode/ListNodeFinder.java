package org.glamey.training.codes.listnode;

public class ListNodeFinder {
    /**
     * 找到第index个元素
     * <p>
     * 顺序loop即可
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
            if (index == len) {
                return tmp;
            }
            tmp = tmp.next;
            len++;
        }
        return null;
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

        int len = ListNodeUtil.getNodeLength(node);
        if (len < reverseIndex) {
            return null;
        }

        ListNode fast = node, slow = node;
        for (int i = 0; i < reverseIndex; i++) {
            fast = fast.next;
        }

        while (fast != null && slow != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
