package org.glamey.training.algorithm.search;

import org.glamey.training.algorithm.sort.BubbleSort;

/**
 * @author zhouyang.zhou. 2017.03.13.10.
 */
public class BinarySearch {

  public static void main(String[] args) {
    int[] arrays = {10, 9, 11, 40, 5, 90, 5};
    BubbleSort.sort(arrays);
    int key = 11;
    int index = binarySearch(arrays, key);
    System.out.println(index);
  }

  public static int binarySearch(int[] arrays, int key) {
    int len = arrays.length;
    int lowIndex = 0;
    int highIndex = len - 1;
    while (lowIndex <= highIndex) {
      int midIndex = (lowIndex + highIndex) / 2;
      int midVal = arrays[midIndex];
      if (key > midVal) {
        lowIndex = midIndex + 1;
      } else if (key < midVal) {
        highIndex = midIndex - 1;
      } else {
        return midIndex;
      }
    }
    return -1;
  }
}
