package com.bobocode.cs;

/**
 * Queue is a data structure that follows "first in, first out" rule (FIFO). Operations {@link Queue#add(Object)} and
 * {@link Queue#poll()} are performed in constant time O(1)
 */
public interface Queue<T> {
    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to add
     */
    void add(T element);

    /**
     * Retrieves and removes queue head.
     *
     * @return an element that was retrieved from the head or null if queue is empty
     */
    T poll();

    /**
     * Returns a size of the queue.
     *
     * @return an integer value that is a size of queue
     */
    int size();

    /**
     * Checks if the queue is empty.
     *
     * @return {@code true} if the queue is empty, returns {@code false} if it's not
     */
    boolean isEmpty();
}
