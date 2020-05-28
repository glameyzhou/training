package org.glamey.training.codes.arrays;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 滑动窗口中的最大值
 */
public class MaxInWindow {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        List<Integer> list = maxInWindowByLoop(nums, k);
        System.out.println(list);

        list = maxInWindowByDeque(nums, k);
        System.out.println(list);
    }

    /**
     * 时间复杂度 O(Nk)
     *
     * @param nums
     * @param k
     * @return
     */
    private static List<Integer> maxInWindowByLoop(int[] nums, int k) {
        ArrayList<Integer> list = new ArrayList<>();

        if (nums == null || nums.length == 0 || k <= 0) {
            return list;
        }
        int len = nums.length;
        for (int i = 0; i < len - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            list.add(max);
        }
        return list;
    }


    /**
     * 时间复杂度 O(N)
     *
     * @param nums
     * @param size
     * @return @see java.util.List
     * 1, 3, -1, -3, 5, 3, 6, 7
     */
    private static List<Integer> maxInWindowByDeque(int[] nums, int size) {
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0 || size <= 0) {
            return list;
        }

        LinkedList<Integer> ret = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) { // 3
            while (!ret.isEmpty() && nums[ret.peekLast()] < nums[i]) {
                ret.pollLast();
            }
            ret.offerLast(i);  //1 2 3

            //头部移除元素
            if (ret.peekFirst() == i - size) {
                ret.pollFirst();
            }
            if (i >= size - 1) {
                list.add(nums[ret.peekFirst()]);
            }
        }
        return list;
    }


}
