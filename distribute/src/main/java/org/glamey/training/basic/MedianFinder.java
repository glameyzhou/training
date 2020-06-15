package org.glamey.training.basic;

import java.util.PriorityQueue;
import java.util.Random;

public class MedianFinder {


    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder(new PriorityQueue<>(), new PriorityQueue<>((a, b) -> b - a));
        int count = 10;
        for (int i = 0; i < 10; i++) {
            int val = new Random().nextInt(count);
            finder.add(val);
            System.out.println(" add -> " + val + " median -> " + finder.get());
        }
    }


    private final PriorityQueue<Integer> minHeap;
    private final PriorityQueue<Integer> maxHeap;
    private int count = 0;

    public MedianFinder(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
        this.minHeap = minHeap;
        this.maxHeap = maxHeap;
    }


    public void add(int val) {
        if (count % 2 == 0) {
            maxHeap.offer(val);
            minHeap.offer(maxHeap.poll());
        } else {
            minHeap.offer(val);
            maxHeap.offer(minHeap.poll());
        }
        count++;
    }

    public double get() {
        if (count % 2 == 0) {
            return (double) (maxHeap.peek() + minHeap.peek()) / 2;
        }
        return (double) minHeap.peek();
    }
}
