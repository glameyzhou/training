package org.glamey.training.codes.leetcode.dp;

import java.util.Arrays;

/**
 * 最多路径问题：
 * <p>
 * [*]    *   *
 * *     *   [*]
 * <p>
 * <p>
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 * <p>
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 * <p>
 * 说明：m 和 n 的值均不超过 100。
 * <p>
 * 示例 1:
 * <p>
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * <p>
 * 示例 2:
 * <p>
 * 输入: m = 7, n = 3
 * 输出: 28
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yang.zhou 2019.11.25.14
 */
public class UniquePaths {

    /**
     * 操纵步骤：
     * 1、定义数组元素的含义
     * dp[i] [j]的含义为：当机器人从左上角走到(i, j) 这个位置时，一共有 dp[i] [j] 种路径
     * <p>
     * 2、找出关系数组元素间的关系式
     * 只能向右或者向下，因此有两种方式可以到达
     * dp[i-1][j]
     * dp[i][j-1]
     * <p>
     * 所以dp[i][j] = dp[i][j - 1] + dp[i - 1][j]
     * <p>
     * 3、找到初始值
     * dp[i][j]中，如果i或者j有一个为0，那么我们就忽略关系式。
     * 我们的初始值式计算所有的dp[0][0...n-1]和所有的dp[0..m-1][0]，这个相当于图中的最上面一行和左边一列。
     * 总结下：我们的初始值式
     * dp[0][0..n-1] = 1，最上面的一行，机器人只嗯那个一直往右走。
     * dp[0..n-1][0] = 1,最左边的一列，机器人只能一直往下走。
     * <p>
     * <p>
     * 时间复杂度O(m*n)
     * 空间复杂度O(m*n)
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths_1(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 时间复杂度O(m*n)
     * 空间复杂度O(min(m,n))
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths_2(int m, int n) {
        if (m * n <= 0) {
            return 0;
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        //dp[i] = dp[i-1] + dp[i]
        for (int i = 1; i < m; i++) {
            dp[0] = 1;
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j - 1] + dp[j];
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println(uniquePaths.uniquePaths_1(7, 3));
        System.out.println(uniquePaths.uniquePaths_1(3, 2));

        System.out.println("--------------------");
        System.out.println(uniquePaths.uniquePaths_2(7, 3));
        System.out.println(uniquePaths.uniquePaths_2(3, 2));
    }
}
