package org.glamey.training.codes.leetcode.dp;

/**
 * 阶乘
 * n! = (n-1)! * n
 * <p>
 * 0! = 1
 * <p>
 * 斐波纳切数列原理
 * <p>
 * factorial_1
 *
 * @author yang.zhou 2019.11.25.15
 */
public class Factorial {

    public static void main(String[] args) {
        Factorial factorial = new Factorial();
        System.out.println(factorial.factorial_1(0)); // 1
        System.out.println(factorial.factorial_1(1)); // 1
        System.out.println(factorial.factorial_1(2)); // 2
        System.out.println(factorial.factorial_1(3)); // 6
        System.out.println(factorial.factorial_1(4)); // 24


        System.out.println("-----------");

        System.out.println(factorial.factorial_2(0)); // 1
        System.out.println(factorial.factorial_2(1)); // 1
        System.out.println(factorial.factorial_2(2)); // 2
        System.out.println(factorial.factorial_2(3)); // 6
        System.out.println(factorial.factorial_2(4)); // 24
    }

    /**
     * 递归的方式实现斐波那契数列
     */
    public int factorial_1(int n) {
        // n! = (n-1)! * n

        if (n <= 1) {
            return 1;
        }
        return n * factorial_1(n - 1);
    }

    /**
     * 动态规划方式实现斐波那契数列
     */
    public int factorial_2(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int a = 1, b = 1;
        for (int i = 2; i <= n; i++) {
            b = a * i;
            a = b;
        }
        return b;
    }
}
