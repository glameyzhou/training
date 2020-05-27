package org.glamey.training.algorithm.meituan;

/**
 * 求两个字符串相加的结果
 */
public class StringSums {

    public static void main(String[] args) {
        String a = "123", b = "877";
        String ret = stringSum(a, b);
        System.out.println(ret);
        System.out.println(ret.equals("1000"));
    }

    private static String stringSum(String a, String b) {
        if (a == null || "".equals(a)) return b;
        if (b == null || "".equals(b)) return a;
        char[] ca = a.toCharArray(), cb = b.toCharArray();
        StringBuffer buffer = new StringBuffer(Math.max(ca.length, cb.length) + 1);
        int m, n, sum, overFlow = 0, i = ca.length - 1, j = cb.length - 1;
        while (i >= 0 || j >= 0) {
            if (i >= 0) {
                m = ca[i] - '0';
                i--;
            } else {
                m = 0;
            }

            if (j >= 0) {
                n = cb[j] - '0';
                j--;
            } else {
                n = 0;
            }

            sum = m + n + overFlow;
            buffer.append(sum % 10);
            overFlow = sum / 10;
        }

        if (overFlow == 1) {
            buffer.append(1);
        }
        return buffer.reverse().toString();
    }
}
