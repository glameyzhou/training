package org.glamey.training.codes.leetcode.greedy;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意你不能在买入股票前卖出股票。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 *
 * @author yang.zhou 2019.11.11.14
 */
public class BuySellStock_1 {

    public static void main(String[] args) {
        System.out.println(maxProfit_easy(new int[] {7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfit_complex(new int[] {7, 1, 5, 3, 6, 4}));
        System.out.println(max(new int[] {7, 1, 5, 3, 6, 4}));

        System.out.println(maxProfit_easy(new int[] {7, 6, 4, 3, 1}));
        System.out.println(maxProfit_complex(new int[] {7, 6, 4, 3, 1}));
        System.out.println(max(new int[] {7, 6, 4, 3, 1}));

    }

    public static int max(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int tmp;
            for (int j = i + 1; j < nums.length; j++) {
                tmp = nums[j] - nums[i];
                max = max < tmp && tmp > 0 ? tmp : max;
            }
        }
        return max;
    }




    /**
     * 时间复杂度 O(n)
     */
    public static int maxProfit_easy(int[] prices) {
        int maxProfit = 0, minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (minPrice > prices[i]) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

    /**
     * 时间复杂度 O(n^2)
     */
    public static int maxProfit_complex(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                maxProfit = Math.max(prices[j] - prices[i], maxProfit);
            }
        }
        return maxProfit;
    }
}
