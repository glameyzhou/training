package org.glamey.training.algorithm.search;

/**
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 *
 * 示例 1:
 *
 * 输入: [3,0,1]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [9,6,4,2,3,5,7,0,1]
 * 输出: 8
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/missing-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author yang.zhou 2020.01.07.12
 */
public class MissingNumber {


    public static void main(String[] args) {
        int[] nums = {3,2,4,0};
        System.out.println(new MissingNumber().missingNumber_oxr(nums));
        System.out.println(new MissingNumber().missingNumber_sum(nums));
    }

    /**
     * 异或操作
     * [3,2,4,0]
     *
     *  4^(0^3) ^ (1^2) ^ (2^4) ^ (3^0)
     * = (4^4) ^ (0^0) ^ (3^3) ^ (2^2) ^ (1)
     * =
     * @param nums
     * @return
     */
    public int missingNumber_oxr(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }


    /**
     * sum = (0 + nums.length) * n / 2
     * @param nums
     * @return
     */
    public int missingNumber_sum(int[] nums) {
        int expectedSum = (nums.length * (nums.length + 1)) >>> 1;
        int actualNum = 0;
        for (int num : nums) {
            actualNum += num;
        }
        return expectedSum - actualNum;
    }
}
