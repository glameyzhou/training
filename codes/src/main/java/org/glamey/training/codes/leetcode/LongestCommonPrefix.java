package org.glamey.training.codes.leetcode;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串""。
 * <p>
 * 示例1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * <p>
 * 示例2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * 所有输入只包含小写字母a-z。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = new String[] {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs));
        System.out.println(longestCommonPrefix_2(strs));
    }

    public static String longestCommonPrefix_2(String[] arrs) {
        if (arrs == null || arrs.length == 0) {
            return "";
        }
        String prefix = arrs[0];
        for (int i = 1; i < arrs.length; i ++) {
            prefix = longestCommonPrefix_2_sub(prefix, arrs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    private static String longestCommonPrefix_2_sub(String a, String b) {
        int maxCommonPrefixLen = Math.min(a.length(), b.length());
        int p = 0;
        while (p < maxCommonPrefixLen && a.charAt(p) == b.charAt(p)) {
            p ++;
        }
        return a.substring(0, p);
    }


    public static String longestCommonPrefix(String[] source) {
        if (source == null || source.length == 0) {
            return "";
        }

        String ans = source[0];

        for (int i = 1; i < source.length; i++) {
            int j = 0;
            for (; j < ans.length() && j < source[i].length(); j++) {
                if (ans.charAt(j) != source[i].charAt(j)) {
                    break;
                }
            }
            ans = ans.substring(0, j);
            if (ans.equals("")) {
                return "";
            }
        }
        return ans;
    }
}
