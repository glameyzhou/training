package org.glamey.training.algorithm.search;

import org.springframework.util.Assert;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 121
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 * <p>
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 * <p>
 * 你能不将整数转为字符串来解决这个问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yang.zhou 2020.01.09.21
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        Assert.isTrue(new PalindromeNumber().isPalindrome(121));
        Assert.isTrue(!new PalindromeNumber().isPalindrome(-121));
        Assert.isTrue(!new PalindromeNumber().isPalindrome(10));
        Assert.isTrue(new PalindromeNumber().isPalindrome(4));
        Assert.isTrue(new PalindromeNumber().isPalindrome(0));
        Assert.isTrue(new PalindromeNumber().isPalindrome(1));
    }

    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int r = 0;
        while (x > r) {
            r = r * 10 + x % 10;
            x /= 10;
        }
        return x == r || x == r / 10;
    }
}
