package org.glamey.training.codes.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/subarray-sum-equals-k/?envType=study-plan-v2&envId=top-100-liked">和为K的子数组</a>
 * <p>
 * 和为K的子数组
 *
 * @author zhouyang281
 * @date 2024-03-04
 */
public class SubarraySum {

    public static void main(String[] args) {
        int[] nums = {1, -1, 0};
        int k = 0;
        System.out.println(subArraySum(nums, k));
        System.out.println(subArraySumV2(nums, k));
    }

    // 时间复杂度O(n1)
    private static int subArraySumV2(int[] nums, int k) {
        int count = 0, sum = 0;
        // key 数组和为0， value = 出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    // 时间复杂度：O(n²)
    private static int subArraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 1, -1, 0
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
