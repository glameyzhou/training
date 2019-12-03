package org.glamey.training.algorithm.leetcode;

import java.util.*;

/**
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * "tree"
 * <p>
 * 输出:
 * "eert"
 * <p>
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 * <p>
 * 输入:
 * "cccaaa"
 * <p>
 * 输出:
 * "cccaaa"
 * <p>
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 示例 3:
 * <p>
 * 输入:
 * "Aabb"
 * <p>
 * 输出:
 * "bbAa"
 * <p>
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-characters-by-frequency
 *
 * @author yang.zhou 2019.12.03.15
 */
public class SortCharactersByFrequency {

    public static void main(String[] args) {
        System.out.println(frequencySort("tree"));
        System.out.println(frequencySort("cccaaa"));
        System.out.println(frequencySort("Aabb"));

        System.out.println(frquencySort_("tree"));
        System.out.println(frquencySort_("cccaaa"));
        System.out.println(frquencySort_("Aabb"));
    }


    /**
     * tree map value comparator
     *
     * @param s
     * @return
     */
    public static String frquencySort_(String s) {
        if (s == null || "".equals(s)) {
            return null;
        }
        TreeMap<Character, Integer> map = new TreeMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        ArrayList<Map.Entry<Character, Integer>> entries = new ArrayList<>(map.entrySet());
        Collections.sort(entries, (a, b) -> b.getValue() - a.getValue());

        StringBuffer sb = new StringBuffer(s.length());
        for (Map.Entry<Character, Integer> entry : entries) {
            for (int i = 0; i < entry.getValue(); i++) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }

    /**
     * 内存超限
     *
     * @param s
     * @return
     */
    public static String frequencySort(String s) {
        if (s == null || "".equals(s)) {
            return null;
        }

        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> queue = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character a, Character b) {
                return map.get(b) - map.get(a);
            }
        });
        for (Character c : map.keySet()) {
            queue.offer(c);
        }

        StringBuffer sb = new StringBuffer(s.length());
        while (!queue.isEmpty()) {
            Character poll = queue.poll();
            Integer count = map.get(poll);
            while (count > 0) {
                sb.append(poll);
                count--;
            }
        }
        return sb.toString();
    }
}
