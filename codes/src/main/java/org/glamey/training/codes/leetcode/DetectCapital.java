package org.glamey.training.codes.leetcode;

/**
 * done 20240317
 * https://leetcode.cn/problems/detect-capital/description/
 * 检测大写字母
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 * 1、全部字母都是大写，比如 "USA" 。
 * 2、单词中所有字母都不是大写，比如 "leetcode" 。
 * 3、如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。
 * <p>
 * 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。
 */
public class DetectCapital {

    public static void main(String[] args) {
        System.out.println(detectCapitalUse("FFFFFFFFFFFFFFFFFFFFf"));
        System.out.println(detectCapitalUse("USA"));
        System.out.println(detectCapitalUse("leetcode"));
        System.out.println(detectCapitalUse("Google"));
        System.out.println(detectCapitalUse("flaG"));

    }

    public static boolean detectCapitalUse(String word) {
        if (word == null || word.length() <= 1) {
            return true;
        }
        boolean isLastUpper = isUpperCase(word.charAt(word.length() - 1));
        // 如果最后一个字符是大写，其他字符都必须是大写
        // 如果最后一个字符是小写，其他的都必须是小写(除了第一个字符，第一个字符大小写均可)
        if (isLastUpper) {
            for (int i = word.length() - 2; i >= 0; i--) {
                if (!isUpperCase(word.charAt(i))) {
                    return false;
                }
            }
        } else {
            for (int i = word.length() - 2; i > 0; i--) {
                if (!isLowerCase(word.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }


    private static boolean isUpperCase(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private static boolean isLowerCase(char c) {
        return c >= 'a' && c <= 'z';
    }
}
