package com.bobocode.tdd;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.function.Consumer;

public class RecursiveBinarySearchTree<T extends Comparable> implements BinarySearchTree<T> {

    public static <T extends Comparable> RecursiveBinarySearchTree<T> of(T... elements) {
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
