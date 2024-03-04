package org.glamey.training.codes.listnode;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 从尾部倒序输出链表
 *
 * @author zhouyang05 <zhouyang05@kuaishou.com>
 * Created on 2021-10-13
 */
public class PrintListFromTail {

    private static final List<Integer> list = Lists.newArrayList();

    public static void main(String[] args) {
        ListNode listNode = ListNodeUtil.create(new int[] {1, 2, 3, 4, 5, 6});
        printListNodeFromTail(listNode);
        System.out.println(list);

        ListNode revertListNode = revertListNode(listNode);
        ListNodeUtil.print(revertListNode);


        ListNode node1 = ListNodeUtil.create(new int[] {1, 10});
        ListNode node2 = ListNodeUtil.create(new int[] {3});

        ListNode mergeNode = mergeNode(node1, node2);
        ListNodeUtil.print(mergeNode);

        String maxEcho = maxEcho("cbbd");
        System.out.println(maxEcho);

    }

    private static String maxEcho(String source) {
        if (source == null || source.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < source.length(); i++) {
            int len1 = subMaxEchoLen(source, i, i);
            int len2 = subMaxEchoLen(source, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return source.substring(start, end + 1);
    }

    private static int subMaxEchoLen(String source, int start, int end) {
        while (start >= 0 && end < source.length() && source.charAt(start) == source.charAt(end)) {
            --start;
            ++end;
        }
        return end - start + 1;
    }

    private static ListNode mergeNode(ListNode node1, ListNode node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        if (node1.val <= node2.val) {
            node1.next = mergeNode(node1.next, node2);
            return node1;
        }
        node2.next = mergeNode(node1, node2.next);
        return node2;
    }

    private static ListNode revertListNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 1, 2, 3, 4
        ListNode pre = head, cur = pre.next, next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head.next = null;
        return pre;
    }

    private static void printListNodeFromTail(ListNode listNode) {
        if (listNode == null) {
            return;
        }
        printListNodeFromTail(listNode.next);
        list.add(listNode.val);
    }
}
