package org.glamey.training.codes.strings;

import java.util.Arrays;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/6fadc1dac83a443c9434f350a5803b51
 * 来源：牛客网
 *
 * 有一个二维数组(n*n),写程序实现从右上角到左下角沿主对角线方向打印。
 *
 * 给定一个二位数组arr及题目中的参数n，请返回结果数组。
 *
 * 测试样例：
 * [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]],4
 * 返回：[4,3,8,2,7,12,1,6,11,16,5,10,15,9,14,13]
 */
public class ArrayPrint {

    public static void main(String[] args) {
        int n = 4, count = 0;
        int[][] nums = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = ++ count;
            }
        }

        for (int[] num : nums) {
            System.out.println(Arrays.toString(num));
        }

        int[] ret = arrayPrint(nums, n);
        System.out.println(Arrays.toString(ret));

    }


    public static int[] arrayPrint(int[][] nums, int n) {
        int[] ret = new int[n * n];
        int startX = 0, startY = n - 1, index = 0;

        while (startX < n) {
            int x = startX, y = startY;
            while (x < n && y < n) {
                ret[index++] = nums[x++][y++];
            }
            if (startY > 0) {
                startY--;
            } else {
                startX++;
            }
        }
        return ret;
    }
}
