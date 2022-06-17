package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

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

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        throw new ExerciseNotCompletedException();
    }

    @Override
    public boolean insert(T element) {
        throw new ExerciseNotCompletedException();
    }

    @Override
    public boolean contains(T element) {
        throw new ExerciseNotCompletedException();
    }

    @Override
    public int size() {
        throw new ExerciseNotCompletedException();
    }

    @Override
    public int depth() {
        throw new ExerciseNotCompletedException();
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        throw new ExerciseNotCompletedException();
    }
}
