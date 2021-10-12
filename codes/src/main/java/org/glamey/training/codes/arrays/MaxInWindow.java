package org.glamey.training.codes.arrays;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * https://www.cxyxiaowu.com/6936.html
 * 滑动窗口中的最大值
 * 实现思路：
 * 一：通过一次数组遍历来实现。
 * 1、遍历数组，遍历的次数=nums.length - k
 * 2、循环里边再次循环，窗口大小是K，找到最大值添加到结果中。
 * 二：通过双端队列来实现。双端队列中保存的内容是数组的下标。
 * 1、双端队列中头部元素永远是大于尾部元素。
 * 2、如果长度=3的时候，直接取出头部元素即可。
 */
public class MaxInWindow {

    public static void main(String[] args) {
        int[] nums = new int[] {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        //通过一次遍历实现
        System.out.println(Arrays.toString(byLoop(nums, k)));
        //通过双端队列实现
        System.out.println(Arrays.toString(byDeque(nums, k)));
    }

    /**
     * 时间复杂度 O(Nk)
     */
    private static int[] byLoop(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        int len = nums.length, index = 0;
        int[] ret = new int[len - k + 1];
        for (int i = 0; i < len - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            ret[index++] = max;
        }
        return ret;
    }


    /**
     * 时间复杂度 O(N)
     *
     * @return @see java.util.List
     * 1, 3, -1, -3, 5, 3, 6, 7
     */
    private static int[] byDeque(int[] nums, int size) {
        if (nums == null || nums.length == 0 || size <= 0) {
            return new int[0];
        }
        int index = 0;
        int[] ret = new int[nums.length - size + 1];
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            //尾部添加元素，保证头部的元素值均大于尾部
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);

            //头部移除元素
            if (queue.peekFirst() == i - size) {
                queue.pollFirst();
            }
            //添加最终结果
            if (i >= size - 1) {
                ret[index++] = nums[queue.peekFirst()];
            }
        }
        return ret;
    }
}
