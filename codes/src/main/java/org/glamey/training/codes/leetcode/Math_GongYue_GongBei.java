package org.glamey.training.codes.leetcode;

/**
 * 最大公约数、最小公倍数
 * <p>
 * 最大公约数：几个自然数中共有的约数，叫这几个自然数的共有约数，其中最大的那个即为最大公约数
 */
public class Math_GongYue_GongBei {

    public static void main(String[] args) {
        System.out.println("最大公约数 -> " + getMaxCommonDivisor(222, 407));
        System.out.println("最小公倍数 -> " + getMinCommonMultiple(222, 407));
    }

    /**
     * 最小公倍数
     */
    public static int getMinCommonMultiple(int m, int n) {
        return m * n / getMaxCommonDivisor(m, n);
    }

    /**
     * 最大公约数
     */
    public static int getMaxCommonDivisor(int m, int n) {
        //用于保持 m >= n
        if (m < n) {
            int tmp = m;
            m = n;
            n = tmp;
        }

        if (m % n == 0) {
            return n;
        }
        while (m % n != 0) {  // 在余数不能为0时,进行循环
            int temp = m % n;
            m = n;
            n = temp;
        }
        return n;
    }
}
