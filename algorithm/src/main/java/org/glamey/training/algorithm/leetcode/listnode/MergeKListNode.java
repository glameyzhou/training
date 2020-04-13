package org.glamey.training.algorithm.leetcode.listnode;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并K个有序链表
 *
 * 以下集中方案：
 * 1、两两进行比较合并，再跟第三个合并。
 * 2、利用最小堆原理：默认把所有待合并的链表全部如堆，循环取出堆顶元素（堆顶元素必然是最小的，然后再把next元素入堆），堆顶元素必然是最小的元素。
 */
public class MergeKListNode {
    public static void main(String[] args) {
        ListNode[] listNodes = Lists.newArrayList(
                ListNodeUtil.create(new int[]{1, 4, 5}),
                ListNodeUtil.create(new int[]{1, 3, 4}),
                ListNodeUtil.create(new int[]{2, 6})
        ).toArray(new ListNode[0]);
        ListNode listNode = mergeKListNode(listNodes);
        ListNodeUtil.print(listNode);
    }

    public static ListNode mergeKListNode(ListNode[] listNodes) {
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
            tmp = tmp.next;

            if (tmp.next != null) {
                queue.add(tmp.next);
            }
        }
        return dumpy.next;
    }
}
