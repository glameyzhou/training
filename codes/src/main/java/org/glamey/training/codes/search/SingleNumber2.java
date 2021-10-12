package org.glamey.training.codes.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 * <p>
 * 示例 :
 * <p>
 * 输入: [1,2,1,3,2,5]
 * 输出: [3,5]
 * 注意：
 * <p>
 * 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
 * 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yang.zhou 2020.01.07.12
 */
public class SingleNumber2 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findByMap(new int[] {1, 2, 1, 3, 2, 5})));
        System.out.println(Arrays.toString(findBySort(new int[] {1, 2, 1, 3, 2, 5})));
    }

    /**
     * 先排序，后遍历，遍历步长为2，如果是单数的话，必须不相等，直接返回即可
     * 时间复杂度O(N*logN)
     */
    public static int[] findBySort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        quickSort(nums, 0, nums.length - 1);
        for (int i = 1; i < nums.length; i += 2) {
            if (nums[i] != nums[i - 1]) {
                return new int[] {nums[i - 1], nums[i]};
            }
        }
        return new int[0];
    }

    private static void quickSort(int[] nums, int left, int right) {
        if (nums == null || nums.length <= 1 || left > right) {
            return;
        }
        int i = left, j = right, p = left, pVal = nums[p];
        while (i < j) {
            while (i < j && nums[j] >= pVal) {
                j--;
            }
            nums[i] = nums[j];

            while (i < j && nums[i] <= pVal) {
                i++;
            }
            nums[j] = nums[i];
            if (i == j) {
                nums[i] = pVal;
            }
        }
        quickSort(nums, left, i - 1);
        quickSort(nums, i + 1, right);
    }

    /**
     * 异或操作：比较位相同:0，比较位不同:1
     * 最好的办法是：将两个单数分到不同的组中，然后两组分别异或求解。
     */
    private static int[] singleNumberOfXor(int[] nums) {
        return new int[0];
    }

    /**
     * 基于hash处理
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     */
    private static int[] findByMap(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (Integer num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[] ints = new int[2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                ints[index++] = entry.getKey();
            }
        }
        return ints;
    }
}
