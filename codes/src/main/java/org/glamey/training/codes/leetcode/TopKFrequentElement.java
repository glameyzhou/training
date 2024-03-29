package org.glamey.training.codes.leetcode;

import com.google.common.primitives.Ints;

import java.util.*;

/**
 * 给定一个非空的整数数组，返回其中出现频率前k高的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 * <p>
 * 你可以假设给定的k总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) ,n是数组的大小。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yang.zhou 2019.12.03.16
 */
public class TopKFrequentElement {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(topKFrequent(nums, k));
        System.out.println(topKFrequent_(nums, k));
        System.out.println(topK(nums, k));
    }

    public static List<Integer> topK(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
        for (Integer key : map.keySet()) {
            queue.offer(key);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        int[] ret = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            ret[i] = queue.poll();
        }

        return Ints.asList(ret);
    }

    /**
     * 最小堆处理
     */
    public static List<Integer> topKFrequent_(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for (Integer integer : map.keySet()) {
            queue.offer(integer);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        int[] array = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            array[i] = queue.poll();
        }

        return Ints.asList(array);
    }

    /**
     * map value comparator
     */
    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
            map.compute(i, (k1, v) -> v == null ? 1 : v + 1);
        }
        List<Integer> list = new ArrayList<>(10);
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(map.entrySet());
        Collections.sort(entries, (a, b) -> b.getValue() - a.getValue());

        for (int i = 0; i < k; i++) {
            System.out.println(entries.get(i).getKey() + "  ->  " + entries.get(i).getValue());
            list.add(entries.get(i).getKey());
        }
        return list;
    }
}
