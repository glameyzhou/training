package org.glamey.training.algorithm.sort;

import org.glamey.training.algorithm.Utils;

import java.util.Arrays;

/**
 *
 * @author zhouyang.zhou. 2017.03.13.11.
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arrays = {1, 3, 2, 8, 5, 4};
        sort(arrays);
        Arrays.stream(arrays).forEach(System.out::println);
    }


    public static void sort(int[] arrays) {
        int len = arrays.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arrays[j] > arrays[j + 1]) {
                    Utils.swap(arrays, j, j + 1);
                }
            }
        }
    }
}
