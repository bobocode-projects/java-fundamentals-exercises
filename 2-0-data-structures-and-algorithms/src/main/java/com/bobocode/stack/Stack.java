package com.bobocode.stack;

public interface Stack<T> {

    void push(T element);

    T pop();

    int size();

    boolean isEmpty();
}
