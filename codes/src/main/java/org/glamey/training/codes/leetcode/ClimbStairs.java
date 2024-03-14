package org.glamey.training.codes.leetcode;

/**
 * done 20240314
 * <p>
 * https://leetcode.cn/problems/climbing-stairs/description/
 * <p>
 * 爬楼梯，每次只能爬1或者2个台阶，问N层台阶共多少种方式
 *
 * @author zhouyang281
 * @date 2024-03-12
 */
public class ClimbStairs {


    public static void main(String[] args) {
        int n = 10;
        System.out.println(climbStairs(n));
        System.out.println(climbStairs_recursion(10));
    }

    private static int climbStairs(int n) {
        int a = 1, b = 1, sum;
        for (int i = 0; i < n - 1; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    /**
     * 递归暴力破解，执行失败
     *
     * @param n
     * @return
     */
    private static int climbStairs_recursion(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return climbStairs_recursion(n - 1) + climbStairs_recursion(n - 2);
        }
    }
}
