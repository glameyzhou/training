package org.glamey.training.codes.leetcode;


/**
 * 最大乘积
 */
public class MaxMulti {


    /**
     * 最大连续子数组乘积问题
     * <p>
     * 形如 {2,3,0,-1,2,5} --> 2 * 5 = 10
     *
     * @param arrays
     * @return
     */
    public static int maxMultiOfContinue(int[] arrays) {
        if (arrays == null || arrays.length == 0) {
            return 0;
        }

        int max = arrays[0], min = arrays[0], result = 0;
        for (int i = 1; i < arrays.length; i++) {
            int preMax = max;
            int preMin = min;

            max = Math.max(arrays[i], Math.max(arrays[i] * preMax, arrays[i] * preMin));
            min = Math.min(arrays[i], Math.min(arrays[i] * preMax, arrays[i] * preMin));
            result = Math.max(max, min) > result ? Math.max(max, min) : result;
        }
        return result;
    }

    /**
     * @param arrays
     * @return
     */
    public static int maxMultiOfContinue_slow(int[] arrays) {
        if (arrays == null || arrays.length == 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < arrays.length; i++) {
            int multi = 1;
            for (int j = i; j < arrays.length; j++) {
                multi *= arrays[j];
                if (multi > result) {
                    result = multi;
                }
            }
        }
        return result;
    }
}
