package org.glamey.training.algorithm.leetcode;

/**
 * 算出数组中，两两相减最大的数字
 *
 * @author zhouyang.zhou. 2017.08.18.22.
 */
public class MaxDiffer {

  public static int[] maxDiff(int[] nums) {
    if(nums.length < 2) {
      return null;
    }

    if(nums.length == 2) {
      return nums;
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
    return new int[]{min, max};
  }

  public static void main(String[] args) {
    int[] nums = {10, 4, 2, 20, 100, -2};
    int[] maxDiff = maxDiff(nums);
    int min = maxDiff[0];
    int max = maxDiff[1];
    System.out.printf("min=%d, max=%d, maxDiff=%d\n", min, max, (max - min));
  }
}
