package org.glamey.training.algorithm.search;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

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
//        System.out.println("source : " + source);
//        System.out.println("最大 TOP K ：" + maxTopK(source, 3));
//        System.out.println("最小 TOP K ：" + minTopK(source, 3));
        top(source);
    }


    public static void top(List<Integer> source) {
        /**
         * 1 10 3 10 100 8 77 20
         * 100 20 77 10 10 1 3 8
         */
        PriorityQueue<Integer> queue = new PriorityQueue<>(source.size(), Comparator.reverseOrder());
        for (Integer integer : source) {
            queue.add(integer);
        }

        Integer[] integers = queue.toArray(new Integer[queue.size()]);
        Arrays.stream(integers).forEach(i -> System.out.print(i + " "));
    }


    private static void checkParameter(Iterable<Integer> source, int k) {
        Preconditions.checkState(source != null);
        Preconditions.checkState(k > 0);
    }

    /**
     * 查询数组中最大的K个值，使用小顶堆
     * <p>
     * Min Heap
     *
     * @param source
     * @param k
     * @return
     */
    public static List<Integer> maxTopK(Iterable<Integer> source, int k) {
        checkParameter(source, k);
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (Integer i : source) {
            if (queue.size() < k) {
                queue.offer(i);
            } else {
                int min = queue.peek();
                if (min < i) {
                    queue.poll();
                    queue.offer(i);
                }
            }
        }
        System.out.println(Arrays.deepToString(queue.toArray()));
        return Lists.newArrayList();
        /*//需要将结果的顺序颠倒下
        Integer[] integers = new Integer[queue.size()];
        int size = queue.size() - 1;
        while (size >= 0) {
            integers[size--] = queue.poll();
        }
        return Arrays.asList(integers);*/
    }

    /**
     * 查询数组中的最小的K个值，使用大顶堆
     * <p>
     * Max Heap
     *
     * @param source
     * @param k
     * @return
     */
    public static List<Integer> minTopK(Iterable<Integer> source, int k) {
        checkParameter(source, k);
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (Integer i : source) {
            if (queue.size() < k) {
                queue.offer(i);
            } else {
                Integer max = queue.peek();
                if (max > i) {
                    queue.poll();
                    queue.offer(i);
                }
            }
        }
        /*//默认队列是自然排序，直接输出即可
        Integer[] integers = queue.toArray(new Integer[queue.size()]);
        return Arrays.asList(integers);*/
        System.out.println(Arrays.deepToString(queue.toArray()));
        return Lists.newArrayList();
    }
}
