package org.glamey.training.algorithm.sort;

/**
 * 将两个递减的数组合并为一个大的递减数组 <p>
 *
 * @author zhouyang.zhou. 2017.06.06.21.
 */
public class MergeDemo {

    public static void main(String[] args) {
        int[] m = {20, 9, 7, 6, 2, 0};
        int[] n = {10, 8, 3, 1};

//        mergeAndSort(m, n);

        System.out.println("----------------------------------------------------");

//        merge(m, n);

        System.out.println(m.length);

        int[] array = new int[10];
        System.out.println(array.length);
        array[0] = 1;
        System.out.println(array.length);

    }

    /**
     * 数组先合并，后排序
     *
     * @param m
     * @param n
     */
    private static void mergeAndSort(int[] m, int[] n) {
        int mLen = m.length, nLen = n.length, kLen = mLen + nLen;
        int[] k = new int[kLen];

        System.arraycopy(m, 0, k, 0, mLen);
        System.arraycopy(n, 0, k, mLen, nLen);

        SelectionSort.sort(k);

        for (int i : k) {
            System.out.println(i);
        }
    }

    /**
     * 合并、排序同时进行
     * <p>
     * 策略：双指针，原地排序
     *
     * @param m
     * @param n
     */
    private static void merge(int[] m, int[] n) {
    }
}
