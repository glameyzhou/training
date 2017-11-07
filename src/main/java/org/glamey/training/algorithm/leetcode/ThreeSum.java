package org.glamey.training.algorithm.leetcode;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/3sum/description/
 *
 * 从一个整形数组中，找出三个不同相加为0的数据，返回所有可能的数组集合 <p/>
 *
 * For example, given array S = [-1, 0, 1, 2, -1, -4]。<p/> A solution update is: [ [-1, 0, 1], [-1, -1, 2] ]
 *
 * @author zhouyang.zhou. 2017.08.17.15.
 */
public class ThreeSum {

  private final static List<List<Integer>> ret = Lists.newArrayList();

  public static List<List<Integer>> sum(int[] num) {
    if(num == null || num.length < 3) return ret;

    Arrays.sort(num);

    int len = num.length;
    for (int i = 0; i < len - 2; i++) {
      if(i > 0 && num[i] == num[i - 1]) {
        continue;
      }
      //寻找两个数与num[i]的和为0
      find(num, i + 1, len - 1, num[i]);
    }

    return ret;
  }

  public static void find(int[] num, int begin, int end, int target) {
    int left = begin, right = end;
    while (left < right) {
      if(num[left] + num[right] + target == 0) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(target);
        list.add(num[left]);
        list.add(num[right]);
        ret.add(list); //放入结果集中
        while (left < right && num[left] == num[left + 1]) {
          left++;
        }
        while (left < right && num[right] == num[right - 1]) {
          right--;
        }
        left++;
        right--;
      } else if(num[left] + num[right] + target < 0) {
        left++;
      } else {
        right--;
      }
    }
  }

  public static void main(String[] args) {
    int[] nums = {-1, 0, 1, 2, -1, -4};
    List<List<Integer>> result = sum(nums);
    for (List<Integer> integers : result) {
      System.out.println(integers);
    }
  }
}
