package org.glamey.training.codes.leetcode;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * f(n) = f(n-1) + f(n-2) n >= 2
 *
 * @author zhouyang.zhou. 2017.07.27.19.
 */
public class Fibonacci {

    public static void main(String[] args) {
        byArray(10);
        bySwap(10);
        byRecursive(10);
    }


    /**
     * 数组方式构建
     *
     * @param n
     */
    public static void byArray(int n) {
        Stopwatch started = Stopwatch.createStarted();
        int[] arrays = new int[n];
        arrays[0] = arrays[1] = 1;
        for (int i = 2; i < arrays.length; i++) {
            arrays[i] = arrays[i - 2] + arrays[i - 1];
        }
        System.out.println("[array] elapsed ms -> " + started.elapsed(TimeUnit.MILLISECONDS));

        for (int array : arrays) {
            System.out.println(array);
        }
    }

    /**
     * 交换方式构建
     *
     * @param n
     */
    public static void bySwap(int n) {
        Stopwatch started = Stopwatch.createStarted();
        int a = 1, b = 1, c;
        System.out.println(a);
        System.out.println(b);
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
            System.out.println(c);
        }
        System.out.println("[swap] elapsed ms -> " + started.elapsed(TimeUnit.MILLISECONDS));
    }

    /**
     * 递归方式构建
     *
     * @param n
     */
    public static void byRecursive(int n) {
        Stopwatch started = Stopwatch.createStarted();
        for (int i = 0; i <= n; i++) {
            System.out.println(byRecursive_(i));
        }
        System.out.println("[recursive] elapsed ms -> " + started.elapsed(TimeUnit.MILLISECONDS));
    }

    private static int byRecursive_(int i) {
        if (i <= 2) {
            return 1;
        }
        return byRecursive_(i - 2) + byRecursive_(i - 1);
    }
}
