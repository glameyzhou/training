package org.glamey.training.codes.sort;

import java.util.Arrays;

/**
 * 将两个递减的数组合并为一个大的递减数组 <p>
 *
 * @author zhouyang.zhou. 2017.06.06.21.
 */
public class MergeDemo {

    public static void main(String[] args) {
        int[] m = {20, 9, 7, 6, 2, 0};
        int[] n = {10, 8, 3, 1};

        System.out.println("---------------------merge and sort-------------------------------");
        System.out.println(Arrays.toString(mergeAndSort(m, n)));

        System.out.println("---------------------merge-------------------------------");
        System.out.println(Arrays.toString(merge(m, n)));
    }

    /**
     * 数组先合并，后排序
     *
     * @param m
     * @param n
     */
    private static int[] mergeAndSort(int[] m, int[] n) {
        int mLen = m.length, nLen = n.length, kLen = mLen + nLen;
        int[] k = new int[kLen];

        System.arraycopy(m, 0, k, 0, mLen);
        System.arraycopy(n, 0, k, mLen, nLen);

        Arrays.sort(k);
        return k;
    }

    /**
     * 从大到小排序
     * 合并、排序同时进行
     *
     * @param nums1
     * @param nums2
     */
    private static int[] merge(int[] nums1, int[] nums2) {
        int l1 = nums1.length, l2 = nums2.length;
        int i = 0, j = 0, m = 0;
        int[] ret = new int[l1 + l2];
        while (i < l1 && j < l2) {
            ret[m++] = nums1[i] > nums2[j] ? nums1[i++] : nums2[j++];
        }

        if (i < l1) {
            System.arraycopy(nums1, i, ret, m, l1 - i);
        }
        if (j < l2) {
            System.arraycopy(nums2, j, ret, m, l2 - j);
        }
        return ret;
    }
}
