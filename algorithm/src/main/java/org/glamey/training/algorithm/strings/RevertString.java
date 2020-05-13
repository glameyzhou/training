package org.glamey.training.algorithm.strings;

/**
 * 循环右转字符串
 *
 * 形如：abcdef 右移前两位变成 cdefab
 *
 */
public class RevertString {

    public static void main(String[] args) {
        String source = "abcdef";
        System.out.println(revertString(source, 6, 6));
    }

    /**
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
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left ++;
            right --;
        }
    }
}
