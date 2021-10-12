package org.glamey.training.codes.listnode;

/**
 * 单向链表反转
 */
public class ListNodeReverser {

    public static ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode p = head, cur = head.next, tmp;
        while (cur != null) {
            tmp = cur.next;
            cur.next = p;
            p = cur;
            cur = tmp;
        }
        head.next = null;
        return p;
    }

    //Recursive

    /**
     * head->1->2->3->4
     * <p>
     * 4->3->2->1->null
     */
    public static ListNode reverse_recursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reverse_recursive(head.next);
        // 改变 1，2节点的指向。
        // 通过 head.next获取节点2
        ListNode n = head.next;
        // 让 2 的 next 指向 2
        n.next = head;
        // 1 的 next 指向 null.
        head.next = null;
        // 把调整之后的链表返回。
        return node;
    }


    public static void main(String[] args) {
        int[] array = new int[] {1, 2, 3, 4};
        ListNode head = new ListNode(array[0]);
        ListNode tmp = head;
        for (int i = 1; i < array.length; i++) {
            ListNode node = new ListNode(array[i]);
            tmp.next = node;
            tmp = node;
        }

        ListNode printNode = head;
        while (printNode != null) {
            System.out.println(printNode.val);
            printNode = printNode.next;
        }

        ListNode reverseNode = head;
        ListNode reverse_recursive = reverse_recursive(reverseNode);
        printNode = reverse_recursive;
        while (printNode != null) {
            System.out.println(printNode.val);
            printNode = printNode.next;
        }
    }

}
