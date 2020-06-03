package org.glamey.training.codes.listnode;

public class ListNodeFinder {


    public static void main(String[] args) {
        ListNode kthToTail = findKthToTail(ListNodeUtil.create(new int[]{1, 2, 3, 4, 5}), 5);
        ListNodeUtil.print(kthToTail);

        kthToTail = findKthToTail(ListNodeUtil.create(new int[]{1, 2, 3, 4, 5}), 6);
        ListNodeUtil.print(kthToTail);

        kthToTail = findKthToTail(ListNodeUtil.create(new int[]{1, 2, 3, 4, 5}), 1);
        ListNodeUtil.print(kthToTail);
    }

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
     * 找到倒数第K的节点
     *
     * @return
     */
    public static ListNode findKthToTail(ListNode head, int k) {
        if (head == null || k < 0) {
            return head;
        }

        ListNode p = head, q = head;
        int i = 0;
        for (; p != null; i++) {
            if (i >= k) {
                q = q.next;
            }
            p = p.next;
        }
        return i < k ? null : q;
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
