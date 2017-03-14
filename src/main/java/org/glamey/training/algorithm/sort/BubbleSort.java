package org.glamey.training.algorithm.sort;

import org.glamey.training.algorithm.Utils;

/**
 * @author zhouyang.zhou. 2017.03.13.11.
 */
public class BubbleSort {
  public static void main(String[] args) {
    int[] arrays = {10, 9, 11, 40, 5, 90, 5};
    sort(arrays);
    for (int array : arrays) {
      System.out.println(array);
    }
  }

  public static void sort(int[] arrays) {
    int len = arrays.length, i, k;
    for (i = 0; i < len; i++) {
      for (k = 0; k < len - i - 1; k++) {
        if (arrays[k] > arrays[k + 1]) {
          Utils.swap(arrays, k, k + 1);
        }
      }
    }
  }
}
