package org.glamey.training.codes.listnode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class ListNodeFinder {


    public static void main(String[] args) {
        /*ListNode kthToTail = findKthToTail(ListNodeUtil.create(new int[] {1, 2, 3, 4, 5}), 5);
        ListNodeUtil.print(kthToTail);

        kthToTail = findKthToTail(ListNodeUtil.create(new int[] {1, 2, 3, 4, 5}), 6);
        ListNodeUtil.print(kthToTail);

        kthToTail = findKthToTail(ListNodeUtil.create(new int[] {1, 2, 3, 4, 5}), 1);
        ListNodeUtil.print(kthToTail);*/


        // 找到最小的3个数字
        int[] nums = new int[]{70, 4, 5, 2, 100, 80};
        int minTotal = 3;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < minTotal; i++) {
            queue.add(nums[i]);
        }

        for (int i = minTotal; i < nums.length; i++) {
            if (queue.peek() >= nums[i]) {
                queue.poll();
                queue.add(nums[i]);
            }
        }
        while (queue.peek() != null) {
            System.out.println(queue.poll());
        }
    }


    /**
     * 找到第index个元素
     * <p>
     * 顺序loop即可
     */
    public static ListNode findKth(ListNode node, int index) {
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
     */
    public static ListNode findKthToTail(ListNode head, int k) {
        if (head == null || k < 0) {
            return head;
        }
        // 1 2 3 4 5  2
        ListNode fast = head, slow = head;
        int i = 0;
        for (; fast != null; i++) {
            if (i >= k) {
                slow = slow.next;
            }
            fast = fast.next;
        }
        return i < k ? null : slow;
    }

    /**
     * 查看倒数第index个元素
     * <p>
     * 思路：使用步长的方式来解决。
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
