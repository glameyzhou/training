package org.glamey.training.codes.leetcode;

import java.util.LinkedHashMap;

/**
 * 【字符都是小写】
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 * 案例:
 * <p>
 * s = "leetcode"
 * 返回 0.
 * <p>
 * s = "loveleetcode",
 * 返回 2.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string
 * <p>
 * <p>
 * 解析思路：都是字母，因此声明长度为26的数组，分别对应26个英文字母。
 * <p>
 * 1、第一遍：循环N，将每一个字母放入对应的下标中，并且进行++操作。
 * 2、第二遍：最多遍历26次，当前下标的值为1，直接跳出循环。
 *
 * @author yang.zhou 2019.12.03.15
 */
public class FirstUniqueCharacterInString {


    public static void main(String[] args) {
        System.out.println(firstUniqChar("leetcode"));
        System.out.println(firstUniqChar("loveleetcode"));

        System.out.println(firstUniqCharacter("leetcode"));
        System.out.println(firstUniqCharacter("loveleetcode"));


        String source = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 26; i++) {
            int num = source.charAt(i);
            System.out.println(source.charAt(i) + " --> " + num);
        }

        String source_ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 26; i++) {
            int num = source_.charAt(i);
            System.out.println(source_.charAt(i) + " --> " + num);
        }
    }


    /**
     * 直接通过char操作
     */
    public static int firstUniqCharacter(String source) {
        if (source == null || "".equals(source)) {
            return -1;
        }
        char[] chars = source.toCharArray();
        int[] array = new int[26];
        for (char c : chars) {
            array[c - 'a']++;
        }

        for (int i = 0; i < chars.length; i++) {
            if (array[chars[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }


    public static int firstUniqChar(String s) {
        if (s == null || "".equals(s)) {
            return -1;
        }

        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }

        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == 1) {
                return i;
            }
        }


        return -1;
    }
}
