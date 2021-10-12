package org.glamey.training.codes.leetcode;

/**
 * 36进制
 *
 * @author yang.zhou 2020.01.12.21
 */
public class Hex36 {

    public static void main(String[] args) {
        System.out.println(getVal('a'));
        System.out.println(getVal('x'));

        System.out.println(add("a", "x"));
        System.out.println(add("1b", "2x"));

    }


    private static final Character[] CHARACTERS =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                    'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /**
     * 单字符十进制转36进制
     */
    public static final char getChar(int index) {
        return CHARACTERS[index];
    }

    /**
     * 单字符转36进制
     */
    public static final int getVal(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        return (c - 'a') + 10;
    }


    public static final String add(String a, String b) {
        if (a == null || "".equals(a)) {
            return b;
        }

        if (b == null || "".equals(b)) {
            return a;
        }
        int i = a.length() - 1, j = b.length() - 1;
        int v1, v2, overVal = 0, sum;

        StringBuilder builder = new StringBuilder(Math.max(a.length(), b.length()) + 1);
        while (i >= 0 || j >= 0) {
            v1 = i >= 0 ? getVal(a.charAt(i)) : 0;
            v2 = j >= 0 ? getVal(b.charAt(j)) : 0;
            sum = v1 + v2 + overVal;
            builder.append(getChar(sum % 36));
            overVal = sum / 36;
            if (i >= 0) {
                i--;
            }
            if (j >= 0) {
                j--;
            }
        }
        builder.append(overVal == 1 ? getChar(1) : "");
        return builder.reverse().toString();
    }
}
