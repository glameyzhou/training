package org.glamey.training.algorithm.leetcode.dp;

public class LongestPalindromic {


    /**
     * 最长回文子串
     *
     * 如果一个字串是回文字串，那么去掉左右两边的字符之后依然是回文。
     * 也可以说是一个回文字串，左右两边加上相同的字符，也是回文字串。
     * 所以我们可以使用索引 i 和 j 来表示一个字符串从索引 i 到 j 的子串，
     * dp[i][j]表示索引i到j的子串是否是回文 dp[i][j] = true表示是回文，反之则为false
     *
     *
     * @param source
     * @return
     */
    public static String longestPalindromic(String source) {
        if (source == null || "".equals(source)) return source;

        int len = source.length(), low = 0, high = 0;
        boolean [][] dp = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                
            }
        }


        return null;
    }
}
