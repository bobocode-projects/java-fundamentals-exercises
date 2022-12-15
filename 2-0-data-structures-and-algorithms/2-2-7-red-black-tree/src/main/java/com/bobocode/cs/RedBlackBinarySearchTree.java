package com.bobocode.cs;

import java.util.function.Consumer;

import com.bobocode.util.ExerciseNotCompletedException;

/**
 * {@link RedBlackBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on nodes,
 * coloring, rotations and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value),
 * references to the parent node, left/right child nodes and also a field type of {@link Color} which is Enum with two possible values RED and BLACK.
 * The BinarySearch tree considered as a RedBlackTree when it meets next properties:
 *  1. Every {@link Node} is either RED or BLACK {@link Color}.
 *  2. RED node can't have RED children nodes
 *  3. The root is always BLACK.
 *  4. For each node, all simple paths from the node to descendant leaves(null nodes) contain the same number of BLACK nodes
 *  5. Every leaf(null node) is null and should be treated as a BLACK node
 *
 * To fulfill these requirements the tree need additional steps after insertion of new node to re-balance itself using
 * recoloring of nodes and rotations. Every newly inserted node should be RED before re-balancing.
 *
 * Red-Black Tree example:
 *                         7(B)
 *                     /         \
 *                   /            \
 *                 3(B)           10(R)
 *                /   \         /       \
 *               /     \       /         \
 *             2(R)   null   9(B)         12(B)
 *            /   \         /    \       /    \
 *           /     \       /      \     /      \
 *         null   null  null     null  null    33(R)
 *
 *
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Serhii Bondarenko
 * @author Taras Boychuk
 */

public class RedBlackBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    public static <T extends Comparable<T>> RedBlackBinarySearchTree<T> of(T... elements) {
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
