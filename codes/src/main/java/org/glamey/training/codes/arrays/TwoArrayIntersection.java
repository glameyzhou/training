package org.glamey.training.codes.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 两个递增数组，求交集
 * 1、使用双指针，分别指向两个数组的头部。依次对比每个值，如果numA[i] == numB[j]，成立，同时i++,j++,numA[i] > numB[j] j++，反之i++
 * 2、通过二分查找法,倒序循环数组A，查看numA[maxIndex]是否存在numB中，如果存在，设置下次遍历的index=binarySearchIndex - 1;
 */
public class TwoArrayIntersection {

    public static void main(String[] args) {
        int[] m = new int[] {1, 3, 5, 6, 10};
        int[] n = new int[] {2, 5, 8};
        int[] intersection = new TwoArrayIntersection().byBinarySearch(m, n);
        System.out.println(Arrays.toString(intersection));

        intersection = new TwoArrayIntersection().byDoublePointer(m, n);
        System.out.println(Arrays.toString(intersection));
    }

    /**
     * 时间复杂度O(M*logN)
     */
    public static int[] byBinarySearch(int[] m, int[] n) {
        List<Integer> ret = new ArrayList<>();
        if (m == null || n == null) {
            return new int[0];
        }
        int left = 0, right = m.length - 1;
        for (int i = n.length - 1; i >= 0; i--) {
            int index = binarySearch(m, n[i], left, right);
            if (index != -1) {
                ret.add(m[index]);
                right = index - 1;
            }
        }
        return ret.size() == 0 ? new int[0] : ret.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int binarySearch(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2; //
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }


    /**
     * 通过双指针的方式来实现
     * 时间复杂度 O(m + n)
     * 空间复杂度 O(min(m,n))
     */
    public static int[] byDoublePointer(int[] m, int[] n) {
        List<Integer> intersection = new ArrayList<>();
        if (m == null || n == null) {
            return new int[0];
        }
        int i = 0, j = 0;
        while (i < m.length && j < n.length) {
            if (m[i] == n[j]) {
                intersection.add(m[i]);
                i++;
                j++;
            } else if (m[i] > n[j]) {
                j++;
            } else {
                i++;
            }
        }
        return intersection.size() == 0 ? new int[0] :
               intersection.stream().mapToInt(Integer::intValue).toArray();
    }
}
