package org.glamey.training.algorithm.search;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 *
 * 示例 :
 *
 * 输入: [1,2,1,3,2,5]
 * 输出: [3,5]
 * 注意：
 *
 * 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
 * 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author yang.zhou 2020.01.07.12
 */
public class SingleNumber2 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 2, 5};
        int[] singleNumbers = new SingleNumber2().singleNumberOfHash(nums);
        for (int singleNumber : singleNumbers) {
            System.out.println(singleNumber);
        }
    }


    public int[] singleNumberOfHash(int[] nums) {
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
