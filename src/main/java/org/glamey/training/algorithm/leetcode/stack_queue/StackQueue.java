package org.glamey.training.algorithm.leetcode.stack_queue;

import java.util.Random;
import java.util.Stack;

/**
 * 通过stack实现队列
 * <p>
 * stack FILO
 * queue FIFO
 *
 * @author yang.zhou 2020.01.17.10
 */
public class StackQueue<E> {
    private Stack<E> s1;
    private Stack<E> s2;

    public StackQueue() {
        this.s1 = new Stack<>();
        this.s2 = new Stack<>();
    }

    public void offer(E t) {
        if (s1.isEmpty()) {
            s1.push(t);
        } else {
            s2.push(t);
        }
    }

    /**
     * get the first element
     *
     * @return
     */
    public E poll() {
        if (s1.isEmpty() && s2.isEmpty()) {
            return null;
        }
        E e;
        if (s2.isEmpty()) {
            while (!s2.isEmpty()) {
                if (s2.size() == 1) {
                    return s2.pop();
                }
                s1.push(s2.pop());
            }
        } else {
            while (!s1.isEmpty()) {
                if (s1.size() == 1) {
                    return s1.pop();
                }
                s2.push(s1.pop());
            }
        }
        return null;
    }


    public static void main(String[] args) {
        StackQueue<Integer> stackQueue = new StackQueue<>();

        for (int i = 0; i < 10; i++) {
            stackQueue.offer(i);
            if (new Random().nextInt(10) % 2 == 0)
                System.out.println(stackQueue.poll());
        }
        System.out.println(stackQueue.poll());
    }
}
