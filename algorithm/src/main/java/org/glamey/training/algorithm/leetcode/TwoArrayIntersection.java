package org.glamey.training.algorithm.leetcode;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 两个递增数组求交集
 */
public class TwoArrayIntersection {

    public static void main(String[] args) {
        int[] m = new int[]{1, 3, 5, 6};
        int[] n = new int[]{2, 5};
        int[] intersection = new TwoArrayIntersection().intersection_1(m, n);
        System.out.println(Arrays.toString(intersection));

        intersection = new TwoArrayIntersection().intersection_2(m, n);
        System.out.println(Arrays.toString(intersection));
    }

    public int[] intersection_1(int[] m, int[] n) {
        List<Integer> ret = new ArrayList<>();
        if (m == null || n == null) return new int[0];
        for (int i = n.length - 1; i >= 0; i--) {
            int index = binarySearch(m, n[i]);
            if (index != -1 && m[index] == n[i]) {
                ret.add(m[index]);
            }
        }
        return ret.size() == 0 ? new int[0] : ArrayUtils.toPrimitive(ret.toArray(new Integer[ret.size()]));
    }

    private int binarySearch(int[] m, int target) {
        int low = 0, high = m.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (m[mid] == target) return mid;
            else if (m[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }


    /**
     * 时间复杂度 O(m + n)
     * 空间复杂度 O(min(m,n))
     *
     * @param m
     * @param n
     * @return
     */
    public int[] intersection_2(int[] m, int[] n) {
        List<Integer> ret = new ArrayList<>();
        if (m == null || n == null) return new int[0];
        int i = 0, j = 0, iL = m.length, jL = n.length;
        while (i < iL && j < jL) {
            if (m[i] == n[j]) {
                ret.add(m[i]);
                i++;
                j++;
            } else if (m[i] < n[j]) {
                i++;
            } else {
                j++;
            }
        }
        return ret.size() == 0 ? new int[0] : ArrayUtils.toPrimitive(ret.toArray(new Integer[ret.size()]));
    }
}
