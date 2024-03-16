package org.glamey.training.codes.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 *
 */
public class FillTemperature {

    public static void main(String[] args) {
        int[] nums = new int[] {23, 25, 21, 19, 22, 26, 23};
        int[] ret = fillTemperature(nums);
        System.out.println(Arrays.toString(ret));  //1,  4, 2,  1,  1, 0,  0
    }

    private static int[] fillTemperature(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] ret = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                Integer index = stack.pop();
                ret[index] = i - index;
            }
            stack.push(i);
        }
/*
         这个可以删除掉，初始化中的int[]默认值均是0
        while (!stack.isEmpty()) {
            ret[stack.pop()] = 0;
        }
 */
        return ret;
    }
}
