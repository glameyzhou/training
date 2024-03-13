package org.glamey.training.codes.search;


/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 121
 * 输出: true
 * 示例2:
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
        System.out.println("121 -> " + isEchoCycle(121));
        System.out.println("-121 -> " + isEchoCycle(-121));
        System.out.println("10 -> " + isEchoCycle(10));
        System.out.println("4 -> " + isEchoCycle(4));
        System.out.println("0 -> " + isEchoCycle(0));
        System.out.println("12321 -> " + isEchoCycle(12321));
    }


    public static boolean isEchoCycle(int n) {
        if (n < 0 || (n > 0 && n % 10 == 0)) {
            return false;
        }

        int r = 0;
        while (n > r) {
            r = r * 10 + n % 10;
            n /= 10;
        }
        /**
         * 偶数个数字 1221  r = 12, n = 12
         * 奇数个数字 12321 r = 123 n = 12
         */
        return r == n || r / 10 == n;
    }
}
