package com.bobocode.cs.redblack;

import com.bobocode.cs.Tree;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * {@link RedBlackSearchTree} is an implementation of a {@link Tree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link RedBlackSearchTree.Node}. It holds an element (a value),
 * three references to the left, right and parent nodes. Also, {@link RedBlackSearchTree.Node} has a boolean color field where RED is true and BLACK is false.
 * To understand Red Black tree better following resources can be used
 * <ul>
 *     <li> <a href="https://yongdanielliang.github.io/animation/web/RBTree.html">Red Black Tree visualizer</a></strong></li>
 *     <li> <a href="https://www.youtube.com/watch?v=qvZGUFHWChY&list=PL9xmBV_5YoZNqDI8qfOZgzbqahCUmUEin&pp=iAQB">Red Black Tree tutorial videos</a></strong></li>
 * </ul>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Yuriy Fomin
 */
public class RedBlackSearchTree<T extends Comparable<T>> implements Tree<T> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private int size;
    private Node<T> root;

    public static <T extends Comparable<T>> RedBlackSearchTree<T> of(T... elements) {
        RedBlackSearchTree<T> redBlackSearchTree = new RedBlackSearchTree<>();
        Stream.of(elements).forEach(redBlackSearchTree::insert);
        return redBlackSearchTree;
    }

    @Override
    public boolean insert(@NonNull T element) {
        Node<T> newNode = insertHelper(this.root, element);
        if (newNode != null) {
            this.size += 1;
            repair(newNode);
        }
        this.root.setColor(BLACK);
        return newNode != null;
    }

    private Node<T> insertHelper(Node<T> node, T value) {
        if (this.root == null) {
            this.root = new Node<>(value, null);
            return this.root;
        }
        if (node == null) {
            return null;
        }
        int res = value.compareTo(node.getValue());
        if (res < 0 && node.getLeft() == null) {
            node.setLeft(new Node<>(value, node));
            return node.getLeft();
        }
        if (res > 0 && node.getRight() == null) {
            node.setRight(new Node<>(value, node));
            return node.getRight();
        }

        if (res < 0) {
            return insertHelper(node.getLeft(), value);
        }
        if (res > 0) {
            return insertHelper(node.getRight(), value);
        }
        return null;
    }

    private void repair(Node<T> node) {
        if (node == null) {
            return;
        }
        if (node.color == BLACK){
            return;
        }
        if (node.getParent() == null) {
            return;
        }
        if (node.getParent().color == BLACK) {
            return;
        }
        if (node.getGrandParent() == null) {
            return;
        }
        Node<T> grandParent = node.getGrandParent();
        Node<T> parent = node.getParent();
        Node<T> uncle = getUncle(parent);
        // case 1 uncle is red -> recolor
        boolean uncleColorIsRed = uncle != null && uncle.color == RED;
        if (uncleColorIsRed) {
            parent.setColor(BLACK);
            grandParent.setColor(RED);
            uncle.setColor(BLACK);
            repair(node.getGrandParent());
            return;
        }
        // case 2 uncle is BLACK and right triangle is formed -> rotate to right parent
        boolean uncleColorIsBlack = uncle == null || uncle.color == BLACK;
        if (uncleColorIsBlack && childAndParentFormTriangle(parent, node) && parent.getLeft() == node) {
            rotateRight(parent);
            repair(parent);
            return;
        }
        // case 2 uncle is BLACK and left triangle is formed -> rotate to left parent
        if (uncleColorIsBlack && childAndParentFormTriangle(parent, node) && parent.getRight() == node) {
            rotateLeft(parent);
            repair(parent);
            return;
        }
        // case 3 uncle is BLACK and flat line is formed to the right -> rotate grandparent to right and recolor parent and grandparent
        if (uncleColorIsBlack && childAndParentFormFlatLine(parent, node) && parent.getLeft() == node) {
            rotateRight(grandParent);
            grandParent.setColor(RED);
            parent.setColor(BLACK);
            repair(node.getParent());
            return;
        }

        // case 3 uncle is BLACK and flat line is formed to the left -> rotate grandparent to right and recolor parent and grandparent
        if (uncleColorIsBlack && childAndParentFormFlatLine(parent, node) && parent.getRight() == node) {
            rotateLeft(grandParent);
            grandParent.setColor(RED);
            parent.setColor(BLACK);
            repair(node.getParent());
            return;
        }
        repair(node.getParent());
    }

    private boolean childAndParentFormTriangle(Node<T> parent, Node<T> child) {
        if (parent == null) {
            return false;
        }
        if (child == null) {
            return false;
        }
        if (parent.getParent() == null) {
            return false;
        }
        Node<T> grandParent = parent.getParent();
        if (parent.getLeft() == child && grandParent.getRight() == parent) {
            return true;
        }
        return parent.getRight() == child && grandParent.getLeft() == parent;
    }

    private boolean childAndParentFormFlatLine(Node<T> parent, Node<T> child) {
        if (parent == null) {
            return false;
        }
        if (child == null) {
            return false;
        }
        if (parent.getParent() == null) {
            return false;
        }
        Node<T> grandParent = parent.getParent();
        if (grandParent.getRight() == parent && parent.getRight() == child) {
            return true;
        }
        return grandParent.getLeft() == parent && parent.getLeft() == child;
    }

    private void rotateRight(@NonNull Node<T> node) {
        Node<T> leftChild = node.getLeft();
        Node<T> parent = node.getParent();
        node.setLeft(null);
        Node<T> leftRightChild = leftChild == null ? null : leftChild.getRight();
        if (parent != null && parent.getRight() == node) {
            parent.setRight(leftChild);
        }

        if (parent != null && parent.getLeft() == node) {
            parent.setLeft(leftChild);
        }

        if (leftChild != null) {
            leftChild.setRight(node);
            leftChild.setParent(node.getParent());
        }
        if (node == this.root) {
            this.root = leftChild;
        }
        node.setParent(leftChild);
        node.setLeft(leftRightChild);
        if (leftRightChild != null) {
            leftRightChild.setParent(node);
        }
    }

    private void rotateLeft(@NonNull Node<T> node) {
        Node<T> rightChild = node.getRight();
        Node<T> parent = node.getParent();
        node.setRight(null);
        Node<T> rightLeftChild = rightChild == null ? null : rightChild.getLeft();
        if (parent != null && parent.getRight() == node) {
            parent.setRight(rightChild);
        }
        if (parent != null && parent.getLeft() == node) {
            parent.setLeft(rightChild);
        }
        if (rightChild != null) {
            rightChild.setLeft(node);
            rightChild.setParent(node.getParent());
        }
        if (node == this.root) {
            this.root = rightChild;
        }
        node.setParent(rightChild);
        node.setRight(rightLeftChild);
        if (rightLeftChild != null) {
            rightLeftChild.setParent(node);
        }
    }

    private Node<T> getUncle(@NonNull Node<T> parent) {
        Node<T> grandParent = parent.getParent();
        if (grandParent.getLeft() == parent) {
            return grandParent.getRight();
        }
        if (grandParent.getRight() == parent) {
            return grandParent.getLeft();
        }
        throw new IllegalStateException("Parent is not a child of grandparent");
    }

    @Override
    public boolean contains(@NonNull T element) {
        return contains(this.root, element);
    }

    private boolean contains(Node<T> node, T el) {
        if (node == null) {
            return false;
        }
        int res = el.compareTo(node.getValue());
        if (res == 0) {
            return true;
        }
        if (res < 0) {
            return contains(node.getLeft(), el);
        }
        return contains(node.getRight(), el);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int depth() {
        return root == null ? 0 : depthHelper(this.root) - 1;
    }

    int depthHelper(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(depthHelper(node.getLeft()), depthHelper(node.getRight()));
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(root, consumer);
    }

    private void inOrderTraversal(Node<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.getLeft(), consumer);
        consumer.accept(node.getValue());
        inOrderTraversal(node.getRight(), consumer);
    }

    @Override
    public void preOrderTraversal(Consumer<T> consumer) {
        preOrderTraversal(root, consumer);
    }

    private void preOrderTraversal(Node<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }
        consumer.accept(node.getValue());
        preOrderTraversal(node.getLeft(), consumer);
        preOrderTraversal(node.getRight(), consumer);
    }

    @Data
    private static class Node<V> {
        V value;
        boolean color;
        @ToString.Exclude
        Node<V> parent;
        Node<V> left;
        Node<V> right;

        private Node(V value, Node<V> parent) {
            this.value = value;
            this.parent = parent;
            this.color = RED;
        }

        private Node<V> getGrandParent() {
            if (this.parent == null) {
                return null;
            }
            return this.getParent().getParent();
        }
    }
}