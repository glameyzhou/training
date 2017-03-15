package org.glamey.training.algorithm.sort;

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
    sort(numbers, 0, numbers.length - 1);

    for (int number : numbers) {
      System.out.println(number);
    }
  }

  private static void sort(int[] numbers, int start, int end) {

    if(start >= end) {
      return;
    }

    int pivotVal = numbers[end];
    int left = start, right = end - 1;
    while (left < right) {

      while (numbers[left] <= pivotVal && left < right) {
        left++;
      }

      while (numbers[right] >= pivotVal && left < right) {
        right--;
      }

      swap(numbers, left, right);
    }

    if(numbers[left] >= numbers[end]) {
      swap(numbers, left, end);
    } else {
      left++;
    }

    sort(numbers, start, left - 1);
    sort(numbers, left + 1, end);
  }

  private static void swap(int[] numbers, int left, int right) {
    int tmp = numbers[left];
    numbers[left] = numbers[right];
    numbers[right] = tmp;
  }
}
