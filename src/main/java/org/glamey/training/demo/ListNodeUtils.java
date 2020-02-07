package org.glamey.training.demo;

/**
 * @author yang.zhou 2020.01.31.21
 */
public class ListNodeUtils {

    public static ListNode create(int[] nums) {
        ListNode dummy = new ListNode(-1), tmp = dummy;
        for (int num : nums) {
            ListNode node = new ListNode(num);
            tmp.next = node;
            tmp = node;
        }
        return dummy.next;
    }


    public static void print(ListNode head) {
        ListNode tmp = head;
        StringBuilder builder = new StringBuilder();
        while (tmp != null) {
            builder.append(tmp.val).append(", ");
            tmp = tmp.next;
        }
        System.out.println(builder.length() > 1 ? builder.deleteCharAt(builder.length() - 2) : "");
    }
}
