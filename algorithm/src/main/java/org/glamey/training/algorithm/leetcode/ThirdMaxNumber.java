package org.glamey.training.algorithm.leetcode;

import java.util.TreeSet;

/**
 * @author yang.zhou 2019.11.11.15
 */
public class ThirdMaxNumber {

    public static void main(String[] args) {

    }


    public int thirdMaxNumber(int[] array) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
            if (set.size() > 3) {
                set.remove(set.first());
            }
        }
        return set.pollLast();
    }

}
