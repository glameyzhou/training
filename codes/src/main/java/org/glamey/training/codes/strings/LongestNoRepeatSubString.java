package org.glamey.training.codes.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * code = 03
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestNoRepeatSubString {

    public static void main(String[] args) {
        System.out.println(longestNoRepeatSubString("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(complex("abcabcbb"));
    }


    /**
     * 暴力方式
     */
    private static int complex(String source) {
        if (source == null || "".equals(source)) {
            return 0;
        }
        int maxLen = 0;
        char[] chars = source.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (process(chars, i, j)) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }

    private static boolean process(char[] chars, int i, int j) {
        Set<Character> set = new HashSet<>();
        for (int k = i; k <= j; k++) {
            if (!set.add(chars[k])) {
                return false;
            }
        }
        return true;
    }

    public static int lengthOfLongestSubstring(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }

        // 记录字符上次出现的位置
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        // 最近出现重复字符的位置
        int pre = -1;
        for (int i = 0, strLen = str.length(); i < strLen; i++) {
            Character ch = str.charAt(i);
            Integer index = map.get(ch);
            if (index != null) {
                pre = Math.max(pre, index);
            }
            max = Math.max(max, i - pre);
            map.put(ch, i);
        }
        return max;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * <p>
     * 1、遍历字符串，将出现过的字符存入字段中，key=字符，value=下标
     * 2、用maxLen保存遍历过程中出现的最大不重复的子串长度。
     * 3、用start保存最长子串的开始下标。
     * 4、如果字符已经在map中存在，更新start
     * 5、如果字符不在map中，更新maxLen
     * 6、返回maxLen
     */
    private static int longestNoRepeatSubString(String source) {
        if (source == null || "".equals(source)) {
            return -1;
        }
        Map<Character, Integer> map = new HashMap<>(); //key=字符，value=下标
        int start = 0, maxLen = 0;
        char[] chars = source.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i]) && start <= map.get(chars[i])) {
                start = map.get(chars[i]) + 1;
            } else {
                maxLen = Math.max(maxLen, i - start + 1);
            }
            map.put(chars[i], i);
        }
        return maxLen;
    }
}
