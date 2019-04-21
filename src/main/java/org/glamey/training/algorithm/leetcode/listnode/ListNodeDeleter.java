package org.glamey.training.algorithm.leetcode.listnode;

public class ListNodeDeleter {


    public static ListNode delete(ListNode node, int value) {
        if (node == null) {
            return null;
        }

        //头节点就是待删除的数据
        if (node.val == value) {
            return node.next;
        }

        //因为删除节点需要知道上一个node，因此我们判断下个节点是否为指定值即可。
        ListNode tmp = node;
        while (tmp.next != null && tmp.next.val != value) {
            tmp = tmp.next;
        }

        //tmp.next有可能为空，需要判断
        if (tmp.next != null && tmp.next.val == value) {
            tmp.next = tmp.next.next;
        }
        return node;
    }
}
