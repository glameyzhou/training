/**
 * https://leetcode-cn.com/tag/dynamic-programming/
 * <p>
 * 动态规划相关的练习题目。
 * <p>
 * <p>
 * 关于动态规划的详解，业界良心
 * https://zhuanlan.zhihu.com/p/91582909
 * <p>
 * <p>
 * <p>
 * 步骤：
 * 1、定义数组元素含义
 * 一般都是dp[i][j]
 * <p>
 * 2、找到数组元素之间的关系式
 * 肯定跟dp[i-1][j] dp[i][j-1] dp[i-1][j-1]有关系
 * <p>
 * 3、找出初始值
 * <p>
 * 4、循环执行就完事.
 *
 * @author yang.zhou 2019.11.25.15
 */
package org.glamey.training.codes.leetcode.dp;