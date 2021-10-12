package org.glamey.training.codes.arrays;

import java.util.Arrays;

import org.glamey.training.codes.Utils;

/**
 * 剑指Offer-21 调整数组顺序使奇数位于偶数前面
 * <p>
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 */
public class ExchangeOddEven {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(exchange(new int[] {1, 2, 3, 4})));
    }

    private static int[] exchange(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int low = 0, high = nums.length - 1;
        while (low < high) {
            while (low < high && nums[low] % 2 != 0) {
                low++;
            }

            while (low < high && nums[high] % 2 == 0) {
                high--;
            }
            Utils.swap(nums, low, high);
        }
        return nums;
    }
}
