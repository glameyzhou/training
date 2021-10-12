package org.glamey.training.codes.search;

/**
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,0,1]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [9,6,4,2,3,5,7,0,1]
 * 输出: 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/missing-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yang.zhou 2020.01.07.12
 */
public class MissingNumber {


    public static void main(String[] args) {
        int[] nums = {3, 2, 4, 0};
        System.out.println(new MissingNumber().missingNumber_oxr(nums));
        System.out.println(new MissingNumber().missingNumber_sum(nums));
    }

    /**
     * 思路：异或操作。不缺数的集合异或当前集合，最终的结果就是缺少的值
     * 异或操作
     * [3,2,4,0]
     * <p>
     * <p>
     * 0^3 ^ 1^2 ^ 2^4 ^ 3^0 ^ 4
     * = 0^0 ^ 3^3 ^ 2^2 ^ 4^4 ^ 1
     * = 0^0^0^0^1
     * =1
     */
    public int missingNumber_oxr(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }


    /**
     * 思路：按照不缺数的请求求和，然后减去当前的集合之和，等于缺少的数字。
     * 等差数列{an}的通项公式为：an=a1+(n-1)d。
     * 前n项和公式为：Sn=n*a1+n(n-1)d/2或Sn=n(a1+an)/2 。
     * sum = (0 + nums.length) * n / 2
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
