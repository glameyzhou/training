package org.glamey.training.codes.listnode;


import java.util.HashSet;
import java.util.Set;

/**
 * 编写一个程序，找到两个单链表相交的起始节点。
 * <p>
 * 注意：
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IntersectionTwoListNode {


    /**
     * 1,2,3,4,5
     * 1,3,4,5
     *
     * @param args
     */
    public static void main(String[] args) {
        ListNode headA = ListNodeUtil.create(new int[]{1, 2, 3, 4, 5});
        ListNode headB = new ListNode(-1);
        headB.next = headA; // -1,1,2,3,4,5

        ListNode intersectionNode = getIntersectionNode(headA, headB);
        ListNodeUtil.print(intersectionNode);

        ListNode intersectionNodeByHash = getIntersectionNodeByHash(headA, headB);
        ListNodeUtil.print(intersectionNodeByHash);

    }


    /**
     * 时间复杂度：O(len(A) + len(B))
     * 空间复杂度：O(1)
     *
     * @param headA
     * @param headB
     * @return
     */
    private static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }

    private static ListNode getIntersectionNodeByHash(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode p = headA, q = headB;
        while (p != null) {
            set.add(p);
            p = p.next;
        }
        while (q != null) {
            if (set.contains(q)) {
                return q;
            }
            q = q.next;
        }
        return null;
    }
}
