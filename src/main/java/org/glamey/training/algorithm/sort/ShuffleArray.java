package org.glamey.training.algorithm.sort;

import org.glamey.training.algorithm.Utils;

import java.util.Arrays;
import java.util.Random;

/**
 * 集合内的数据再次随机分布
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
        Random rnd = r == null ? new Random() : r;
        shuffle(array, rnd);
    }

    public static void shuffle(int[] array, Random random) {
        if (null == array || array.length == 0) {
            return;
        }

        int len = array.length;
        for (int i = len; i > 0; i--) {
            int index = random.nextInt(i);
            System.out.println(String.format("%d -> %d", i - 1, index));
            Utils.swap(array, i - 1, index);
        }
    }
}
