package org.glamey.training.codes.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组中缺少的两个数字
 * 0-n的递增数组中，找到缺失的两个数字。
 * 形如： [1] 缺少 [2,3]
 * [2,4] 缺少 [1,4]
 */
public class MissingTwoNumbers {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findTheMissingTwoNumbers(new int[] {1, 3})));
        System.out.println(Arrays.toString(findTheMissingTwoNumbers(new int[] {1})));
        System.out.println(Arrays.toString(findTheMissingTwoNumbers(new int[] {3})));
        System.out.println(Arrays.toString(findTheMissingTwoNumbers(new int[] {2, 4})));
    }

    private static int[] findTheMissingTwoNumbers(int[] nums) {
        if (nums == null) {
            return new int[] {1, 2};
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > i + 1) {
                for (int j = nums[i] - i; j < nums[i]; j++) {
                    list.add(j);
                }
            }
        }
        //0-1
        int curSize = list.size();
        for (int i = 1; i <= 2 - curSize; i++) {
            list.add(nums[nums.length - 1] + i);

        }
        int[] ret = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }
}
