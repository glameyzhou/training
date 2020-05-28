package org.glamey.training.codes.search;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/find-median-from-data-stream/
 * <p>
 * <p>
 * 数据流中，查询中位数：如果是偶数个，返回中间的两个平均数；反之，返回中间的那个
 * <p>
 * 思路：通过大顶堆和小顶堆两个数据结果搞定
 * <p>
 * 要求：
 * 1、大顶堆的顶元素 小于或者等于 小顶堆的顶元素
 * 2、大顶堆的元素个数 大于后者等于 小顶堆的元素
 * <p>
 * 步骤：
 * 1、两个堆元素之和为偶数（刚开始的时候），为了让大顶堆多一个元素，必须是：大顶堆 -> 小顶堆 -> 大顶堆
 * 2、两个堆元素之和为奇数，此时小顶堆元素必须多一个元素，这样大顶堆、小顶堆的元素个数才相同，采用：大顶堆 -> 小顶堆
 * <p>
 * 无论元素之和是偶数还是奇数，都必须是 大顶堆 -> 小顶堆，而当加入新元素后，元素总数变成奇数个后，把小顶堆的顶元素给大顶堆即可。
 * <p>
 * <p>
 * 时间复杂度：O(logN)，优先队列的出队入队操作都是对数级别的，数据在两个堆中间来回操作是常数级别的，综上时间复杂度是 O(logN) 级别的。
 * 空间复杂度：O(N)，使用了三个辅助空间，其中两个堆的空间复杂度是 O(n/2),一个表示数据流元素个数的计数器 count，占用空间 O(1)，综上空间复杂度为 O(N)。
 *
 * @author yang.zhou 2019.11.27.14
 */
public class MedianFinder {
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;
    private int count; //两个堆的元素只和

    public MedianFinder() {
        count = 0;
        //设置为大顶堆
        this.maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        //默认小顶堆
        this.minHeap = new PriorityQueue<>();
    }

    public void add(int number) {
        count += 1;
        maxHeap.add(number);
        minHeap.add(maxHeap.poll());

        // 如果两个堆合起来的元素个数是奇数，小顶堆要拿出堆顶元素给大顶堆
        if ((count & 1) != 0) {
            maxHeap.add(minHeap.poll());
        }
    }

    /**
     * 如果两个队列size大小一样的话，分别取头节点求平均数。反之取大顶堆头节点。
     *
     * @return
     */
    public double find() {
        //两个堆元素之和为偶数
        if ((count & 1) == 0) {
            return (double) (maxHeap.peek() + minHeap.peek()) / 2;
        }
        //两个堆元素只和为奇数
        return (double) maxHeap.peek();
    }

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        List<Integer> list = Lists.newArrayList(4, 5, 1, 2, 3);
        for (Integer integer : list) {
            finder.add(integer);
        }
        System.out.println(finder.find());
    }
}
