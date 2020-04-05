package org.glamey.training.algorithm.leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/contains-duplicate/
 * 给定一个整数数组，判断是否存在重复元素。
 * <p>
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 *
 * @author yang.zhou 2019.11.11.15
 */
public class ContainsDuplicate_1 {

    public static void main(String[] args) {
        System.out.println(new ContainsDuplicate_1().containsDuplicate(new int[]{1, 2, 3, 1}));
        System.out.println(new ContainsDuplicate_1().containsDuplicate(new int[]{1, 2, 3, 4}));
        System.out.println(new ContainsDuplicate_1().containsDuplicate(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
    }

    public boolean containsDuplicate(int[] array) {
        Arrays.sort(array);
        for (int i = 1; i < array.length; i++) {
            if (array[i] == array[i - 1]) {
                return true;
            }
        }
        return false;
    }
}
