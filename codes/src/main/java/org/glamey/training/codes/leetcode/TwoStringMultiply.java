package org.glamey.training.codes.leetcode;

/**
 * 两个非负整数字符串相乘
 * "12" * "12" = "144"
 */
public class TwoStringMultiply {

    public static void main(String[] args) {
        String a = "123", b = "456";
        String multiply = multiply(a, b);
        System.out.println(multiply);
    }

    private static String multiply(String a, String b) {
        if ("0".equals(a) || "0".equals(b)) {
            return "0";
        }
        char[] ca = a.toCharArray(), cb = b.toCharArray();
        String[] ret = new String[cb.length];
        int len = ca.length + cb.length;
        for (int i = cb.length - 1; i >= 0; i--) {
            ret[i] = multiply_(cb[i], ca, cb.length - 1 - i, len);
        }
        return sum(ret);
    }

    private static String sum(String[] nums) {
        StringBuilder builder = new StringBuilder(nums.length + 1);
        int sum = 0, over = 0;
        int charLen = nums[0].length();
        for (int i = 0; i < 10; i++) {

        }


        return null;
    }

    private static String multiply_(char m, char[] ca, int tailZeroLen, int len) {
        int over = 0, multiply = 0;
        StringBuilder builder = new StringBuilder(ca.length);
        for (int i = 0; i < tailZeroLen; i++) {
            builder.append("0");
        }
        for (char c : ca) {
            multiply = (m - '0') * (c - '0');
            if (multiply % 10 > 0) {
                builder.append(multiply % 10);
                over = 1;
            } else {
                builder.append(multiply);
            }
        }
        if (over == 1) {
            builder.append("1");
        }
        int headZeroLen = len - builder.length();
        for (int i = 0; i < headZeroLen; i++) {
            builder.append("0");
        }
        return builder.reverse().toString();
    }
}
