package org.glamey.training.algorithm.leetcode.listnode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EchoCyclicListNode {

    public static void main(String[] args) {
        System.out.println(isEchoCyclicListNode_1(ListNodeUtil.create(new int[]{1, 2, 3, 2, 1})));
        System.out.println(isEchoCyclicListNode_1(ListNodeUtil.create(new int[]{1, 2, 3, 1, 1})));

        System.out.println(isEchoCyclicListNode_2(ListNodeUtil.create(new int[]{1, 2, 3, 2, 1})));
        System.out.println(isEchoCyclicListNode_2(ListNodeUtil.create(new int[]{1, 2, 2, 1})));


        LinkedList<Integer> originList = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            originList.addLast(i);
        }

        for (int i = 0; i < 4; i++) {
            System.out.println(originList.poll());
        }

    }

    /**
     * 将链表复制到数组中，通过数组的下标方式O(1)获取对应的值，双指针的方式来判定是否回文。
     * 时间复杂度： O(n) + O(n/2) = O(n)
     * 空间复杂度：O(n)
     *
     * @param root
     * @return
     */
    private static boolean isEchoCyclicListNode_1(ListNode root) {
        if (root == null) return true;
        List<Integer> list = new ArrayList<>();//超长链表有扩容的现象
        ListNode tmp = root;
        while (tmp != null) {
            list.add(tmp.val);
            tmp = tmp.next;
        }
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (list.get(left) != list.get(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    /**
     * 原地处理
     * 时间复杂度：O(n)
     * 空间复杂度: O(1)
     * 1、把链表切割为前后两部分（快慢指针，如果是奇数个节点的话，把中间的节点放在前半部分）
     * 2、后半部分翻转
     * 3、前后对比
     * 4、恢复链表原貌
     * @param head
     * @return
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
