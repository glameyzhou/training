package org.glamey.training.codes.search;

/**
 * done 20240314
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * <p>
 * 字符串t由字符串s随机重排，然后在随机位置添加一个字母。
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
        System.out.println(findByXOR("abcd", "abcde"));
        System.out.println(findByXOR("ageef", "aegefc"));
        System.out.println(findByAscII_1("ageef", "aegefc"));
        System.out.println("findByAscII_2-->" + findByAscII_2("ageef", "aegefc"));
        char c = 'a';
        int i = c - 'a';
        System.out.println(i);

        int j = 2;
        char x = (char) (j + 'a');
        System.out.println(x);
    }


    public static char findByXOR(String s, String t) {
        char diff = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            diff ^= s.charAt(i) ^ t.charAt(i);
        }
        diff ^= t.charAt(len);
        return diff;
    }

    /**
     * ASCII表格：https://tool.oschina.net/commons?type=4
     * @param s
     * @param t
     * @return
     */
    public static char findByAscII_1(String s, String t) {
        int sSum = 0, tSum = 0;
        for (int i = 0; i < s.length(); i ++) {
            sSum += s.charAt(i);
        }
        for (int i = 0; i < t.length(); i ++) {
            tSum += t.charAt(i);
        }
        return (char) (tSum - sSum);
    }

    public static char findByAscII_2(String s, String t) {
        int[] ret = new int[26];
        for (int i = 0; i < s.length(); i ++) {
            ret[s.charAt(i) - 'a'] ++;
        }
        for (int i = 0; i < t.length(); i ++) {
            int index = t.charAt(i) - 'a';
            ret[index] --;
            if (ret[index] < 0) {
                return (char) (index + 'a');
            }
        }
        return 0;
    }
}
