package com.bobocode.cs;

/**
 * {@link Stack} is a fundamental data structure that follows last-in-first-out (LIFO) principle. This interface
 * represents a simple contact, that can be implemented in various ways (e.g. using existing collections, arrays or
 * custom linked nodes)
 *
 * @param <T> type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public interface Stack<T> {

    void push(T element);

    T pop();

    int size();

    boolean isEmpty();
}
