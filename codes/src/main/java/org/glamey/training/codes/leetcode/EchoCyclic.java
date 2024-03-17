package org.glamey.training.codes.leetcode;

import org.glamey.training.codes.Utils;

/**
 * done 20240317
 * 是否为回文字符，忽略空格、非字母、大小写
 */
public class EchoCyclic {

    public static void main(String[] args) {
        System.out.println(isEchoCyclic("A man, a plan, a canal: Panama"));
        System.out.println(isEchoCyclic("race a car"));
        System.out.println(isEchoCyclic("  A "));


        System.out.println(isEchoCyclicIgnoreCase("A man, a plan, a canal: Panama"));
        System.out.println(isEchoCyclicIgnoreCase("race a car"));
        System.out.println(isEchoCyclicIgnoreCase("  A "));
    }

    public static boolean isEchoCyclicIgnoreCase(String source) {
        if (source == null || source.length() < 2) {
            return true;
        }
        int left = 0, right = source.length() - 1;
        while (left < right) {
            char a = source.charAt(left), b = source.charAt(right);
            if (Character.isSpaceChar(a) || !isLetter(a)) {
                left++;
                continue;
            }
            if (Character.isSpaceChar(b) || !isLetter(b)) {
                right--;
                continue;
            }
            if (left >= right) {
                return false;
            }

            if (!isCharEqualIgnoreCase(a, b)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private static boolean isCharEqualIgnoreCase(char a, char b) {
        if (a == b) {
            return true;
        }
        // a 大写， b 小写
        if ((isUpper(a) && isLower(b) && ((char) ((int) a + 32)) == b)) {
            return true;
        }
        // a 小写 b大写
        if (isLower(a) && isUpper(b) && ((char) ((int) a - 32)) == b) {
            return true;
        }
        return false;
    }

    private static boolean isUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private static boolean isLower(char c) {
        return c >= 'a' && c <= 'z';
    }

    private static boolean isLetter(char c) {
        return isUpper(c) || isLower(c);
    }


    private static boolean isEchoCyclic(String source) {
        if (Utils.isBlank(source)) {
            return true;
        }
        source = source.toLowerCase();
        int left = 0, right = source.length() - 1;
        while (left < right) {
            while (source.charAt(left) > 'z' || source.charAt(left) < 'a') {
                left++;
            }
            while (source.charAt(right) > 'z' || source.charAt(right) < 'a') {
                right--;
            }
            if (left == right) {
                return true;
            }
            if (left > right) {
                return false;
            }
            if (source.charAt(left) != source.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
