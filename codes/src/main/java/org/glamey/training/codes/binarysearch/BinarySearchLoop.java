package org.glamey.training.codes.binarysearch;

/**
 * 非递归方式的二分查找
 * 时间复杂度：O(logn)
 * 核心步骤：
 * 1、确定搜索的范围和区间。
 * 2、取出中间数判定是否满足条件。
 * 3、如果不满足条件，判定应该去那个半边继续进行检索。
 */
public class BinarySearchLoop {
    public static void main(String[] args) {
        System.out.println(loopSearch(new int[]{1, 3, 4, 6, 7, 8, 10, 13, 14}, 7) == 4);
        System.out.println(loopSearch(new int[]{1, 3, 4, 6, 7, 8, 10, 13, 14}, 1) == 0);
        System.out.println(loopSearch(new int[]{1, 3, 4, 6, 7, 8, 10, 13, 14}, 9) == -1);
    }

    private static int loopSearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
