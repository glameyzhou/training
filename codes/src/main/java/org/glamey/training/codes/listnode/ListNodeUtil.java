package org.glamey.training.codes.listnode;

/**
 * 单向链表工具类：创建、输出
 */
public class ListNodeUtil {

    public static void main(String[] args) {
        ListNode n1 = create(new int[]{1, 2, 3});
        print(n1);
        ListNode n2 = create(new int[]{1, 2});
        print(n2);

        System.out.println(eq(null, null));


    }


    public static ListNode create(int[] array) {
        if (array == null) {
            return null;
        }

        /*ListNode head = new ListNode(array[0]);
        ListNode tmp = head;
        for (int i = 1; i < array.length; i++) {
            ListNode curNode = new ListNode(array[i]);
            tmp.next = curNode;
            tmp = curNode;
        }
        return head;*/

        ListNode dummy = new ListNode(0);
        ListNode tmp = dummy;
        for (int arr : array) {
            ListNode node = new ListNode(arr);
            tmp.next = node;
            tmp = node;
        }
        return dummy.next;
    }


    public static void print(ListNode head) {
        ListNode tmp = head;
        StringBuilder builder = new StringBuilder();
        while (tmp != null) {
            builder.append(tmp.val).append(", ");
            tmp = tmp.next;
        }
        String ret = builder.length() > 0 ? builder.deleteCharAt(builder.length() - 2).toString() : "";
        System.out.println(ret);
    }


    public static int getNodeLength(ListNode head) {
        ListNode tmp = head;
        int len = 0;
        while (tmp != null) {
            tmp = tmp.next;
            len++;
        }
        return len;
    }

    public static boolean eq(ListNode n1, ListNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        }

        if (n1 == null || n2 == null) {
            return false;
        }

        while (n1 != null || n2 != null) {
            if (n1 == null || n2 == null) {
                return false;
            }
            if (n1.val != n2.val) {
                return false;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        return true;
    }
}
