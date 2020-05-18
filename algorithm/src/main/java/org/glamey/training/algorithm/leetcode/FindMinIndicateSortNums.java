package org.glamey.training.algorithm.leetcode;


/**
 * 一个递增数组（包含了重复数字），找到指定数字的最小下标
 */
public class FindMinIndicateSortNums {

    public static void main(String[] args) {
        System.out.println(findMinIndex(new int[]{1, 2, 3, 3, 3, 4, 5, 5, 6, 7}, 3));
        System.out.println(findMinIndex(new int[]{1, 2, 3, 3, 3, 4, 5, 5, 6, 7}, 10));
        System.out.println(findMinIndex(new int[]{0, 1, 1, 1, 1, 2}, 1));
    }

    private static int findMinIndex(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int targetIndex, left = 0, right = nums.length - 1, lastIndex = -1; //上一次查询的位置
        do {
            targetIndex = binarySearch(nums, target, left, right);
            if (targetIndex == 0) {
                return 0;
            }
            if (targetIndex == -1) {
                return lastIndex != -1 ? lastIndex : targetIndex;
            }
            right = targetIndex - 1;
            lastIndex = targetIndex;
        } while (targetIndex >= 0);
        return -1;
    }

    private static int binarySearch(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = (left + right) >>> 1;
            int midVal = nums[mid];
            if (midVal < target) {
                left = mid + 1;
            } else if (midVal > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
