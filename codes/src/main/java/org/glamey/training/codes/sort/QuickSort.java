package org.glamey.training.codes.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 从数列中挑出一个元素，称为"基准"（pivot），
 * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
 * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 *
 * @author zhouyang.zhou, 2017.03.10.18.
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] numbers = {10, 0, 29, 9, 7, 90, 58, 10, 88, 100};
        System.out.println(String.format("[双指针]前：%s", Arrays.toString(numbers)));

        doublePointSort(numbers, 0, numbers.length - 1);
        System.out.println(String.format("[双指针]后：%s", Arrays.toString(numbers)));

        numbers = new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(String.format("[挖坑]前：%s", Arrays.toString(numbers)));
        diggerHole(numbers, 0, numbers.length - 1);
        System.out.println(String.format("[挖坑]后：%s", Arrays.toString(numbers)));
    }


    private static void diggerHole(int[] nums, int low, int high) {
        if (low <= high) {
            int midIndex = getDiggerHoldMidIndex(nums, low, high);
            diggerHole(nums, low, midIndex - 1);
            diggerHole(nums, midIndex + 1, high);
        }
    }

    private static int getDiggerHoldMidIndex(int[] nums, int low, int high) {
        //默认最左侧的数据作为基准
        int baseVal = nums[low];
        nums[low] = -1;

        while (low < high) {

            while (low < high && nums[high] >= baseVal) {
                high--;
            }
            nums[low] = nums[high];
            nums[high] = -1;


            while (low < high && nums[low] <= baseVal) {
                low++;
            }
            nums[high] = nums[low];
            nums[low] = -1;
        }

        if (low == high) {
            nums[low] = baseVal;
        }
        return low;
    }

    private static void doublePointSort(int[] nums, int head, int tail) {
        if (head >= tail || nums == null || nums.length <= 1) {
            return;
        }

        int i = head, j = tail, p = (head + tail) >>> 1, pv = nums[p];

        while (i <= j) {

            while (nums[i] < pv) {
                ++i;
            }

            while (nums[j] > pv) {
                --j;
            }

            if (i < j) {
                swap(nums, i, j);
                ++i;
                --j;
            } else if (i == j) {
                ++i;
            }
        }
        doublePointSort(nums, head, j);
        doublePointSort(nums, i, tail);
    }


    private static void swap(int[] numbers, int left, int right) {
        if (left == right) {
            return;
        }

        numbers[left] ^= numbers[right];
        numbers[right] ^= numbers[left];
        numbers[left] ^= numbers[right];
    }
}
