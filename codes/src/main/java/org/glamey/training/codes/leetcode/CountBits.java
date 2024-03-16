package org.glamey.training.codes.leetcode;

import java.util.Arrays;

/**
 * done 20240315
 * https://leetcode.cn/problems/counting-bits/description/
 * <p>
 * 比特位计数
 *
 * @author zhouyang281
 * @date 2024-03-14
 */
public class CountBits {

    public static void main(String[] args) {
        int[] ret = countBits(5);
        System.out.println(Arrays.toString(ret));
        System.out.println(Arrays.equals(ret, new int[]{0, 1, 1, 2, 1, 2}));
    }

    public static int[] countBits(int n) {
        int[] ret = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            ret[i] = calBit(i);
        }
        return ret;
    }

    public static int calBit(int n) {
        int count = 0;
        while (n > 0) {
            if (n % 2 == 1) {
                count++;
            }
            n /= 2;
        }
        return count;
    }
}
