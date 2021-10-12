package org.glamey.training.codes.leetcode.dp;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yang.zhou 2020.01.29.18
 */
public class MaximumSubarray {


    public static void main(String[] args) {
        System.out.println(maxLongestSubArray(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4})); //6
        System.out.println(greedy(new int[] {-2, -3, -1, 1, -5, 4})); //4
    }

    /**
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     */
    private static int greedy(int[] nums) {
        //初始化值均为第0个元素
        int maxSum = nums[0], maxTmp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            /**
             * 如果当前元素之前的所有元素之和<=0,那么当前元素nums[i]应该就是最大连续子序列和
             * 反之：继续相加
             */
            if (maxTmp <= 0) {
                maxTmp = nums[i];
            } else {
                maxTmp += nums[i];
            }

            maxSum = Math.max(maxSum, maxTmp);
        }
        return maxSum;
    }

    /**
     * 暴力破解方法
     * 时间复杂度O(N*N)
     */
    private static int maxLongestSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                max = Math.max(sum, max);
            }
        }
        return max;
    }
}
