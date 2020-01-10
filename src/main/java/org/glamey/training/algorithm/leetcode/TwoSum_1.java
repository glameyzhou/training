package org.glamey.training.algorithm.leetcode;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 *
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
 *
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 *
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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