package org.glamey.training.algorithm.search;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 数据流中，查询中位数：如果是偶数个，返回中间的两个平均数；反之，返回中间的那个
 * <p>
 * 思路：通过最大堆和最小堆两个数据结果搞定
 *
 * @author yang.zhou 2019.11.27.14
 */
public class MedianFinder {
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        //默认最小堆
        this.minHeap = new PriorityQueue<>();
        //设置为最大堆
        this.maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void add(int num) {
        maxHeap.add(num);
        minHeap.add(maxHeap.poll());

        // maintain size property
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    /**
     * 如果两个队列size大小一样的话，分别取头节点求平均数。反之取最大堆头节点。
     *
     * @return
     */
    public double find() {
        return ((maxHeap.size() + minHeap.size()) & 1) == 1 ?
                (maxHeap.peek() + minHeap.peek()) >>> 1 : maxHeap.peek();
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        for (Integer integer : list) {
            medianFinder.add(integer);
        }
        System.out.println(medianFinder.find());

    }
}
