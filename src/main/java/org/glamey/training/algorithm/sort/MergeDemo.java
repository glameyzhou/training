package org.glamey.training.algorithm.sort;

import org.glamey.training.algorithm.Utils;

/**
 * 将两个递减的数组合并为一个大的递减数组
 * <p>
 * 为了方便，默认设置m的长度大于n
 *
 * @author zhouyang.zhou. 2017.06.06.21.
 */
public class MergeDemo {

    public static void main(String[] args) {
        int[] m = {20, 9, 7, 6, 2, 0};
        int[] n = {10, 8, 3, 1};

        merge_1(m, n);

        System.out.println("----------------------------------------------------");

        merge_2(m, n);
    }

    private static void merge_1(int[] m, int[] n) {
        int mLen = m.length, nLen = n.length, kLen = mLen + nLen;
        int[] k = new int[kLen];

        System.arraycopy(m, 0, k, 0, mLen);
        System.arraycopy(n, 0, k, mLen, nLen);

        //选择性排序
        int maxIndex;
        for (int i = 0; i < kLen; i++) {
            maxIndex = i;
            for (int j = i + 1; j < kLen; j++) {
                if (k[maxIndex] < k[j]) {
                    maxIndex = j;
                }
                Utils.swap(k, maxIndex, i);
            }
        }


        for (int i : k) {
            System.out.println(i);
        }
    }

    private static void merge_2(int[] m, int[] n) {
        int mLen = m.length, nLen = n.length, kLen = mLen + nLen;
        int[] k = new int[kLen];
        int a = 0, b = 0, c = 0;

        while (a < mLen) {
            if (b >= nLen || (b < nLen && m[a] > n[b])) {
                k[c++] = m[a];
                a++;
            } else {
                k[c++] = n[b];
                b++;
            }
        }

        for (int i : k) {
            System.out.println(i);
        }
    }

}
