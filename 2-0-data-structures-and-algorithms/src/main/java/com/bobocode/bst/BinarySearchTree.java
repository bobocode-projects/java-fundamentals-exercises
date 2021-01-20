package com.bobocode.bst;

import java.util.function.Consumer;

public interface BinarySearchTree<T extends Comparable> {
    /**
     * insert an element
     * @return true if element did not exist in the tree and was inserted
     */
    boolean insert(T element);

    //todo: rename
    /**
     * @return true if element exists in the tree
     */
    boolean search(T element);

    /**
     * @return number of elements in the tree
     */
    int size();

    //todo: rename to depth
    /**
     * @return 1 + max. number of nodes between root node and any other node(0 - if no elements in the tree; 1 - if there is only root)
     */
    int height();

    /**
     * traverse the tree in element's natural order
     * @param consumer accepts ref. to node during traversing
     */
    void inOrderTraversal(Consumer<T> consumer);

}
