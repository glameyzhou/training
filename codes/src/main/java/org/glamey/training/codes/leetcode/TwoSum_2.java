package org.glamey.training.codes.leetcode;

/**
 * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
 * <p>
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * <p>
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * <p>
 * 说明:
 * <p>
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 * <p>
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 * @author yang.zhou 2019.11.11.14
 */
public class TwoSum_2 {

    public static void main(String[] args) {
        int[] indicates = new TwoSum_2().twoSum(new int[]{2, 7, 11, 15}, 9);
        for (int indicate : indicates) {
            System.out.println(indicate);
        }
    }

    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null) {
            return new int[0];
        }
        int low = 0, high = numbers.length - 1, sum;
        while (low < high) {
            sum = numbers[low] + numbers[high];
            if (sum > target) {
                high--;
            } else if (sum < target) {
                low++;
            } else {
                return new int[]{low + 1, high + 1};
            }
        }
        return new int[0];
    }
}
