package org.glamey.training.algorithm.leetcode.listnode;

/**
 * 单向链表：重组一个新的链表，让前半部分的值都小于target。
 */
public class ListNodeCompareAndMerge {

    public static void main(String[] args) {
        int[] nums = {1, 4, 2, 3, 5, 2};
        int target = 3;
        ListNode root = ListNodeUtil.create(nums);
        ListNode retNode = compareAndMerge(root, target);
        ListNodeUtil.print(retNode);
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param root
     * @param target
     * @return
     */
    private static ListNode compareAndMerge(ListNode root, int target) {
        ListNode dumpy_a = new ListNode(-1), tmp_a = dumpy_a;
        ListNode dumpy_b = new ListNode(-1), tmp_b = dumpy_b;
        ListNode tmp = root;
        while (tmp != null) {
            ListNode node = new ListNode(tmp.val);
            if (tmp.val < target) {
                tmp_a.next = node;
                tmp_a = node;
            } else {
                tmp_b.next = node;
                tmp_b = node;
            }
            tmp = tmp.next;
        }
        tmp_a.next = dumpy_b.next;
        return dumpy_a.next;
    }
}
