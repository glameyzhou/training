package org.glamey.training.codes.leetcode.greedy;

/**
 * 可以买、卖多次。最终利润最大。
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * @author yang.zhou 2019.11.06.19
 */
public class BuySellStock {

    public static void main(String[] args) {
        int[] nums = new int[] {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(nums));
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 1) {
            return 0;
        }
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] >= prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }
}
