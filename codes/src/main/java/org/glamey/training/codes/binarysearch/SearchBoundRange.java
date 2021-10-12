package org.glamey.training.codes.binarysearch;

import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * <p>
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SearchBoundRange {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchBoundRange(new int[] {5, 7, 7, 8, 8, 10}, 8)));
        System.out.println(Arrays.toString(searchBoundRange(new int[] {5, 7, 7, 8, 8, 10}, 6)));
    }

    /**
     * 左边界判定：
     * target == nums[mid] && (mid == 0 || nums[mid - 1] < target)
     * 右边界判定：
     * target == nums[mid] && (mid == nums.length - 1 || nums[mid+1] > target)
     */
    public static int[] searchBoundRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[] {-1, -1};
        }
        int leftBounder = searchLeftBound(nums, target, 0, nums.length - 1);
        if (leftBounder == -1) {
            return new int[] {-1, -1};
        }
        int rightBounder = searchRightBound(nums, target, leftBounder + 1, nums.length - 1);
        if (rightBounder == -1) {
            return new int[] {leftBounder, leftBounder};
        }
        return new int[] {leftBounder, rightBounder};
    }

    private static int searchLeftBound(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (target == nums[mid]
                && (mid == 0 || nums[mid - 1] < target)) {
            return mid;
        }
        if (target <= nums[mid]) { //注意 等号
            return searchLeftBound(nums, target, left, mid - 1);
        } else {
            return searchLeftBound(nums, target, mid + 1, right);
        }
    }

    private static int searchRightBound(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        /**
         * 判断是否是上边界时，
         * 先看看 middle 的数是否为 target，
         * 并判断该数是否已为数组的最后一个数，或者，它右边的数是不是比它大，如果都满足，即为上边界。    
         */
        if (target == nums[mid]
                && (mid == nums.length - 1 || nums[mid + 1] > target)) {
            return mid;
        }

        if (target <= nums[mid]) {
            return searchRightBound(nums, target, left, mid - 1);
        } else {
            return searchRightBound(nums, target, mid + 1, right);
        }
    }
}
