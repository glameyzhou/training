package org.glamey.training.algorithm.leetcode.listnode;


/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * <p>
 * 示例 1:
 * 输入: 1->1->2
 * 输出: 1->2
 * <p>
 * 示例 2:
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 * 通过次数98,109提交次数195,173
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
 */
public class DeleteDuplicateListNode {

    public static void main(String[] args) {
        ListNodeUtil.print(deleteDuplicates(ListNodeUtil.create(new int[]{1, 1, 1, 2, 2, 2, 3})));
        ListNodeUtil.print(deleteDuplicates(ListNodeUtil.create(new int[]{1, 1, 3, 4,})));
    }


    /**
     * 删除重复链表（保留最后一个）
     * 时间复杂度：O(n)
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head, next = head.next;
        while (cur != null && next != null) {
            if (cur.val == next.val) {
                cur.next = next.next;
                next = cur.next;
            } else {
                cur = cur.next;
                next = next.next;
            }
        }
        return head;
    }
}
