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
    private static class Node<T>{
        T element;
        Node<T> right;
        Node<T> left;

        public Node(T element) {
            this.element = element;
        }
    }

    private Node<T> root;
    private int size;

    @SafeVarargs
    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree<T> rbst = new RecursiveBinarySearchTree<>();
        Arrays.stream(elements).forEach(rbst::insert);
        return rbst;

    }

    @Override
    public boolean insert(T element) {
        if (root == null) {
            root = new Node<>(element);
            size++;
            return true;
        }
        else return insert(root, element);
    }

    private boolean insert (Node<T> current, T element){
        if(element.compareTo(current.element) < 0) {
            if (current.left == null) {
                current.left = new Node<>(element);
                size++;
                return true;
            } else return insert (current.left, element);

        } else if (element.compareTo(current.element) > 0) {
            if (current.right == null){
                current.right = new Node<>(element);
                size++;
                return true;
            } else return insert (current.right, element);
        } else return false;
    }


    @Override
    public boolean contains(T element) {
        if (root == null) throw new NullPointerException();
        return contains(root, element);
    }

    public boolean contains(Node<T> current, T element) {
        if(current == null) return false;
        else if(element.compareTo(current.element) < 0) {
            return contains(current.left, element);
        } else if(element.compareTo(current.element) > 0) {
            return contains(current.right, element);
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

    private int depth(Node<T> current){
        if (current == null) return 0;
        else return 1 + Math.max(depth(current.left), depth(current.right));
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(root, consumer);
    }

    private void inOrderTraversal(Node<T> current, Consumer<T> consumer){
        if (current != null) {
            inOrderTraversal(current.left, consumer);
            consumer.accept(current.element);
            inOrderTraversal(current.right, consumer);
        }
    }
}
