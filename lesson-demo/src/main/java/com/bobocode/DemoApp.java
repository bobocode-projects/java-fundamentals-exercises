package com.bobocode;

import com.bobocode.util.ExerciseNotCompletedException;

public class DemoApp {
    public static void main(String[] args) {
        var head = createLinkedList(1, 2, 3, 4, 5);
        printLinkedList(head); // should print "1 -> 2 -> 3 -> 4 -> 5"
    }

    static class Node<T> {
        T elemet;
        Node<T> next;

        public Node(T elemet) {
            this.elemet = elemet;
        }
    }

    /**
     * Creates a linked list based on provided values and returns a head of the list.
     *
     * @param values
     * @param <T>
     * @return
     */
    public static <T> Node<T> createLinkedList(T... values) {
        throw new ExerciseNotCompletedException(); // todo:
    }

    /**
     * Prints a provided linked list using the following format: "1 -> 2 -> 3 -> 4 -> 5"
     *
     * @param head a head node of the list
     */
    public static void printLinkedList(Node<?> head) {
        throw new ExerciseNotCompletedException(); // todo:
    }
}
