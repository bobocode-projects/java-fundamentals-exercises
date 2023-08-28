package com.bobocode;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.function.Consumer;

public class DemoApp {
    public static void main(String[] args) {
        var treeRoot = createBinarySearchTree(6, 4, 5, 2, 3, 7, 1);
        inOrderTraversal(treeRoot, System.out::println); // should print 1,2,3,4,5,6,7
    }

    /**
     * Creates a binary search tree based on the given elements and returns the root node.
     *
     * @param elements
     * @param <T>
     * @return
     */
    public static <T extends Comparable<? super T>> Node<T> createBinarySearchTree(T... elements) {
        throw new ExerciseNotCompletedException(); //todo:
    }

    public static <T> void inOrderTraversal(Node<T> root, Consumer<? super T> consumer) {
        if (root != null) {
            inOrderTraversal(root.left, consumer);
            consumer.accept(root.element);
            inOrderTraversal(root.right, consumer);
        }
    }


    static class Node<T> {
        T element;
        Node<T> left;
        Node<T> right;

        public Node(T element) {
            this.element = element;
        }
    }


}
