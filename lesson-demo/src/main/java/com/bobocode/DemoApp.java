package com.bobocode;

import com.bobocode.util.ExerciseNotCompletedException;

public class DemoApp {
    public static void main(String[] args) {
        var head = createLinkedList(4, 3, 7, 9, 0);
        var current = head;
        while (current!=null) {
            System.out.print(current.element + " ");
            current = current.next;
        }
        System.out.println();
    }

    private static <T> Node<T> createLinkedList(T... elements) {
        var head = new Node<>(elements[0]);
        var current = head;
        for (int i = 1; i < elements.length; i++) {
            current.next = new Node<>(elements[i]);
            current = current.next;
        }
        return head;
    }

    /**
     * Reverses a linked list and returns a new head (old tail). PLEASE NOTE that it does not create new nodes,
     * instead in changes the next references of the current elements.
     */
    private static <T> Node<T> reverseLinkedList(Node<T> head) {
        throw new ExerciseNotCompletedException(); //todo:   
    }

    static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }
    

}
