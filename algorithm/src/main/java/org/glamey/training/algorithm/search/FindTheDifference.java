package org.glamey.training.algorithm.search;

/**
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 *
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 *
 * 请找出在 t 中被添加的字母。
 *
 *  
 *
 * 示例:
 *
 * 输入：
 * s = "abcd"
 * t = "abcde"
 *
 * 输出：
 * e
 *
 * 解释：
 * 'e' 是那个被添加的字母。
 *
 * 题解：相当从一个大字符串中找出来不重复的一个字符。
 *
 * 通过异或来实现：两个相同字符异或=0，任何字符与0异或等于本身。因此所有字符异或后的值就是不重复的那个字符。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author yang.zhou 2020.01.07.13
 */
public class FindTheDifference {

    public static void main(String[] args) {
        char theDifference = new FindTheDifference().findTheDifference("abcd", "abcde");
        System.out.println(theDifference);
    }


    public char findTheDifference(String s, String t) {
        char diff = 0;
        for (int i = 0; i < s.length(); i++) {
            diff ^= s.charAt(i);
        }

        for (int i = 0; i < t.length(); i++) {
            diff ^= t.charAt(i);
        }

        return diff;
    }
}
