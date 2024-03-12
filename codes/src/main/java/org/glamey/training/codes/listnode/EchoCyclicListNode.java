package org.glamey.training.codes.listnode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/palindrome-linked-list/description/
 * 回文链表
 */
public class EchoCyclicListNode {

    public static void main(String[] args) {
        System.out.println(isEchoCyclicListNode_1(ListNodeUtil.create(new int[]{1, 2, 3, 2, 1})));
        System.out.println(isEchoCyclicListNode_1(ListNodeUtil.create(new int[]{1, 2, 3, 1, 1})));

        System.out.println(isEchoCyclicListNode_2(ListNodeUtil.create(new int[]{1, 2, 3, 2, 1})));
        System.out.println(isEchoCyclicListNode_2(ListNodeUtil.create(new int[]{1, 2, 2, 1})));

        System.out.println(isPalindrome(ListNodeUtil.create(new int[]{1, 2, 2, 1})));

    }

    /**
     * 1、链表存储数组。
     * 2、双指针方式遍历数组
     * 时间复杂度： O(n) + O(n/2) = O(n)
     * 空间复杂度：O(n)
     */
    private static boolean isEchoCyclicListNode_1(ListNode head) {
        if (head == null) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        ListNode tmp = head;
        while (tmp != null) {
            list.add(tmp.val);
            tmp = tmp.next;
        }
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            if (list.get(left) != list.get(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        //1、找到前半段的尾节点
        ListNode firstHalfEnd = findHalfEnd(head);

        //2、翻转后半段
        ListNode secondHalfStart = reverse(firstHalfEnd.next);

        //3、数据对比
        ListNode p1 = head, p2 = secondHalfStart;
        boolean success = true;
        while (success && p2 != null) {
            if (p1.val != p2.val) {
                success = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        //4、恢复原始表
        firstHalfEnd.next = reverse(secondHalfStart);
        return success;
    }

    // 找到前半段的最后一个节点
    private static ListNode findHalfEnd(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    // 翻转链表
    private static ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null, cur = head, next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    /**
     * 原地处理
     * 时间复杂度：O(n)
     * 空间复杂度: O(1)
     * 1、把链表切割为前后两部分（快慢指针，如果是奇数个节点的话，把中间的节点放在前半部分）
     * 2、后半部分翻转
     * 3、前后对比
     * 4、恢复链表原貌
     */
    private static boolean isEchoCyclicListNode_2(ListNode head) {
        if (head == null) {
            return true;
        }
        //找到前半部分的尾结点（快慢指针方式）
        ListNode firstHalfEnd = endOfFirstHalf(head);
        //找到后半部分，并且做翻转
        ListNode secondHalfStart = revertEndHalf(firstHalfEnd.next);

        //比较两个链表是否为一样
        ListNode p1 = head, p2 = secondHalfStart;
        boolean isEchoCyclic = true;
        while (p2 != null && isEchoCyclic) {
            if (p1.val != p2.val) {
                isEchoCyclic = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        //最后是不是要还原链表的原貌
        firstHalfEnd.next = revertEndHalf(secondHalfStart);
        return true;
    }

    private static ListNode revertEndHalf(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private static ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
