package org.glamey.training.codes.strings;

import org.glamey.training.codes.Utils;

/**
 * 循环右转字符串
 * <p>
 * 形如：abcdef 右移前两位变成 cdefab
 */
public class RevertString {

    public static void main(String[] args) {
        String source = "abcdef";
        System.out.println(revertString(source, 2, 6).equals("cdefab"));
    }

    /**
     * 1 bacdef  翻转前两位
     * 2 bafedc  翻转后四位
     * 3 cdefab  翻转所有
     *
     * @param source
     * @param k
     * @param len
     * @return
     */
    private static String revertString(String source, int k, int len) {
        if (k <= 0 || len <= 0 || k == len) {
            return source;
        }
        char[] chars = source.toCharArray();
        doRevert(chars, 0, k - 1);
        doRevert(chars, k, len - 1);
        doRevert(chars, 0, len - 1);
        return new String(chars);
    }

    private static void doRevert(char[] chars, int left, int right) {
        while (left < right) {
            Utils.swap(chars, left, right);
            left++;
            right--;
        }
    }
}
