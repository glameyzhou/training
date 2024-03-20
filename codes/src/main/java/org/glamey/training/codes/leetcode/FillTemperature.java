package org.glamey.training.codes.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 *
 * https://leetcode.cn/problems/daily-temperatures/
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer 。
 * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 */
public class FillTemperature {

    public static void main(String[] args) {
        //单调栈，时间复杂度是：O(n²)
        int[] nums = new int[] {23, 25, 21, 19, 22, 26, 23};
        int[] ret = fillTemperature(nums);
        System.out.println(Arrays.toString(ret));  //1,  4, 2,  1,  1, 0,  0

        //暴力方式获取，时间复杂度是:O(n²)，空间复杂度是：O(n)
        int[] doubleLoopArray = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] calRet = doubleLoop(doubleLoopArray);
        System.out.println(Arrays.toString(calRet)); //1,1,4,2,1,1,0,0
    }


    private static int[] doubleLoop(int[] temperatures) {
        int[] array = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i ++) {
            int cur = temperatures[i];
            for (int j = i + 1; j < temperatures.length; j ++) {
                int next = temperatures[j];
                if (next > cur) {
                    array[i] = j - i;
                    break;
                }
            }
        }
        return array;
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
