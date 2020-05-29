package org.glamey.training.codes.arrays;

import java.util.Arrays;

/**
 * 重要的提示：递增的排序数组，通过这个线索，通过双指针的来实现。
 * 剑指Offer-57 和为s的两个数字
 * <p>
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * <p>
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 */
public class TwoSum_IncreaseNums {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int[] ret = twoSum_IncreaseNums(nums, 13);
        System.out.println(Arrays.toString(ret));
    }

    private static int[] twoSum_IncreaseNums(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return new int[0];

        int left = 0, right = nums.length - 1, tmp;
        while (left < right) {
            tmp = nums[left] + nums[right];
            if (tmp == target) {
                return new int[]{left, right};
            } else if (tmp < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
    }
}
