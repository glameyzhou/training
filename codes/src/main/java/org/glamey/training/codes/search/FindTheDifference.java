package org.glamey.training.codes.search;

/**
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * <p>
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * <p>
 * 请找出在 t 中被添加的字母。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入：
 * s = "abcd"
 * t = "abcde"
 * <p>
 * 输出：
 * e
 * <p>
 * 解释：
 * 'e' 是那个被添加的字母。
 * <p>
 * 题解：相当从一个大字符串中找出来不重复的一个字符。
 * <p>
 * 通过异或来实现：两个相同字符异或=0，任何字符与0异或等于本身。因此所有字符异或后的值就是不重复的那个字符。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yang.zhou 2020.01.07.13
 */
public class FindTheDifference {

    public static void main(String[] args) {
        System.out.println(findByOX("abcd", "abcde"));
    }

    public static char findByOX(String s, String t) {
        char diff = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            diff ^= s.charAt(i) ^ t.charAt(i);
        }
        diff ^= t.charAt(len);
        return diff;
    }
}
