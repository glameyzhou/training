package org.glamey.training.codes.strings;

import org.glamey.training.codes.Utils;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * <p>
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 * 通过次数282,046提交次数922,997
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        System.out.println(byComplex("babad"));
        System.out.println(byComplex("cbbd"));

        System.out.println(bySpread("babad"));
        System.out.println(bySpread("cbbd"));

        System.out.println(bySplit("babad"));
        System.out.println(bySplit("cbbd"));
    }
    /*************************************【暴力循环方式，遍历所有的字符串，然后判定每个字符串是否为回文】*****************************************************/
    /**
     * 暴力方式求解
     * 时间复杂度为O(N^3)
     *
     * @param source
     * @return
     */
    public static String byComplex(String source) {
        if (Utils.isBlank(source) || source.length() < 2) {
            return source;
        }

        int start = 0, maxLen = 0;
        char[] chars = source.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (maxLen < j - i + 1 && isPalindrome(chars, i, j)) {
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }
        return source.substring(start, start + maxLen);
    }

    private static boolean isPalindrome(char[] chars, int left, int right) {
        while (left <= right) {
            if (chars[left] != chars[right]) return false;
            left++;
            right--;
        }
        return true;
    }


    /**************************************[中心往外扩充的方式]****************************************************/
    /**
     * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
     * 中心扩散的原理
     * 枚举所有中心O(N),中心扩散判定回文需要O(N)，总体时间复杂度为O(N^2)
     * <p>
     * 奇数个元素，中心是实际的元素。
     * 偶数个元素，中心是一个间隙。
     *
     * @param source
     * @return
     */
    public static String bySpread(String source) {
        if (Utils.isBlank(source) || source.length() < 2) {
            return source;
        }
        int maxLen = 1;
        String res = source.substring(0, 1);
        for (int i = 0; i < source.length(); i++) {
            //下标重合，返回的字符长度为奇数个
            String oddString = contentSpread(source, i, i);
            //下标不重复，返回的字符长度为偶数个
            String evenString = contentSpread(source, i, i + 1);
            String maxString = oddString.length() > evenString.length() ? oddString : evenString;
            if (maxString.length() > maxLen) {
                maxLen = maxString.length();
                res = maxString;
            }
        }
        return res;
    }

    private static String contentSpread(String source, int left, int right) {
        /**
         * eft = right 的时候，此时回文中心是一个字符，回文串的长度是奇数
         * right = left + 1 的时候，此时回文中心是一个空隙，回文串的长度是偶数
         */
        int i = left, j = right, len = source.length();
        while (i >= 0 && j < len) {
            if (source.charAt(i) != source.charAt(j)) {
                break;
            }
            i--;
            j++;
        }
        return source.substring(i + 1, j); //include startIndex, exclude endIndex
    }

    /***************************************【每个字符前后插入特殊字符的方式，这样不用判定是否为偶数个字符】***************************************************/
    public static String bySplit(String source) {
        if (Utils.isBlank(source) || source.length() < 2) {
            return source;
        }
        char[] chars = generateBySplit(source);
        /**
         * # b # a # b # c #
         * 0 1 2 3 4 5 6 7 8
         * 0 3 0 7 0 3 0 3 0
         *
         */
        int start = 0, maxLen = 0;
        for (int i = 0; i < chars.length; i++) {
            int maxPalindrome = maxPalindrome(chars, i);
            if (maxLen < maxPalindrome) {
                maxLen = maxPalindrome;
                start = i;
            }
        }
        return new String(chars, start, maxLen);
    }

    /**
     * # b # a # b #
     *
     * @param chars
     * @param index
     * @return
     */
    private static int maxPalindrome(char[] chars, int index) {
        if (index <= 0) return 0;
        int i = index, j = index;
        int count = 0;
        while (i >= 0 && j < chars.length) {
            if (chars[i--] != chars[j++]) {
                break;
            }
            count++;
        }
        return (count - 1) * 2 + 1; // count - 1=中间值需要去掉
    }

    private static char[] generateBySplit(String source) {
        char[] chars = new char[source.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < source.length(); i++) {
            chars[index++] = '#';
            chars[index++] = source.charAt(i);
        }
        chars[index++] = '#';
        return chars;
    }
}


