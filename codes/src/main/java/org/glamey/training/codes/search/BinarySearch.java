package org.glamey.training.codes.search;

/**
 * @author zhouyang.zhou. 2017.03.13.10.
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 8};
        int index = binarySearch(nums, 5);
        System.out.println("index -> " + index);
    }


    public static int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 1) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
