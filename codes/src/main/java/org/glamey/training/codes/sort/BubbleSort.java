package org.glamey.training.codes.sort;


import org.glamey.training.codes.Utils;

import java.util.Arrays;

/**
 * @author zhouyang.zhou. 2017.03.13.11.
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 8, 5, 4};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }


    public static void sort(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    Utils.swap(nums, j, j + 1);
                }
            }
        }
    }
}
