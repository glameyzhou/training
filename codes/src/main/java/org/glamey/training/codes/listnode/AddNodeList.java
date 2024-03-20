package org.glamey.training.codes.listnode;

import java.util.Arrays;

/**
 * 单向链表中在指定位置添加节点
 *
 * @author zhouyang281
 * @date 2024-03-19
 */
public class AddNodeList {

    public static void main(String[] args) {
        ListNode addNodeV1 = addNodeV1(ListNodeUtil.create(new int[]{1, 2, 4, 5}), new ListNode(3), 2);
        ListNodeUtil.print(addNodeV1);

        ListNode addNodeV2 = addNodeV2(ListNodeUtil.create(new int[]{1, 2, 4, 5}), new ListNode(3), 2);
        ListNodeUtil.print(addNodeV2);

    }

    private static ListNode addNodeV1(ListNode head, ListNode addNode, int index) {
        if (head == null) {
            return null;
        }
        int size = ListNodeUtil.getNodeLength(head);
        if (index > size - 1) {
            return head;
        }
        if (index == 0) {
            addNode.next = head;
            return addNode;
        }
        ListNode pre = head;
        int i = 0;
        while (i < index - 1) {
            pre = pre.next;
            i++;
        }
        addNode.next = pre.next;
        pre.next = addNode;
        return head;
    }


    private static ListNode addNodeV2(ListNode head, ListNode addNode, int index) {
        // 1, 2, 4, 5
        if (head == null || addNode == null || index < 0) {
            return head;
        }
        if (index == 0) {
            addNode.next = head;
            return addNode;
        }
        int i = 1;
        ListNode p = head;
        while (i < index && p != null) {
            p = p.next;
            i++;
        }
        //index超过单向链表的长度
        if (i < index) {
            return head;
        }
        addNode.next = p.next;
        p.next = addNode;
        return head;
    }
}
