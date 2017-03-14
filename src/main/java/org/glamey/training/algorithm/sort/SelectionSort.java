package org.glamey.training.algorithm.sort;

import org.glamey.training.algorithm.Utils;

/**
 * @author zhouyang.zhou. 2017.03.14.14.
 */
public class SelectionSort {
  public static void main(String[] args) {
    int[] arrays = {10, 9, 11, 40, 5, 90, 5};
    sort(arrays);
    for (int array : arrays) {
      System.out.println(array);
    }
  }

  public static void sort(int[] arrays) {
    int len = arrays.length;
    int minIndex = 0;
    for (int i = 0; i < len; i++) {
      minIndex = i;
      for (int j = i + 1; j < len; j++) {
        if (arrays[minIndex] > arrays[j]) {
          minIndex = j;
        }
        Utils.swap(arrays, minIndex, i);
      }
    }
  }
}
