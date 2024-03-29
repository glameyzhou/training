package org.glamey.training.codes.leetcode;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * 通过次数122,021提交次数203,220
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MoveZeroes {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeros(nums);
        System.out.println(Arrays.toString(nums));

        int[] array = {0, 1, 0, 3, 12};
        System.out.println(Arrays.toString(array));
        moveZerosByDoublePointer(array);
        System.out.println(Arrays.toString(array));
    }


    public static void moveZerosByDoublePointer(int[] nums) {
        if (nums == null) {
            return;
        }
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[index];
                nums[index] = tmp;
                index ++;
            }
        }
    }


    public static void moveZeros(int[] nums) {
        int index = 0;
        //0,1,0,3,0,5
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i]; //直接把非零的数据往前复制。
            }
        }
        while (index < nums.length) {
            nums[index++] = 0;
        }
    }
}
