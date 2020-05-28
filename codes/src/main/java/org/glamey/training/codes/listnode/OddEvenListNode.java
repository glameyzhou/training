package org.glamey.training.codes.listnode;

/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 *
 * 示例 2:
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/odd-even-linked-list
 */
public class OddEvenListNode {

    public static void main(String[] args) {
        ListNode listNode = ListNodeUtil.create(new int[]{1, 2, 3, 4, 5});
        ListNode oddEvenList = oddEvenList(listNode);
        ListNodeUtil.print(oddEvenList);


        ListNode node = oddEventList_v2(ListNodeUtil.create(new int[]{1, 2, 3, 4, 5}));
        ListNodeUtil.print(node);
    }

    private static ListNode oddEventList_v2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dumpyOdd = new ListNode(-1), tmpOdd = dumpyOdd;
        ListNode dumpyEven = new ListNode(-1), tmpEven = dumpyEven;
        int index = 1;
        while (head != null) {
            ListNode node = new ListNode(head.val);
            if (index % 2 == 1) {
                tmpOdd.next = node;
                tmpOdd = node;
            } else {
               tmpEven.next = node;
               tmpEven = node;
            }
            head = head.next;
            index ++;
        }
        tmpOdd.next = dumpyEven.next;
        return dumpyOdd.next;
    }

    /**
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)，但是我们需要四个指针
     *
     * @param head
     * @return
     */
    private static ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
