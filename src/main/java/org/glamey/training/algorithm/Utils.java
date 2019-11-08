package org.glamey.training.algorithm;

import org.springframework.util.Assert;

/**
 * @author zhouyang.zhou. 2017.03.14.14.
 */
public class Utils {

    /**
     * 交换数组指定下边的值
     *
     * @param arrays
     * @param x
     * @param y
     */
    public static void swap(int[] arrays, int x, int y) {
        int len = arrays.length;
        Assert.state(x >= 0 && x < len && y >= 0 && y < len);
        int tmp = arrays[x];
        arrays[x] = arrays[y];
        arrays[y] = tmp;
    }


    /**
     * 交换数组指定下标的值
     *
     * @param array
     * @param i
     * @param j
     */
    public static void swap(Object[] array, int i, int j) {
        Object tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
