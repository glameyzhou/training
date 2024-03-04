package org.glamey.training.codes.leetcode;

import org.glamey.training.codes.Utils;

/**
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "aba"
 * 输出: True
 * 示例 2:
 * <p>
 * 输入: "abca"
 * 输出: True
 * 解释: 你可以删除c字符。
 * 注意:
 * <p>
 * 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome-ii
 */
public class EchoCyclic2 {

    public static void main(String[] args) {
        System.out.println(isEchoCyclic2("aba"));
        System.out.println(isEchoCyclic2("cabcacbeba"));

        System.out.println(process("aba"));
        System.out.println(process("cabcacbeba"));

    }


    public static boolean process(String source) {
        if (source == null || source.length() < 2) {
            return true;
        }
        int left = 0, right = source.length() - 1;
        while (left < right) {
            char leftVal = source.charAt(left);
            char rightVal = source.charAt(right);
            if (leftVal != rightVal) {
                // 跳过左边一个字符、跳过右边一个字符
                return skipCharProcess(source, left + 1, right) || skipCharProcess(source, left, right - 1);

            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean skipCharProcess(String source, int left, int right) {
        while (left < right) {
            char leftVal = source.charAt(left);
            char rightVal = source.charAt(right);
            if (leftVal != rightVal) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    private static boolean isEchoCyclic2(String source) {
        if (Utils.isBlank(source) || source.length() < 2) {
            return true;
        }
        int left = 0, right = source.length() - 1;
        while (left < right) {
            if (source.charAt(left) != source.charAt(right))
            //尝试跳过左边，或者跳过右边
            {
                return processEchoCyclic(source, left, right - 1) || processEchoCyclic(source, left + 1, right);
            }
            left++;
            right--;
        }
        return true;
    }

    private static boolean processEchoCyclic(String source, int left, int right) {
        while (left < right) {
            if (source.charAt(left) != source.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
