package org.glamey.training.demo.easy;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author yang.zhou 2020.01.30.10
 */
public class TwoSum extends Object {


    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int[] ints = twoSum(nums, 9);
        System.out.println(Arrays.toString(ints));
        System.out.println(new TwoSum().toString());
    }

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> valIndexMapping = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i], mapVal = target - val;
            if (valIndexMapping.containsKey(mapVal)) {
                return new int[]{valIndexMapping.get(mapVal), i};
            }
            valIndexMapping.put(val, i);
        }
        return new int[]{};
    }
}
