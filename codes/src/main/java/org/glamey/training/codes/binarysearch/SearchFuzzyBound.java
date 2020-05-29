package org.glamey.training.codes.binarysearch;

/**
 * 模糊边界
 * 二分搜索可以用来查找一些模糊的边界。模糊的边界指，边界的值并不等于目标的值，而是大于或者小于目标的值。
 * 例题：
 * 1、从数组 {search-in-rotated-sorted-array} 中找到第一个大于 6 的数。
 * 2、从数组 {-2, 0, 1, 4, 4, 6, 6, 7, 9, 10} 中找到第一个小于 6 的数。
 */
public class SearchFuzzyBound {

    public static void main(String[] args) {
        int[] nums = {-2, 0, 1, 4, 4, 6, 6, 7, 9, 10};
        System.out.println(getFirstGreaterThan(nums, 6, 0, nums.length - 1));
        System.out.println(getFirstLessThan(nums, 6, 0, nums.length - 1));
    }

    /**
     * 找到第一个小于target的值，必须满足以下条件：
     * 1、mid值小于target。
     * 2、mid为最后一个数字，或者mid之后的数字都大于或者等于target
     *
     * @param nums
     * @param target
     * @param left
     * @param right
     * @return
     */
    private static int getFirstLessThan(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (nums[mid] < target
                && (mid == nums.length - 1 || nums[mid + 1] >= target)) {
            return nums[mid];
        }

        if (nums[mid] > target) {
            return getFirstLessThan(nums, target, left, mid - 1);
        } else {
            return getFirstLessThan(nums, target, mid + 1, right);
        }
    }


    /**
     * 找到第一个比target大的值。
     * 必须满足以下条件：
     * 1、mid值大于target
     * 2、mid为第一个数字，或者mid之前的数子小于等于target
     *
     * @param nums
     * @param target
     * @param left
     * @param right
     * @return
     */
    private static int getFirstGreaterThan(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (target < nums[mid]
                && (mid == 0 || nums[mid - 1] <= target)) {
            return nums[mid];
        }

        if (target < nums[mid]) {
            return getFirstGreaterThan(nums, target, left, mid - 1);
        } else {
            return getFirstGreaterThan(nums, target, mid + 1, right);
        }
    }
}
