package org.glamey.training.codes.binarysearch;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * <p>
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 * 通过次数125,688提交次数331,051
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        System.out.println(search(new int[] {3, 4, 5, 6, 7, 0, 1, 2, 2}, 0));
    }

    /**
     * 查询有序数组旋转后的指定数字下标
     */
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    private static int binarySearch(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            return mid;
        }

        //判断左边是不是排好序
        if (nums[left] <= nums[mid]) {
            //是，判定目标是不是在左侧
            if (nums[left] <= target && target < nums[mid]) {
                //是在左侧，继续在左侧二分搜索
                return binarySearch(nums, target, left, mid - 1);
            }
            //不在左侧，在右侧范围搜索
            return binarySearch(nums, target, mid + 1, right);
        }
        //如果右侧是排好序的
        else {
            //如果目标值在右侧，继续在右侧二分查找
            if (nums[mid] < target && target <= nums[right]) {
                return binarySearch(nums, target, mid + 1, right);
            }
            //否则，直接在左侧二分查找
            return binarySearch(nums, target, left, mid - 1);
        }
    }
}
