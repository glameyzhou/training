package org.glamey.training.algorithm.leetcode.listnode;

import java.util.ArrayList;
import java.util.List;

public class EchoCyclicListNode {

    public static void main(String[] args) {
        System.out.println(isEchoCyclicListNode_1(ListNodeUtil.create(new int[]{1, 2, 3, 2, 1})));
        System.out.println(isEchoCyclicListNode_1(ListNodeUtil.create(new int[]{1, 2, 3, 1, 1})));

        System.out.println(isEchoCyclicListNode_2(ListNodeUtil.create(new int[]{1, 2, 3, 2, 1})));
        System.out.println(isEchoCyclicListNode_2(ListNodeUtil.create(new int[]{1, 2, 3, 1, 1})));
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
     * 翻转链表，然后对比
     *
     * @param root
     * @return
     */
    private static boolean isEchoCyclicListNode_2(ListNode root) {
        if (root == null) return true;
        ListNode tmp = root;
        ListNode reverseNode = reverse(tmp);
        while (reverseNode != null && root!= null) {
            if (reverseNode.val != root.val) {
                return false;
            }
            reverseNode = reverseNode.next;
            root = root.next;
        }
        return true;
    }

    private static ListNode reverse(ListNode root) {
        ListNode tmp = root;
        ListNode pre = tmp, cur = tmp.next, next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        tmp.next = null;
        return pre;
    }


}
