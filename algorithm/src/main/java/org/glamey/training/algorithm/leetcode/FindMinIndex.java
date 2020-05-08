package org.glamey.training.algorithm.leetcode;

import java.util.Arrays;

/**
 * 一个递增数组(有重复数字)，找到输入值的最小下标
 */
public class FindMinIndex {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 4, 4, 5, 7, 7, 10};
        System.out.println(findMinIndex(nums, 4));
        System.out.println(findMinIndex(nums, 1));
        System.out.println(findMinIndex(nums, 11));

        int index = Arrays.binarySearch(nums, 1);
        System.out.println(index);
    }

    private static int findMinIndex(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int index = findBinaryIndex(nums, target);
        if (index == -1) {
            return -1;
        }
        while (index >= 0) {
            if (nums[index] != target) {
                return index + 1;
            }
            index--;
        }
        return -1;
    }

    private static int findBinaryIndex(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            int midVal = nums[mid];
            if (midVal == target) {
                return mid;
            } else if (midVal > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private static int findBinaryIndex(int[] nums, int target, int left, int right) {
        while (left < right) {
            int mid = (left + right) >>> 1;
            int midVal = nums[mid];
            if (midVal == target) {
                return mid;
            } else if (midVal > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
