package org.glamey.training.demo;

import java.util.Arrays;
import java.util.Random;

/**
 * @author yang.zhou 2020.01.30.10
 */
public class ArrayMarker {

    public static void main(String[] args) {
        int[] nums = randomArray(9);
        System.out.println(Arrays.toString(nums));
    }


    public static int[] randomArray(int count) {
        int[] array = new int[count];
        int val = count;
        while (count > 0) {
            array[--count] = --val;
        }
        shuffle(array);
        return array;
    }


    public static void shuffle(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        Random random = new Random();
        for (int i = nums.length - 1; i > 0; i--) {
            int index = random.nextInt(i);
            swap(nums, i, index);
        }


    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
