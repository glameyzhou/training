package org.glamey.training.codes.arrays;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三个数的和为0，返回可能出现的不重复的组合
 * <p>
 * nums = [-1, 0, 1, 2, -1, -4]
 * <p>
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class ThreeSumEqualsZero {

    public static void main(String[] args) {
        int[] nums = new int[] {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> ret = threeSum(nums);
        System.out.println(ret);
    }

    private static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return list;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            int num = nums[i], target = 0 - num;
            List<Integer> subList = findTwoSum(nums, i, nums.length - 1, target);
            if (subList != null && subList.size() > 0) {
                subList.add(num);
                list.add(subList);
            }
        }
        return list;

    }

    private static List<Integer> findTwoSum(int[] nums, int left, int right, int target) {
        List<Integer> list;
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                list = new ArrayList<>();
                list.add(nums[left]);
                list.add(nums[right]);
                return list;
            } else if (nums[left] + nums[right] > target) {
                right--;
            } else {
                left++;
            }
        }
        return null;
    }
}
