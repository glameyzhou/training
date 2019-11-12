package org.glamey.training.algorithm.sort;

import org.glamey.training.algorithm.Utils;

/**
 * https://zh.wikipedia.org/wiki/%E9%80%89%E6%8B%A9%E6%8E%92%E5%BA%8F <p> 选择排序（Selection sort）是一种简单直观的排序算法。它的工作原理如下: 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。 以此类推，直到所有元素均排序完毕。
 *
 * @author zhouyang.zhou. 2017.03.14.14.
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arrays = {10, 9, 11, 40, 5, 90, 50};
        sort(arrays);
        for (int array : arrays) {
            System.out.println(array);
        }
    }


    public static void sort(int[] arrays) {
        int len = arrays.length;
        int minIndex;
        for (int i = 0; i < len; i++) {
            minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (arrays[minIndex] > arrays[j]) {
                    minIndex = j;
                }
            }
            Utils.swap(arrays, minIndex, i);
        }
    }
}
