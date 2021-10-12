package org.glamey.training.codes.search;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * TOP K
 * <p>
 * 时间复杂度：O(N * logK)
 * <p>
 * 采用了 {@link PriorityQueue}，此队列入队和出队的时间复杂度是O(logN)
 * <p>
 * 数组中，父子节点的关系如下：
 * parentNo = (nodeNo - 1) / 2
 * leftNo = parentNo * 2 + 1
 * rightNo = parentNo * 2 + 2
 * <p>
 * <p>
 * 非线程安全,线程安全需要参考 {@link java.util.concurrent.PriorityBlockingQueue}
 *
 * @author yang.zhou 2019.11.26.14
 */
public class TopKSelector {

    public static void main(String[] args) {
        List<Integer> source = Lists.newArrayList(10, 8, 3, 20, 100, 1, 77, 10);
        System.out.println("source : " + source);
        System.out.println("最大 TOP K ：" + maxTopK(source, 3));
        System.out.println("最小 TOP K ：" + minTopK(source, 3));
    }


    private static void checkParameter(Iterable<Integer> source, int k) {
        Preconditions.checkState(source != null);
        Preconditions.checkState(k > 0);
    }

    /**
     * 小顶堆
     * 查询数组中最大的K个值
     * <p>
     * Min Heap
     */
    public static List<Integer> maxTopK(Iterable<Integer> source, int k) {
        checkParameter(source, k);
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (Integer i : source) {
            if (queue.size() < k) {
                queue.add(i);
            } else {
                int min = queue.peek();
                if (i > min) {
                    queue.poll();
                    queue.add(i);
                }
            }
        }
        //小顶堆，需要把数据颠倒过来
        Integer[] integers = new Integer[queue.size()];
        for (int i = integers.length - 1; i >= 0; i--) {
            integers[i] = queue.poll();
        }
        return Arrays.asList(integers);
    }

    /**
     * 使用大顶堆
     * <p>
     * 查询数组中的最小的K个值
     * <p>
     * Max Heap
     */
    public static List<Integer> minTopK(Iterable<Integer> source, int k) {
        checkParameter(source, k);
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (Integer i : source) {
            if (queue.size() < k) {
                queue.add(i);
            } else {
                Integer max = queue.peek();
                if (i < max) {
                    queue.poll();
                    queue.add(i);
                }
            }
        }
        //默认队列是自然排序
        Integer[] integers = new Integer[queue.size()];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = queue.poll();
        }
        return Arrays.asList(integers);
    }
}
