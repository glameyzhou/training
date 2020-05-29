package org.glamey.training.codes.arrays;

import java.util.Arrays;

/**
 * https://www.cxyxiaowu.com/1450.html
 * <p>
 * 二维数组中查找是否存在一个元素
 * <p>
 * 二维数组特性：
 * 1、每行递增，从左至右。
 * 2、每列递增，从上至下。
 */
public class FindTwoDimensional {

    public static void main(String[] args) {
        int num = 1;
        int[][] nums = new int[4][3];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                nums[i][j] = num ++;
            }
        }
        for (int i = 0; i < 4; i++) {
            System.out.println(Arrays.toString(nums[i]));
        }
        System.out.println(nums.length);

        int target = 10;
        System.out.println(findByBinarySearch(nums, target));
        System.out.println(findByMath(nums, target));
    }

    /**
     * 时间复杂度O(m*logn)
     * 按照每行或者每列进行一次二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public static boolean findByBinarySearch(int[][] nums, int target) {
        if (nums == null) return false;
        for (int i = 0; i < nums.length; i++) {
            int binaryIndex = binarySearch(nums[i], target);
            if (binaryIndex != -1) return true;
        }
        return false;
    }
    private static int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }


    /**
     * 时间复杂度O(m+n)
     * 从左下角开始查询
     * row = nums.length - 1,
     * columns = 0
     * @param nums
     * @param target
     * @return
     */
    public static boolean findByMath(int[][] nums, int target) {
        if (nums == null) return false;
        int rows = nums.length - 1, columns = 0, tmp;
        while (rows >= 0 && columns <= nums[0].length - 1) {
            tmp = nums[rows][columns];
            if (target == tmp) return true;
            else if (target < tmp) columns ++;
            else rows --;
        }
        return false;
    }
}
