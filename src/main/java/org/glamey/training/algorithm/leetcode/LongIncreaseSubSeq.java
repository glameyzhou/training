package org.glamey.training.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 查找数组中最长的一个递增子数组
 * https://leetcode.com/problems/longest-increasing-subsequence/description/
 *
 * Given  [10, 9, 2, 5, 3, 7, 101, 18]
 * Result [2, 3, 7, 101]
 * the length is 4
 *
 * O(n^2)
 *
 * @author zhouyang.zhou. 2017.08.08.17.
 */
public class LongIncreaseSubSeq {
  public static int lengthOfList(int[] nums) {
    int length = nums.length;
    if(length == 0) {
      return 0;
    }

    List<Integer> result = new ArrayList<Integer>();
    int maxSize = 0;
    List<Integer> tmpList;
    int base, tmp;
    for (int i = 0; i < length; i++) {
      base = nums[i];
      tmpList = new ArrayList<Integer>();
      tmpList.add(base);
      for (int j = i + 1; j < length; j++) {
        tmp = nums[j];
        if(base >= tmp) {
          continue;
        }
        base = tmp;
        tmpList.add(tmp);
      }

      if(tmpList.size() >= maxSize) {
        maxSize = tmpList.size();
        result = new ArrayList<>(tmpList);
      }
    }
    System.out.printf("maxSize=%d, array=%s %n", maxSize, result);
    return maxSize;
  }

  public static void main(String[] args) {
    int[] arrs = {10, 9, 2, 5, 3, 7, 101, 18};
    //int[] arrs = {10,9,2,5,3,4};

    lengthOfList(arrs);
  }
}
