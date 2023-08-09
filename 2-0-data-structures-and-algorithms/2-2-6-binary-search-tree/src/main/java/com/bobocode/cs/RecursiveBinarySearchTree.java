package com.bobocode.cs;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    private Node<T> root;
    private int size;

    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        public Node(T value) {
            this.value = value;
        }
    }

    @SafeVarargs
    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree<T> binarySearchTree = new RecursiveBinarySearchTree<>();
        Arrays.stream(elements).forEach(binarySearchTree::insert);
        return binarySearchTree;
    }

    @Override
    public boolean insert(T element) {
        if (root == null) {
            root = new Node<>(element);
            size++;
            return true;
        } else {
            return insert(root, element);
        }
    }

    private boolean insert(Node<T> node, T element) {
        int compare = element.compareTo(node.value);

        if (compare < 0) {
            if (node.left == null) {
                node.left = new Node<>(element);
                size++;
                return true;
            } else {
                return insert(node.left, element);
            }
        } else if (compare > 0) {
            if (node.right == null) {
                node.right = new Node<>(element);
                size++;
                return true;
            } else {
                return insert(node.right, element);
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(T element) {
        if (element == null) {
            throw new NullPointerException();
        } else if (root == null) {
            return false;
        } else {
            return contains(root, element);
        }


    }

    private boolean contains(Node<T> root, T element) {
        if (root == null)
            return false;
        int compare = element.compareTo(root.value);

        if (compare > 0) {
            return contains(root.right, element);
        } else if (compare < 0) {
            return contains(root.left, element);
        } else return true;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        return root == null ? 0 : depth(root) - 1;
    }

    private int depth(Node<T> node) {
        if (node == null) {
            return 0;
        }

        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(root, consumer);
    }

    private void inOrderTraversal(Node<T> node, Consumer<T> consumer) {
        if (node != null) {
            inOrderTraversal(node.left, consumer);
            consumer.accept(node.value);
            inOrderTraversal(node.right, consumer);
        }
    }
}
