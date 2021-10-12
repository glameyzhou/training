package org.glamey.training.codes.meituan;

import org.glamey.training.codes.Utils;

/**
 * 求两个字符串相加的结果
 */
public class StringSums {

    public static void main(String[] args) {
        System.out.println(sumString("123", "877").equals("1000"));
        System.out.println(sumString("123", "7").equals("130"));
    }

    public static String sumString(String a, String b) {
        if (Utils.isBlank(a)) {
            return b;
        }
        if (Utils.isBlank(b)) {
            return a;
        }
        char[] charA = a.toCharArray(), charB = b.toCharArray();
        int m, n, sum, over = 0, i = charA.length - 1, j = charB.length - 1;
        StringBuilder builder = new StringBuilder(Math.max(charA.length, charB.length) + 1);
        while (i >= 0 || j >= 0) {
            if (i >= 0) {
                m = charA[i] - '0';
                i--;
            } else {
                m = 0;
            }
            if (j >= 0) {
                n = charB[j] - '0';
                j--;
            } else {
                n = 0;
            }
            sum = m + n + over;
            builder.append(sum % 10);
            over = sum / 10;
        }
        if (over == 1) {
            builder.append(1);
        }
        return builder.reverse().toString();
    }
}
