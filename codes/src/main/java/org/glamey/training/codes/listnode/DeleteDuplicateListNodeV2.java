package org.glamey.training.codes.listnode;

/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * <p>
 * 示例 1:
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * <p>
 * 示例 2:
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 * 通过次数44,713提交次数94,119
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
 */
public class DeleteDuplicateListNodeV2 {


    public static void main(String[] args) {
        ListNodeUtil.print(deleteDuplicates(ListNodeUtil.create(new int[]{1, 2, 3, 4})));
        ListNodeUtil.print(deleteDuplicates(ListNodeUtil.create(new int[]{1, 1, 1, 2, 2, 2})));
        ListNodeUtil.print(deleteDuplicates(ListNodeUtil.create(new int[]{1, 2, 2, 3})));
    }

    /**
     * 删除所有重复的节点
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dumpy = new ListNode(-1);
        dumpy.next = head;
        ListNode cur = dumpy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                ListNode tmp = cur.next;
                while (tmp.next != null && tmp.val == tmp.next.val) {
                    tmp = tmp.next;
                }
                cur.next = tmp.next;
            } else {
                cur = cur.next;
            }
        }
        return dumpy.next;
    }
}
