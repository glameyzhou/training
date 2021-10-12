package org.glamey.training.codes.leetcode;

/**
 * 算出数组中，两两相减最小的数字
 *
 * @author zhouyang.zhou. 2017.08.18.22.
 */
public class MinDiffer {

    public static int minDiff(int[] nums) {

        if (nums == null) {
            return -1;
        }

        int len = nums.length;
        if (len < 2) {
            return -1;
        }

        if (len == 2) {
            return diff(nums[0], nums[1]);
        }

        int minDiff = 0, curDiff = 0;
        for (int i = 1; i < len; i++) {
            curDiff = diff(nums[i - 1], nums[i]);
            if (minDiff >= curDiff) {
                minDiff = curDiff;
            }
        }
        return minDiff;
    }

    private static int diff(int a, int b) {
        if (a >= b) {
            return b - a;
        }
        return a - b;
    }

    public static void main(String[] args) {
        int[] nums = {10, 4, 2, 20, 100, -2};
        int minDiff = minDiff(nums);
        System.out.printf("mindDiff=%d\n", minDiff);
    }
}
