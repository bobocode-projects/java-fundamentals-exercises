package com.bobocode.cs;

public interface Stack<T> {

    void push(T element);

    T pop();

    int size();

    boolean isEmpty();
}
