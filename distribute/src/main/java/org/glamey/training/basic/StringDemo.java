package org.glamey.training.basic;

import java.util.Objects;

/**
 * String Constants Pool 字符串常量池
 *
 * @author zhouyang.zhou, 2017.03.21.15.
 */
public class StringDemo {

    public static void main(String[] args) {
        String s = "abcd";
        int length = s.length(); // string char size

        int sLen = s.codePointCount(0, s.length());
        System.out.println("char size = " + length + "   string len " + sLen);

        System.out.println(contains("ababcab", "bc"));
        System.out.println(contains("ababcab", "ac"));
    }


    /**
     * 字符串是否包含 {@link java.lang.String#indexOf(String)}
     * @param source
     * @param target
     * @return
     */
    public static boolean contains(String source, String target) {
        if (Objects.isNull(source) || Objects.isNull(target)) {
            return false;
        }
        char[] charS = source.toCharArray(), charT = target.toCharArray();
        int loopMax = charS.length - charT.length;
        char first = charT[0];
        for (int i = 0; i < loopMax; i++) {
            //找到source中第一个匹配的下标
            while (charS[i] != first && ++i <= loopMax);
            int j = i + 1, end = j + charT.length - 1;
            for (int k = 1; k < charT.length && charS[j] == charT[k] ; k++, j ++);
            if (j == end) {
                return true;
            }
        }
        return false;
    }
}
