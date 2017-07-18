package org.glamey.training.algorithm.leetcode;

/**
 * https://leetcode.com/problems/add-two-numbers/#/description
 * <p>
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 *
 * @author zhouyang.zhou. 2017.06.28.16.
 */
public class SinglyLinkAdd {

    public static int reverse(int x) {
        String source = String.valueOf(x);
        char[] chars = source.toCharArray();
        String symbol = "";
        StringBuilder builder = new StringBuilder();
        int len = chars.length;
        for (int i = len - 1; i >= 0; i--) {
            char c = chars[i];
            if (i == 0) {
                if (!Character.isDigit(c)) {
                    symbol = String.valueOf(c);
                    continue;
                }
            }
            builder.append(c);
        }
        if (symbol != "") {
            builder.insert(0, symbol);
        }
        Long l = Long.valueOf(builder.toString());
        if (l >= Integer.MAX_VALUE || l <= Integer.MIN_VALUE) {
            return 0;
        }

        return Integer.valueOf(builder.toString());
    }


    public String multiply(String num1, String num2) {

        return null;
    }

    public static void main(String[] args) {
        System.out.println(reverse(12345));
        System.out.println(reverse(-12345));
        System.out.println(reverse(1234567899));
        System.out.println(reverse(-1234567899));
    }
}
