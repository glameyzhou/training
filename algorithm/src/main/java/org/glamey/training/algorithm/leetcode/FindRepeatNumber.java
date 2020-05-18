package org.glamey.training.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 找出数组中重复的数字。
 * <p>
 * <p>
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *  
 * <p>
 * 限制：
 * <p>
 * 2 <= n <= 100000
 * <p>
 * 通过次数42,201提交次数62,962
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 */
public class FindRepeatNumber {

    public static void main(String[] args) {
        System.out.println(findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}));
        System.out.println(findRepeatNumber_v2(new int[]{2, 3, 1, 0, 2, 5, 3}));
        System.out.println(findRepeatNumber_v3(new int[]{2, 3, 1, 0, 2, 5, 3}));
    }


    /**
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     * 在排序后的情况下，index = nums[index]。
     * 如果index != nums[index], 那么讲当前值，与nums[index]下标的值进行交换
     * @param nums
     * @return
     */
    private static int findRepeatNumber_v3(int[] nums) {
        if (nums == null || nums.length == 1) {
            return -1;
        }
        /**
         * 2, 3, 1, 0, 2, 5, 3
         * 1, 3, 2, 0, 2, 5, 3
         * 3, 1, 2, 0, 2, 5, 3
         * 0, 1, 2, 3, 2, 5, 3
         */
        for (int i = 0; i < nums.length; i++) {
            while (i != nums[i]) {
                int tmp = nums[nums[i]];
                if (nums[i] ==  tmp) {
                    return tmp;
                }
                nums[nums[i]] = nums[i];
                nums[i] = tmp;
            }
        }
        return -1;
    }
    /**
     * O(N*logN)
     * @param nums
     * @return
     */
    private static int findRepeatNumber_v2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (i != nums[i]) {
                return nums[i];
            }
        }
        return -1;
    }

    public static int findRepeatNumber(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return -1;
        }
        HashSet<Integer> set = new HashSet<>(nums.length);
        for (Integer integer : nums) {
            if (!set.add(integer)) {
                return integer;
            }
        }
        return -1;
    }
}
