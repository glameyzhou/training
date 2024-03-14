package org.glamey.training.codes.search;

import java.util.Arrays;

import org.glamey.training.codes.Utils;

/**
 * 给定一个包含n + 1 个整数的数组nums，其数字都在 1 到 n之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 * <p>
 *     3,1,3,4,2
 *     3,1,2,3,4
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yang.zhou 2019.12.16.14
 */
public class FindDuplicateNumber {

    public static void main(String[] args) {
//        System.out.println(findByBinarySearch(new int[]{1, 3, 4, 2, 2}));
//        System.out.println(findByInPlaceSwap(new int[]{1, 2, 3, 4, 2}));
//        System.out.println(findBySort(new int[]{1, 2, 3, 4, 2}));


        System.out.println(find(new int[]{1, 3, 4, 2, 2}));
        
        
    }
    
    public static int find(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return nums[i];
            }
        }
        return -1;
    }
    
    


    public static int findBySort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * https://wiki.jikexueyuan.com/project/for-offer/question-fifty-one.html
     * 原地操作。
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     */
    public static int findByInPlaceSwap(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        //判断数字是否均在 0-n之间
        for (int num : nums) {
            if (num > nums.length || num < 1) {
                return -1;
            }
        }
        //1, 2, 2, 3, 4
        int index = 0;
        while (index < nums.length) {
            int val = nums[index];
            if (val == index + 1) {
                index++;
                continue;
            }
            if (val == nums[val]) {
                return val;
            }
            Utils.swap(nums, index, val);
        }
        return -1;
    }

    /**
     * 通过使用二分查找来实现
     * 遍历的不是下标，而是下标上面的数字
     */
    private static int findByBinarySearch(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int left = 1;
        int right = len - 1;
        while (left < right) {
            // int mid = left + (right - left) / 2;
            int mid = (left + right) >>> 1;
            int counter = 0;
            for (int num : nums) {
                if (num <= mid) {
                    counter += 1;
                }
            }
            if (counter > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
