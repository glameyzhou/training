package org.glamey.training.algorithm.leetcode.listnode;

import java.util.Stack;

/**
 * 两个单向链表相加。
 * 1 -> 2 -> 8 -> 3
 * 8 -> 1 -> 7
 * <p>
 * 2 -> 1 -> 0 -> 0
 */
public class ListNodeAdd {


    /**
     * 通过栈的方式来实现
     *
     * @param n1
     * @param n2
     * @return
     */
    public static ListNode addTwoNodeByStack(ListNode n1, ListNode n2) {
        if (n1 == null) {
            return n2;
        }

        if (n2 == null) {
            return n1;
        }

        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (n1 != null) {
            s1.push(n1.val);
            n1 = n1.next;
        }

        while (n2 != null) {
            s2.push(n2.val);
            n2 = n2.next;
        }

        ListNode head = null, tmp;
        int value1, value2, sumValue, over = 0;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            value1 = s1.isEmpty() ? 0 : s1.pop();
            value2 = s2.isEmpty() ? 0 : s2.pop();
            sumValue = value1 + value2 + over;

            /**
             *  思路，最终是返回head接口，设置一个缓存节点tmp存放上一个节点的所有数据。
             *  1、tmp = head
             *  2、构建当前节点内容head，并且next = pre
             *
             *  第二轮的时候，先把head缓存至tmp,重复1、2，这样结果链表就构建完毕。
             */
            tmp = head;
            head = new ListNode(sumValue % 10);
            head.next = tmp;//高位指向低位
            over = sumValue / 10;
        }

        //最后一次还有进位的话，新增一个节点，值为1。
        if (over == 1) {
            tmp = head;
            head = new ListNode(1);
            head.next = tmp;
        }
        return head;
    }

    public static ListNode addTwoNodeByReverse(ListNode n1, ListNode n2) {
        if (n1 == null) {
            return n2;
        }

        if (n2 == null) {
            return n1;
        }
        ListNode head = null, tmp = null;
        ListNode nodeA = ListNodeReverser.reverse(n1);
        ListNode nodeB = ListNodeReverser.reverse(n2);
        int a, b, sum, over = 0;
        while (nodeA != null || nodeB != null) {
            a = nodeA != null ? nodeA.val : 0;
            b = nodeB != null ? nodeB.val : 0;
            sum = a + b + over;
            tmp = head;
            head = new ListNode(sum % 10);
            head.next = tmp;
            over = sum / 10;

            nodeA = nodeA != null ? nodeA.next : null;
            nodeB = nodeB != null ? nodeB.next : null;
        }

        if (over == 1) {
            tmp = head;
            head = new ListNode(1);
            head.next = tmp;
        }

        return head;
    }
}
