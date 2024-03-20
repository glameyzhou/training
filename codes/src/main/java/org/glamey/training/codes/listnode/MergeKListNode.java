package org.glamey.training.codes.listnode;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.google.common.collect.Lists;

/**
 * https://leetcode.cn/problems/merge-k-sorted-lists/
 * 合并K个有序链表
 * <p>
 * 以下集中方案：
 * 1、两两进行比较合并，再跟第三个合并。
 * 2、利用最小堆原理：默认把所有待合并的链表全部入堆，循环取出堆顶元素（堆顶元素必然是最小的，然后再把next元素入堆），堆顶元素必然是最小的元素。
 */
public class MergeKListNode {
    public static void main(String[] args) {
        ListNode[] listNodes = Lists.newArrayList(
                ListNodeUtil.create(new int[]{1, 4, 5}),
                ListNodeUtil.create(new int[]{1, 3, 4}),
                ListNodeUtil.create(new int[]{2, 6})
        ).toArray(new ListNode[0]);


        int len = 3;
        // 1, 1, 2, 3, 4, 4, 5, 6
        // 通过优先级队列（最小堆来实现）
        ListNode[] list1 = ListNodeUtil.copy(listNodes);
        ListNodeUtil.print(mergeKListNodeByPriority(list1));

        // 通过两两合并的思路实现
        ListNode[] list2 = ListNodeUtil.copy(listNodes);
        System.arraycopy(listNodes, 0, list2, 0, len);
        ListNodeUtil.print(mergeKListNodeV2(list2));
    }

    public static ListNode mergeKListNodeV2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }

        ListNode ret = lists[0];
        for (int i = 1; i < lists.length; i++) {
            ret = merge(ret, lists[i]);
        }
        return ret;
    }

    private static ListNode merge(ListNode n1, ListNode n2) {
        if (n1 == null) {
            return n2;
        }
        if (n2 == null) {
            return n1;
        }
        ListNode dumpy = new ListNode(-1);
        ListNode p1 = n1, p2 = n2, tmp = dumpy;
        while (p1 != null && p2 != null) {
            ListNode node;
            if (p1.val > p2.val) {
                node = new ListNode(p2.val);
                p2 = p2.next;
            } else {
                node = new ListNode(p1.val);
                p1 = p1.next;
            }
            tmp.next = node;
            tmp = node;
        }
        if (p1 != null) {
            tmp.next = p1;
        }
        if (p2 != null) {
            tmp.next = p2;
        }
        return dumpy.next;
    }


    public static ListNode mergeKListNodeByPriority(ListNode[] listNodes) {
        if (listNodes == null || listNodes.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparing(node -> node.val));
        for (int i = 0; i < listNodes.length; i++) {
            if (listNodes[i] != null) {
                queue.add(listNodes[i]);
            }
        }

        ListNode dumpy = new ListNode(0);
        ListNode tmp = dumpy;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            tmp.next = node;
            tmp = node;

            if (tmp.next != null) {
                queue.add(tmp.next);
            }
        }
        return dumpy.next;
    }
}
