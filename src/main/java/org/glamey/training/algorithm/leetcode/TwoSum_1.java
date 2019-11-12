package org.glamey.training.algorithm.leetcode;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 数组中的两个数字相加等于指定值 https://leetcode.com/problems/two-sum/#/description <p> 复杂度O(n)
 *
 * @author zhouyang.zhou. 2017.06.28.15.
 */
public class TwoSum_1 {
    public static void main(String[] args) {
        TwoSum_1 sum = new TwoSum_1();
        int[] indices = sum.twoSum(new int[]{1, 7, 2, 3, 5, 8, 12}, 11);
        if (indices == null) {
            System.out.println("no....");
        } else {
            for (int i : indices) {
                System.out.println(i);
            }
        }
    }

    private int[] twoSum(int[] ints, int target) {
        if (ints == null || ints.length < 2) {
            return null;
        }
        Map<Integer, Integer> valueIndicesMapping = Maps.newHashMap();
        int tmp;
        for (int i = 0; i < ints.length; i++) {
            tmp = ints[i];
            if (valueIndicesMapping.containsKey(target - tmp)) {
                return new int[]{i, valueIndicesMapping.get(valueIndicesMapping.get(target - tmp))};
            } else {
                valueIndicesMapping.put(tmp, i);
            }
        }

        return null;
    }
}