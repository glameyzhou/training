package org.glamey.training.codes.arrays;

public class CanThreeSum {

    public static void main(String[] args) {
        int[] nums = new int[] {0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1};

        boolean canThreeSum = canThreeSum(nums);
    }

    private static boolean canThreeSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 3 != 0) {
            return false;
        }
        int part = sum / 3;

        return false;
    }
}
