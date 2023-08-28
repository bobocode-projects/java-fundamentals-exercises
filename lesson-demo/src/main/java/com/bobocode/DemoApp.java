package com.bobocode;

import com.bobocode.util.ExerciseNotCompletedException;

public class DemoApp {
    public static void main(String[] args) {
        var intListHead = createLinkedList(1, 2, 3, 4, 5);
        printLinkedList(intListHead);
    }

    /**
     * Creates a linked list based on the provided elements and returns a list head.
     *
     * @param elements
     * @param <T>
     * @return created list head
     */
    public static <T> Node<T> createLinkedList(T... elements) {
        throw new ExerciseNotCompletedException();// todo:
    }

    /**
     * Prints a provided linked list in the following format:
     * 1 -> 2 -> 3 -> 4 -> 5
     *
     * @param head a list head
     */
    public static void printLinkedList(Node<?> head) {
        throw new ExerciseNotCompletedException();// todo:
    }

    /**
     * Prints a provided linked list recursively in the following format:
     * 1 -> 2 -> 3 -> 4 -> 5
     *
     * @param head a list head
     */
    public static void printLinkedListRecursively(Node<?> head) {
        throw new ExerciseNotCompletedException();// todo:
    }

    static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }


}
