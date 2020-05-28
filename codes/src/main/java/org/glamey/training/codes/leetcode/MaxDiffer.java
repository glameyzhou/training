package org.glamey.training.codes.leetcode;

/**
 * 算出数组中，两两相减最大的数字
 *
 * @author zhouyang.zhou. 2017.08.18.22.
 */
public class MaxDiffer {

  public static int maxDiff(int[] nums) {
    if(nums.length < 2) {
      return -1;
    }

    if(nums.length == 2) {
      return nums[0] > nums[1] ? nums[0] - nums[1] : nums[1] - nums[0];
    }

    int max = nums[0];
    int min = nums[0];

    int len = nums.length;
    int tmp;
    for (int i = 1; i < len; i++) {
      tmp = nums[i];
      max = tmp > max ? tmp : max;
      min = tmp > min ? min : tmp;
    }
    System.out.printf("min=%d, max=%d\n", min, max);
    return max - min;
  }

  public static void main(String[] args) {
    int[] nums = {10, 4, 2, 20, 100, -2};
    int maxDiff = maxDiff(nums);
    System.out.printf("maxDiff=%d\n", maxDiff);
  }
}
