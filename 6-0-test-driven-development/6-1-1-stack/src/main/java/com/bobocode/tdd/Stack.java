package com.bobocode.tdd;

/**
 *
 * Stack is a data structure that follows "last in, first out" rule (LIFO).
 */
public interface Stack<T> {
    /**
     * Adds an element to the begining of the stack.
     *
     * @param element the element to add
     */
    void push(T element);

    /**
     * Retrieves and removes stack head.
     *
     * @return an element that was retrieved from the head or null if stack is empty
     */
    T pop();

    /**
     * Returns a size of the stack.
     *
     * @return an integer value that is a size of stack
     */
    int size();

    /**
     * Checks if the stack is empty.
     *
     * @return {@code true} if the stack is empty, returns {@code false} if it's not
     */
    boolean isEmpty();
}
