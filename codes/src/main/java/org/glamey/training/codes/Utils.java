package org.glamey.training.codes;


import com.google.common.base.Preconditions;

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
        Preconditions.checkState(x >= 0 && x < len && y >= 0 && y < len);
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

    public static void swap(char[] chars, int left, int right) {
        if (left > right) {
            return;
        }
        char tmp = chars[left];
        chars[left] = chars[right];
        chars[right] = tmp;
    }


    public static boolean isBlank(String source) {
        int strLen;
        if (source == null || (strLen = source.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(source.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(String source) {
        return !isBlank(source);
    }
}
