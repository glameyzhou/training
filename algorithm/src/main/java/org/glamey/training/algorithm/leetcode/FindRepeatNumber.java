package org.glamey.training.algorithm.leetcode;

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
        System.out.println(new FindRepeatNumber().findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}));
    }

    public int findRepeatNumber(int[] nums) {
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
