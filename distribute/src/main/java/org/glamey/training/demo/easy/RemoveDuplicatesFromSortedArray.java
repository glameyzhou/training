package org.glamey.training.demo.easy;

/**
 * @author yang.zhou 2020.01.31.22
 */
public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int len = removeDuplicates(nums);
        for (int li = 0; li < len; li++) {
            System.out.println(nums[li]);
        }
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int index = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[index]) {
                index++;
                nums[index] = nums[j];
            }
        }
        return index + 1;
    }
}
