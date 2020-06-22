package org.glamey.training.good_habit;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @author zhouyang.zhou. 2017.09.26.15.
 */
public class TreeSetDemo {

    public static void main(String[] args) {
        int[] nums = {1, 10, 39, 7, 9, 78, 10, 67};
        System.out.println(getSecondBigNumber(nums));

        System.out.println(getSecondBigNumber(nums, 2));
    }

    private static int getSecondBigNumber(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.poll();
    }

    public static int getSecondBigNumber(int[] nums) {
        if (nums == null) {
            return -1;
        }

        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }

        if (len == 2) {
            return nums[0] > nums[1] ? nums[1] : nums[0];
        }

        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int num : nums) {
            treeSet.add(num);
        }
        return treeSet.lower(treeSet.last());
    }
}
