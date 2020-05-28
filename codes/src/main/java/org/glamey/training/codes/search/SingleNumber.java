package org.glamey.training.codes.search;

import java.util.Arrays;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * <p>
 * <p>
 * 1、其余元素均是出现两次，只有一个出现一次。 2*(a + b + c) - (a + a + b + b + c) = c
 * 2、异或操作：节省空间、不会出现溢出
 * a ^ a = 0
 * a ^ 0 = a
 * a ^ b ^ a = (a ^ a) ^ b = 0 ^ b = b
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yang.zhou 2020.01.07.11
 */
public class SingleNumber {

    public static void main(String[] args) {
        int[] nums = {1, 1, 3, 5, 3, 4, 5,};
        System.out.println(findByXOR(nums));
        System.out.println(findByMathFormula(nums));
    }


    /**
     * 数学方式
     * 2a + 2b + 2c - (a + b + c) = c
     *
     * @param nums
     * @return
     */
    private static int findByMathFormula(int[] nums) {
        return 2 * Arrays.stream(nums).distinct().sum() - Arrays.stream(nums).sum();
    }


    private static int findByXOR(int[] nums) {
        int c = 0;
        for (int num : nums) {
            c ^= num;
        }
        return c;
    }
}
