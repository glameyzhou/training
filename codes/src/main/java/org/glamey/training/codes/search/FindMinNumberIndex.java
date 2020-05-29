package org.glamey.training.codes.search;

/**
 * https://www.cxyxiaowu.com/463.html
 *
 * 一个递增数据，旋转其中一部分，找到旋转后的数组中最小的数据
 * 形如：[0,1,2,3,4,5] --> [4,5,0,1,2,3]
 */
public class FindMinNumberIndex {

    public static void main(String[] args) {
        System.out.println(findByDoublePointer(new int[]{4,5,0,1,2,3}));
        System.out.println(findByBinarySearch(new int[]{4,5,0,1,2,3}));
    }

    /**
     * 双指针的方式，时间复杂度O(n)
     * @param nums
     * @return
     */
    public static int findByDoublePointer(int[] nums) {
        if (nums == null) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] > nums[right]) {
                left ++;
            } else {
                right --;
            }
        }
        return left;
    }

    /**
     * 二分查找方式
     * 时间复杂度O(logn)
     * @param nums
     * @return
     */
    public static int findByBinarySearch(int[] nums) {
        if (nums == null) return -1;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] < nums[right]) return left;
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) left = mid + 1;
            else right = mid;
        }
        return left;
    }

}
