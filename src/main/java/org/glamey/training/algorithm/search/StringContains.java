package org.glamey.training.algorithm.search;

/**
 *
 * 有两个字符串a, b。判断字符串a是否包含字符串b
 * @author yang.zhou 2020.01.07.10
 */
public class StringContains {

    public static void main(String[] args) {

        System.out.println(new StringContains().contains("ababc", "bc"));
        System.out.println(new StringContains().contains("helloword1", "llow"));
    }

    /**
     * b是否包含在a中
     *
     * @param a
     * @param b
     * @return
     */
    public boolean contains(String a, String b) {
        if (a == null || "".equals(a) || b == null || "".equals(b))
            return false;

        if (b.length() > a.length())
            return false;


        char bFirst = b.charAt(0);

        for (int i = 0; i < a.length(); i++) {

            //没有在a找到b[0]开头在字符
            if (bFirst != a.charAt(i) && i + b.length() > a.length())
                return false;

            /**
             * 找到b[0]开头的字符，截取a中从i + 1开始长度为 b.length - 1字符，与b[1-b.length]每个字符进行比较
             * 如果相等，返回true，反之，继续循环a，找b[0]。
             */
            char[] subA = a.substring(i + 1, i + b.length()).toCharArray();
            char[] subB = b.substring(1).toCharArray();

            if (compareChars(subA, subB)) {
                return true;
            }
        }
        return false;
    }

    private boolean compareChars(char[] subA, char[] subB) {
        if (subA.length != subB.length)
            return false;

        int length = subA.length;
        for (int i = 0; i < length; i++) {
            if (subA[i] != subB[i])
                return false;
        }
        return true;
    }
}

