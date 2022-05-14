package com.bobocode;

/**
 * A generic node that holds an element of any type and a reference to the next element.
 *
 * @param <T> element type
 */
public class Node<T> {
    T element;
    Node<T> next;

    public Node(T element) {
        this.element = element;
    }
}
