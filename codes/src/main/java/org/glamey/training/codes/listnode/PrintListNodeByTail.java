package org.glamey.training.codes.listnode;


import java.util.Stack;


/**
 * 从尾到头打印单向链表
 * <p>
 * https://wiki.jikexueyuan.com/project/for-offer/question-five.html
 */
public class PrintListNodeByTail {

    public static void main(String[] args) {
        ListNode head = ListNodeUtil.create(new int[]{1, 2, 3, 4, 5});

        printListNodeByTailThroughStack(head);

        printListNodeByTailThroughRecursion(head);
    }

    /**
     * 通过递归来实现
     * 时间复杂度：O(N)
     * 空间复杂度：O(1) 其实也有栈空间的
     *
     * @param head
     */
    private static void printListNodeByTailThroughRecursion(ListNode head) {
        if (head == null) {
            return;
        }
        printListNodeByTailThroughRecursion(head.next);
        System.out.println(head.val);
    }

    /**
     * 通过栈的方式来实现。
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param head
     */
    private static void printListNodeByTailThroughStack(ListNode head) {
        if (head == null) {
            return;
        }
        Stack<Integer> stack = new Stack<>();
        ListNode tmp = head;
        while (tmp != null) {
            stack.push(tmp.val);
            tmp = tmp.next;
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
