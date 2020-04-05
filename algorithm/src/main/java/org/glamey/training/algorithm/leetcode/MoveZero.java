package org.glamey.training.algorithm.leetcode;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * https://leetcode-cn.com/problems/move-zeroes/
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * @author yang.zhou 2019.11.11.15
 */
public class MoveZero {

    public static void main(String[] args) {
        List<int[]> list = Lists.newArrayList(
                new int[]{0, 1, 0, 3, 12},
                new int[]{1, 3, 12, 0, 0}
        );
        for (int[] array : list) {
            new MoveZero().moveZero(array);
            for (int i : array) {
                System.out.println(i);
            }
            System.out.println("---");
        }

    }

    public void moveZero(int[] array) {
        int lastNoZeroIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                array[lastNoZeroIndex++] = array[i];
            }
        }

        for (int i = lastNoZeroIndex; i < array.length; i++) {
            array[i] = 0;
        }
    }
}
