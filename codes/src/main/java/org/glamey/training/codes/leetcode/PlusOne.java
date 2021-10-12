package org.glamey.training.codes.leetcode;

import java.util.Arrays;

/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 * <p>
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yang.zhou 2020.01.06.15
 */
public class PlusOne {

    public static void main(String[] args) {
        int[] digits = {1, 9, 9};
        int[] array = new PlusOne().plusOne(digits);
        Arrays.stream(array).forEach(System.out::println);
    }

    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }

        int index = digits.length;
        int overVal = 1, sum;
        while (overVal > 0 && --index >= 0) {
            sum = digits[index] + overVal;
            if (sum >= 10) {
                digits[index] = 0;
                overVal = 1;
            } else {
                digits[index] += 1;
                overVal = 0;
            }
        }

        if (overVal == 1) {
            int[] array = new int[digits.length + 1];
            System.arraycopy(digits, 0, array, 1, index);
            array[0] = 1;
            return array;
        }
        return digits;
    }
}
