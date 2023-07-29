package com.bobocode;

import com.bobocode.util.ExerciseNotCompletedException;

public class DemoApp {
    public static void main(String[] args) {
        var head = createLinkedList(1, 2, 3, 4, 5);
        printLinkedList(head); // should print "1 -> 2 -> 3 -> 4 -> 5"
        printReversedRecursively(head); // should print "5 -> 4 -> 3 -> 2 -> 1"
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
        var head = new Node<>(values[0]);
        var current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new Node<>(values[i]);
            current = current.next;
        }
        return head;
    }

    /**
     * Prints a provided linked list using the following format: "1 -> 2 -> 3 -> 4 -> 5"
     *
     * @param head a head node of the list
     */
    public static void printLinkedList(Node<?> head) {
        var current = head;
        while (current.next != null) {
            System.out.print(current.elemet + " -> ");
            current = current.next;
        }
        System.out.println(current.elemet);
    }

    /**
     * Prints a provided linked list in the reversed order using the following format: "5 -> 4 -> 3 -> 2 -> 1"
     *
     * @param head a head node of the list
     */
    public static void printReversedRecursively(Node<?> head) {
        throw new ExerciseNotCompletedException(); // todo:
    }
}
