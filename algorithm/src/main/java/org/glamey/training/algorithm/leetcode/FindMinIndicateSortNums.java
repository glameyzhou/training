package org.glamey.training.algorithm.leetcode;


/**
 * 一个递增数组（包含了重复数字），找到指定数字的最小下标
 */
public class FindMinIndicateSortNums {

    public static void main(String[] args) {
        System.out.println(findMinIndex(new int[]{1, 2, 3, 3, 3, 4, 5, 5, 6, 7}, 3) == 2);
        System.out.println(findMinIndex(new int[]{1, 2, 3, 3, 3, 4, 5, 5, 6, 7}, 10) == -1);
        System.out.println(findMinIndex(new int[]{0, 1, 1, 1, 1, 2}, 1) == 1);
    }


    private static int findMinIndex(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int binarySearchIndex, left = 0, right = nums.length - 1, lastIndex = -1; //上一次查询的位置
        do {
            binarySearchIndex = binarySearch(nums, target, left, right);
            if (binarySearchIndex == 0) {
                return 0;
            }
            if (binarySearchIndex == -1) {
                return lastIndex != -1 ? lastIndex : binarySearchIndex;
            }
            right = binarySearchIndex - 1;
            lastIndex = binarySearchIndex;
        } while (binarySearchIndex >= 0);
        return -1;
    }

    private static int binarySearch(int[] nums, int target, int left, int right) {
        int midIndex, midVal;
        while (left <= right) {
            midIndex = (left + right) >>> 1;
            midVal = nums[midIndex];
            if (midVal < target) {
                left = midIndex + 1;
            } else if (midVal > target) {
                right = midIndex - 1;
            } else {
                return midIndex;
            }
        }
        return -1;
    }
}
