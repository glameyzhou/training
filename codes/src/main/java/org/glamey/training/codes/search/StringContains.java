package org.glamey.training.codes.search;

import org.glamey.training.codes.Utils;

/**
 * https://blog.csdn.net/u010983881/article/details/78281296
 * 有两个字符串a, b。判断字符串a是否包含字符串b
 *
 * @author yang.zhou 2020.01.07.10
 */
public class StringContains {

    public static void main(String[] args) {
        System.out.println("ababced -> bc = " + contains("ababcdef", "bc"));
        System.out.println("ababced -> efg = " + contains("ababcdef", "efg"));
        System.out.println("ab -> efg = " + contains("ab", "efg"));
        System.out.println("null -> efg = " + contains(null, "efg"));
        System.out.println("ab -> null = " + contains("ab", null));
    }

    public static boolean contains(String source, String target) {
        if (Utils.isBlank(source) || Utils.isBlank(target) || source.length() < target.length()) {
            return false;
        }
        char[] charS = source.toCharArray(), charT = target.toCharArray();
        char first = charT[0];
        int loopMax = charS.length - charT.length;
        for (int i = 0; i <= loopMax; i++) {
            //找到source中匹配的第一个字符
            while (charS[i] != first && ++i <= loopMax)
                ;

            if (i > loopMax) {
                return false;
            }
            int s = i + 1, end = s + charT.length - 1;
            for (int k = 1; s < end && charS[s] == charT[k]; k++, s++)
                ;
            //找到完整的字符串，直接返回
            if (s == end) {
                return true;
            }
        }
        return false;
    }
}

