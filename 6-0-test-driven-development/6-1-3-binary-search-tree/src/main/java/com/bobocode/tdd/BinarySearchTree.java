package com.bobocode.tdd;

import java.util.function.Consumer;

public interface BinarySearchTree<T extends Comparable> {
    /**
     * insert an element
     * @return true if element did not exist in the tree and was inserted successfully
     */
    boolean insert(T element);

    /**
     * @return true if tree contains element
     */
    boolean contains(T element);

    /**
     * @return number of elements in the tree
     */
    int size();

    /**
     * @return max. number of transition between root node and any other node; 0 - if tree is empty or contains 1 element
     */
    int depth();

    /**
     * traverse the tree in element's natural order
     * @param consumer accepts ref. to node during traversing
     */
    void inOrderTraversal(Consumer<T> consumer);
}
