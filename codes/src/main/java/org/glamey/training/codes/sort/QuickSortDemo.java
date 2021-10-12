package org.glamey.training.codes.sort;

import java.util.Arrays;
import java.util.Random;

public class QuickSortDemo {

    public static void main(String[] args) {
        int[] nums = new int[10];
        for (int i = 0; i < 10; i++) {
            nums[i] = i;
        }
        shuffle(nums);
        System.out.println("before v1 -> " + Arrays.toString(nums));
        quickSort_v1(nums, 0, nums.length - 1);
        System.out.println("after v1 -> " + Arrays.toString(nums) + "\r\n");

        nums = new int[] {47, 29, 71, 99, 78, 19, 24, 47};
        System.out.println("before v2 -> " + Arrays.toString(nums));
        quickSort_v2(nums, 0, nums.length - 1);
        System.out.println("after v2 -> " + Arrays.toString(nums) + "\r\n");

        nums = new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println("before v3 -> " + Arrays.toString(nums));
        quickSort_v3(nums, 0, nums.length - 1);
        System.out.println("after v3 -> " + Arrays.toString(nums) + "\r\n");


        nums = new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println("before v4 -> " + Arrays.toString(nums));
        quickSort_v4(nums, 0, nums.length - 1);
        System.out.println("after v4 -> " + Arrays.toString(nums) + "\r\n");
    }


    private static void quickSort_v4(int[] nums, int left, int right) {
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
        }
        if (i == j) {
            nums[i] = pVal;
        }

        quickSort_v4(nums, left, i - 1);
        quickSort_v4(nums, i + 1, right);
    }


    private static void quickSort_v3(int[] nums, int left, int right) {
        if (nums == null || nums.length <= 1 || left > right) {
            return;
        }

        int partitionIndex = partition(nums, left, right);
        quickSort_v3(nums, left, partitionIndex - 1);
        quickSort_v3(nums, partitionIndex + 1, right);

    }

    private static int partition(int[] nums, int left, int right) {
        int p = left, pVal = nums[p];
        while (left < right) {
            while (left < right && nums[right] >= pVal) {
                right--;
            }
            nums[left] = nums[right];

            while (left < right && nums[left] <= pVal) {
                left++;
            }
            nums[right] = nums[left];
        }
        if (left == right) {
            nums[left] = pVal;
        }
        return left;
    }

    private static void quickSort_v2(int[] nums, int left, int right) {
        if (nums == null || nums.length <= 1 || left > right) {
            return;
        }
        int i = left, j = right, pVal = nums[left];
        while (i <= j) {
            while (i <= j && nums[j] >= pVal) {
                j--;
            }
            if (i <= j) {
                nums[i] = nums[j];
                i++;
            }

            while (i <= j && nums[i] <= pVal) {
                i++;
            }
            if (i <= j) {
                nums[j] = nums[i];
                j--;
            }
        }
        nums[i] = pVal;

        quickSort_v2(nums, left, i - 1);
        quickSort_v2(nums, i + 1, right);
    }

    private static void quickSort_v1(int[] nums, int left, int right) {
        if (left >= right || nums == null || nums.length <= 1) {
            return;
        }
        int i = left, j = right, p = (left + right) >>> 1, pVal = nums[p];
        while (i <= j) {
            while (pVal > nums[i]) {
                ++i;
            }
            while (pVal < nums[j]) {
                --j;
            }

            if (i < j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                ++i;
                --j;
            } else if (i == j) {
                ++i;
            }
        }

        quickSort_v1(nums, left, j);
        quickSort_v1(nums, i, right);

    }

    private static void shuffle(int[] nums) {
        Random random = new Random();
        for (int i = nums.length - 1; i > 0; i--) {
            int r = random.nextInt(i);
            int tmp = nums[r];
            nums[r] = nums[i];
            nums[i] = tmp;
        }
    }
}
