package org.glamey.training.algorithm.search;

/**
 * 一个数据，特点是一部分依次递增 ，一部分依次递减，找到最大值的下标。
 * 如果未找到返回-1
 *
 * @author yang.zhou 2019.12.16.14
 */
public class FindMaxNumIndex {

    public static void main(String[] args) {
        int[] array = {0, 1, 2, 3, 4, 5, 4, 3};
        System.out.println(findMaxNumberIndexInArray(array));
        System.out.println(getMaxNumberIndex(new int[]{1, 2, 3, 3, 2, 1}));
        System.out.println(getMaxNumberIndex(new int[]{1, 2, 3, 1}));
        System.out.println(getMaxNumberIndex(new int[]{3, 1}));
        System.out.println(getMaxNumberIndex(new int[]{3}));
    }

    /**
     * 双指针的方式来搞定
     *
     * @param nums
     * @return
     */
    private static int getMaxNumberIndex(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] > nums[right]) {
                right--;
            } else {
                left++;
            }
        }
        return left;
    }

    // 目标是 array[i] > array[i + 1] && array[i] > array[i - 1]
    public static final int findMaxNumberIndexInArray(int[] array) {
        int len = array.length, low = 0, high = len - 1, mid = low + (high - low) >>> 1;
        while (mid > 0 && mid < len - 1) {
            if (array[mid] > array[mid + 1] && array[mid] > array[mid - 1]) {
                return mid;
            } else if (array[mid] > array[mid - 1]) {
                low = mid + 1;
                mid = (low + high) >>> 1;
            } else {
                high = mid - 1;
                mid = (low + high) >>> 1;
            }
        }
        return -1;
    }


}
