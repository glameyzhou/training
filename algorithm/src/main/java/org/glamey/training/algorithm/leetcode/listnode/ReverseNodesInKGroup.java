package org.glamey.training.algorithm.leetcode.listnode;

import com.google.common.collect.Sets;

import java.util.Set;

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
