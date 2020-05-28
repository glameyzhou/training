package org.glamey.training.codes.sort;


import org.glamey.training.codes.Utils;

import java.util.Arrays;
import java.util.Random;

/**
 * 针对数组的洗牌函数
 * <p>
 * 将一个数组乱序
 *
 * @author yang.zhou 2019.11.04.14
 */
public class ShuffleArray {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6};
        shuffle(array);
        Arrays.stream(array).forEach(System.out::println);
    }


    private static Random r;

    public static void shuffle(int[] array) {
        Random rnd = r;
        if (rnd == null) {
            r = rnd = new Random();
        }
        shuffle(array, rnd);
    }

    public static void shuffle(int[] array, Random random) {
        if (null == array || array.length == 0) {
            return;
        }

        int len = array.length;
        for (int i = len; i > 0; i--) {
            Utils.swap(array, i - 1, random.nextInt(i));
        }
    }
}
