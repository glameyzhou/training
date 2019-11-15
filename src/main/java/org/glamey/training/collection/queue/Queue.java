package org.glamey.training.collection.queue;

/**
 * @author yang.zhou 2019.11.14.17
 */
public interface Queue<T> {
    int size();

    boolean isEmpty();

    void enqueue(T t);

    T dequeue();
}
