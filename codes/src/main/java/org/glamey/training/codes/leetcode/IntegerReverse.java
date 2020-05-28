package org.glamey.training.codes.leetcode;

import com.google.common.collect.Lists;

import java.util.ArrayList;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

/**
 * https://leetcode-cn.com/problems/reverse-integer/
 *
 * @author yang.zhou 2019.11.07.18
 */
public class IntegerReverse {


    public static void main(String[] args) {
        // max = 2147483647, min = -2147483648
        ArrayList<Integer> list = Lists.newArrayList(123, -123, 110, 10000, -100, 1534236469);
        for (Integer source : list) {
            System.out.println(source + " -> " + reverse(source));
        }

    }

    public static int reverse(int source) {
        int rev = 0;
        while (source != 0) {
            int pop = source % 10;
            source /= 10;

            if (rev > MAX_VALUE / 10 || (rev == MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }

            if (rev < MIN_VALUE / 10 || (rev == MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }

            rev = rev * 10 + pop;
        }
        return rev;
    }
}
