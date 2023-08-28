package com.bobocode;

import java.util.function.Consumer;

public class BinarySearchTreeDemoApp {
    public static void main(String[] args) {
        var treeRoot = createBinarySearchTree(6, 4, 5, 2, 3, 7, 1);
        inOrderTraversal(treeRoot, e -> System.out.print(e + " ")); // should print 1,2,3,4,5,6,7
    }

    /**
     * Creates a binary search tree based on the given elements and returns the root node.
     *
     * @param elements
     * @param <T>
     * @return
     */
    public static <T extends Comparable<? super T>> Node<T> createBinarySearchTree(T... elements) {
        var root = new Node<>(elements[0]);
        for (int i = 1; i < elements.length; i++) {
            insert(root, elements[i]);
        }
        return root;
    }

    private static <T extends Comparable<? super T>> void insert(Node<T> root, T element) {
        if (element.compareTo(root.element) < 0) { // go left
            if (root.left == null) {
                root.left = new Node<>(element);
            } else {
                insert(root.left, element);
            }
        } else if (element.compareTo(root.element) > 0) { // go right
            if (root.right == null) {
                root.right = new Node<>(element);
            } else {
                insert(root.right, element);
            }
        }
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
