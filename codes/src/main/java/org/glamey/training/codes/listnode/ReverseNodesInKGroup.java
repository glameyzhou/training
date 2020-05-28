package org.glamey.training.codes.listnode;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.Stack;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 示例：
 * 给你这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明：
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseNodesInKGroup {

    public static void main(String[] args) {
        String reverse = reverseString("i love china");
        System.out.println(reverse);

        String hello = reverseVowels("hello");
        System.out.println(hello);

        int len = removeVowels("hello");
        System.out.println(len);


        ListNode listNode = ListNodeUtil.create(new int[]{1, 2, 3, 4, 5, 6, 7});
        ListNode reverseK = reverseK(listNode, 4);
        ListNodeUtil.print(reverseK);


        String s = "123456";
        System.out.println(reverseOddEven(s));
    }

    private static String reverseOddEven(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        int left = 0, right = s.length() - 1;
        char[] chars = s.toCharArray();
        while (left < right) {
            while (left < right && (chars[left] - '0') % 2 != 0) {
                left ++;
            }
            while (left < right && (chars[right] - '0') % 2 == 0) {
                right --;
            }
            if (left > right) {
                break;
            }
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;

            left ++;
            right --;
        }
        return new String(chars);
    }

    /**
     * K个一组翻转链表，如果最后不够K个元素，不用翻转。
     * 时间复杂度：O(N)
     * 空间复杂度：O(K)
     * @param root
     * @param k
     * @return
     */
    public static ListNode reverseK(ListNode root, int k) {
        if (root == null || k <= 1) {
            return root;
        }

        ListNode dumpy = new ListNode(-1), tmp = dumpy;

        Stack<Integer> stack = new Stack<>();
        int count = 0;
        while (root != null) {
            stack.push(root.val);
            root = root.next;
            count ++;
            if (count == k) {
                while (!stack.isEmpty()) {
                    ListNode node = new ListNode(stack.pop());
                    tmp.next = node;
                    tmp = node;
                }
                count = 0;
            }
        }
        ListNode head = null, tmpNode;
        while (!stack.isEmpty()) {
            tmpNode = head;
            head = new ListNode(stack.pop());
            head.next = tmpNode;
        }
        tmp.next = head;
        return dumpy.next;
    }

    private static int removeVowels(String source) {
        if (source == null || "".equals(source)) {
            return 0;
        }
        char[] chars = source.toCharArray();
        int ret = 0, len = chars.length;
        for (int i = 0; i < len; i++) {
            if (!isVowel(chars[i])) {
                chars[ret++] = chars[i];
            }
        }
        String subString = new String(chars, 0, ret);
        System.out.println(subString);
        return ret;
    }


    private static String reverseVowels(String source) {
        int len = source.length(), low = 0, high = len - 1;
        char[] chars = source.toCharArray();
        while (low <= high) {
            while (low < len && !isVowel(chars[low])) {
                low++;
            }
            while (high >= 0 && !isVowel(chars[high])) {
                high--;
            }

            if (low > high) {
                break;
            }

            swap(chars, low, high);
            low++;
            high--;


        }
        return new String(chars);
    }

    private static void swap(char[] chars, int low, int high) {
        if (low >= high) return;
        char tmp = chars[low];
        chars[low] = chars[high];
        chars[high] = tmp;
    }

    private static final Set<Character> vowelSet = Sets.newHashSet('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

    private static boolean isVowel(char aChar) {
        return vowelSet.contains(aChar);
    }


    private static String reverseString(String source) {
        int low = 0, high = source.length() - 1;
        char[] chars = source.toCharArray();
        while (low <= high) {
            char tmp = chars[low];
            chars[low] = chars[high];
            chars[high] = tmp;
            low++;
            high--;
        }
        return new String(chars);
    }
}
