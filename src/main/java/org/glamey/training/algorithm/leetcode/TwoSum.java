package org.glamey.training.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组中的两个数字相加等于指定值
 * https://leetcode.com/problems/two-sum/#/description
 * <p>
 * 复杂度O(n)
 *
 * @author zhouyang.zhou. 2017.06.28.15.
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        int[] result = new int[2];
        int len = nums.length, num;
        Map<Integer, Integer> map = new HashMap<>(len); // number,index
        for (int i = 0; i < len; i++) {
            num = nums[i];
            if (map.containsKey(target - num)) {
                result[0] = map.get(target - num);
                result[1] = i;
                return result;
            }
            map.put(num, i);
        }
        return result;
    }


    public static void main(String[] args) {
        TwoSum sum = new TwoSum();
        int[] indices = sum.twoSum(new int[]{1, 7, 2, 3, 5, 8, 12}, 11);
        if (indices == null) {
            System.out.println("no....");
        } else {
            for (int i : indices) {
                System.out.println(i);
            }
        }
    }
}