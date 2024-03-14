package org.glamey.training.codes.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个整数数组，判断是否存在重复元素。
 * <p>
 * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 * <p>
 * 
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例3:
 * <p>
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yang.zhou 2019.11.11.15
 */
public class ContainsDuplicate_1 {

    public static void main(String[] args) {
        System.out.println(findBySort(new int[] {1, 2, 3, 4}));
        System.out.println(findBySort(new int[] {1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
        System.out.println(findBySort(new int[] {1, 2, 3, 1}));

        System.out.println(findByMap(new int[] {1, 2, 3, 4}));
        System.out.println(findByMap(new int[] {1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
        System.out.println(findByMap(new int[] {1, 2, 3, 1}));
    }


    public static boolean findByMap(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    public static boolean findBySort(int[] array) {
        Arrays.sort(array);
        for (int i = 1; i < array.length; i++) {
            if (array[i] == array[i - 1]) {
                return true;
            }
        }
        return false;
    }
}
