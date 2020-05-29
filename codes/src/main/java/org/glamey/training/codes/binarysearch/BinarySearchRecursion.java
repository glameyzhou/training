package org.glamey.training.codes.binarysearch;

/**
 * 递归处理二分查找
 * 优点：代码简单。
 * 缺点：执行消耗大，主要开辟的栈空间不可控。
 * <p>
 * 时间复杂度：
 * 假设我们要对长度为 n 的数组进行二分搜索，T(n) 是执行时间函数，我们可以得到T(n) = T(n/2) + 1
 * <p>代入公式法得：a = 1，b = 2，f(n) = 1，因此：O(nlog(b)a) = O(n0) = 1 等于 O(f(n))，
 * 时间复杂度就是 O(nlog(b)alogn) = O(logn)。
 */
public class BinarySearchRecursion {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 4, 6, 7, 8, 10, 13, 14};
        System.out.println(recursionSearch(nums, 7, 0, nums.length - 1) == 4);
        System.out.println(recursionSearch(nums, 1, 0, nums.length - 1) == 0);
        System.out.println(recursionSearch(nums, 9, 0, nums.length - 1) == -1);
    }

    public static int recursionSearch(int[] nums, int target, int low, int high) {
        // 为了避免无限循环，先判断如果起始位置大于终点位置，表明是一个非法搜索区间，说明已经尝试了所有搜索区间依然没找到结果，直接返回-1
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2; //不用 (low + high)/2 是为了防止int溢出。
        if (target == nums[mid]) {
            return mid;
        }
        if (target > nums[mid]) { //以为mid下标数据我们已经判定不符合条件，因此下次迭代需要去掉这个数据，故使用mid+1 mid-1
            return recursionSearch(nums, target, mid + 1, high);
        } else {
            return recursionSearch(nums, target, low, mid - 1);
        }
    }
}
