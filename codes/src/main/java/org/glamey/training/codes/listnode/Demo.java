package org.glamey.training.codes.listnode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

/**
 * @author glamey.zhou
 * Created on 2021-10-20
 */
public class Demo {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 3, 30, 31, 40, 90};
        int target = 44;
        int[] ret = findTwoSumIndex(nums, target);
        System.out.println(Arrays.toString(ret));


        int[] threeSum = threeSum(nums, target);
        System.out.println(Arrays.toString(threeSum));
    }

    private static int[] threeSum(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return new int[0];
        }
        for (int i = 0; i < nums.length; i++) {
            List<Integer> twoSum = twoSum(nums, target - nums[i], i);
            System.out.println(twoSum);
            if (twoSum == null || twoSum.size() != 2) {
                continue;
            }
            return new int[] {i, twoSum.get(0), twoSum.get(1)};
        }
        return new int[0];
    }

    @Nullable
    private static List<Integer> twoSum(int[] nums, int target, int skipIndex) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        Map<Integer, Integer> valIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (skipIndex == i) {
                continue;
            }
            if (!valIndexMap.containsKey(target - nums[i])) {
                valIndexMap.put(nums[i], i);
                continue;
            }
            return Lists.newArrayList(valIndexMap.get(target - nums[i]), i);
        }
        return null;
    }

    private static int[] findTwoSumIndex(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (target == nums[left] + nums[right]) {
                return new int[] {left, right};
            }
            if (target > nums[left] + nums[right]) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
    }
}
