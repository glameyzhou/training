package org.glamey.training.codes.leetcode.dp;

/**
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1:
 * <p>
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2:
 * <p>
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释:
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yang.zhou 2019.11.25.15
 */
public class EditDistance {

    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        System.out.println(editDistance.minDistance_1("horse", "ros"));
        System.out.println(editDistance.minDistance_2("horse", "ros"));
    }


    /**
     * 空间复杂度 O(m*n)
     */
    public int minDistance_1(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        // dp[0][0...n2]的初始值
        for (int j = 1; j <= n2; j++) {
            dp[0][j] = dp[0][j - 1] + 1;
        }
        // dp[0...n1][0] 的初始值
        for (int i = 1; i <= n1; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        // 通过公式推出 dp[n1][n2]
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                // 如果 word1[i] 与 word2[j] 相等。第 i 个字符对应下标是 i-1
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                }
            }
        }
        return dp[n1][n2];
    }

    /**
     * 空间复杂度O(min(m,n))
     */
    public int minDistance_2(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[] dp = new int[n2 + 1];
        // dp[0...n2]的初始值
        for (int j = 0; j <= n2; j++) {
            dp[j] = j;
        }
        // dp[j] = min(dp[j-1], pre, dp[j]) + 1
        for (int i = 1; i <= n1; i++) {
            int temp = dp[0];
            // 相当于初始化
            dp[0] = i;
            for (int j = 1; j <= n2; j++) {
                // pre 相当于之前的 dp[i-1][j-1]
                int pre = temp;
                temp = dp[j];
                // 如果 word1[i] 与 word2[j] 相等。第 i 个字符对应下标是 i-1
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = pre;
                } else {
                    dp[j] = Math.min(Math.min(dp[j - 1], pre), dp[j]) + 1;
                }
                // 保存要被抛弃的值
            }
        }
        return dp[n2];
    }
}
