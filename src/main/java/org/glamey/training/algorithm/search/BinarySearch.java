package org.glamey.training.algorithm.search;

import org.glamey.training.algorithm.sort.BubbleSort;

import java.util.Arrays;

/**
 * @author zhouyang.zhou. 2017.03.13.10.
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arrays = {1, 3, 2, 8, 5, 4};
        BubbleSort.sort(arrays);
        Arrays.stream(arrays).forEach(System.out::println);
        int index = binarySearch(arrays, 5);
        System.out.println("index -> " + index);
    }


    public static int binarySearch(int[] arrays, int key) {
        int len = arrays.length;
        int lowIndex = 0;
        int highIndex = len - 1;
        while (lowIndex <= highIndex) {
            int midIndex = (highIndex + lowIndex) >>> 1;
            int midVal = arrays[midIndex];
            if (key > midVal) {
                lowIndex = midIndex + 1;
            } else if (key < midIndex) {
                highIndex = midIndex - 1;
            } else {
                return midIndex;
            }
        }
        return -1;
    }
}
