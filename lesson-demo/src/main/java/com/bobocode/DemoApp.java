package com.bobocode;

import com.bobocode.util.ExerciseNotCompletedException;

public class DemoApp {
    public static void main(String[] args) {
        var head = createLinkedList(1, 2, 3, 4, 5);
        printLinkedList(head); // should print "1 -> 2 -> 3 -> 4 -> 5"

        System.out.println("----------------");
        printReversedRecursively(head); // should print "5 -> 4 -> 3 -> 2 -> 1"
        System.out.println("----------------");

        var newHead = reverseLinkedList(head);
        printLinkedList(newHead);// should print "5 -> 4 -> 3 -> 2 -> 1"
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
        printReversedRecursivelyHelper(head.next);
        System.out.println(head.elemet);
    }

    private static void printReversedRecursivelyHelper(Node<?> head) {
        if (head != null) {
            printReversedRecursivelyHelper(head.next);
            System.out.print(head.elemet + " -> ");
        }
    }

    /**
     * Reverses a linked list by relinking next references in the reversed order. Please note, that this method
     * DOES NOT create new nodes and does not change node values.
     *
     * @param head a head node of the list
     * @param <T>
     * @return a new head of the list (old tail)
     */
    public static <T> Node<T> reverseLinkedList(Node<T> head) {
        throw new ExerciseNotCompletedException();// todo:
    }
}
