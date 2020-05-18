package org.glamey.training.algorithm.strings;

/**
 * 给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
 * <p>
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *  
 * <p>
 * 示例:
 * <p>
 * 输入: s = "abcdefg", k = 2
 * 输出: "bacdfeg"
 *  
 * <p>
 * 提示：
 * <p>
 * 该字符串只包含小写英文字母。
 * 给定字符串的长度和 k 在 [1, 10000] 范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-string-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseStringV2 {

    public static void main(String[] args) {
        String source = "abcdefg";
        int k = 2;
        System.out.println(revertStringV2(source, k));
    }

    private static String revertStringV2(String source, int k) {
        char[] chars = source.toCharArray();
        for (int start = 0; start < chars.length - 1; start += 2 * k) {
            int m = start, n = Math.min(start + k - 1, chars.length - 1);
            revertString(chars, m,n, k);
        }
        return new String(chars);
    }


    /**
     * 只用翻转前K个字符即可
     *
     * @param chars
     * @param start
     * @param end
     * @param k
     */
    private static void revertString(char[] chars, int start, int end, int k) {
        if (start >= end || k <= 1 || chars == null) {
            return;
        }
        doRevertString(chars, start, start + k - 1);
    }

    private static void doRevertString(char[] chars, int start, int end) {
        while (start < end) {
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;
            end--;
        }

    }
}
