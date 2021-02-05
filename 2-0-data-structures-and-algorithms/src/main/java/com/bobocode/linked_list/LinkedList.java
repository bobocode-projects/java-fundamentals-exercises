package com.bobocode.linked_list;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        LinkedList<T> linkedList = new LinkedList<>();
        Stream.of(elements).forEach(linkedList::add);
        return linkedList;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (size == 0) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        Node<T> newNode = Node.valueOf(element);
        if (index == 0) {
            addAsHead(newNode);
        } else if (index == size) {
            addAsTail(newNode);
        } else {
            add(index, newNode);
        }
        size++;
    }

    private void addAsHead(Node<T> newNode) {
        newNode.next = first;
        first = newNode;
        if (first.next == null) {
            last = first;
        }
    }

    private void addAsTail(Node<T> newNode) {
        last.next = newNode;
        last = newNode;
    }

    private void add(int index, Node<T> newNode) {
        Node<T> node = findNodeByIndex(index - 1);
        newNode.next = node.next;
        node.next = newNode;
    }

    private Node<T> findNodeByIndex(int index) {
        Objects.checkIndex(index, size);
        if (index == size - 1) {
            return last;
        } else {
            return nodeAt(index);
        }
    }

    private Node<T> nodeAt(int index) {
        Node<T> currentNode = first;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        Node<T> node = findNodeByIndex(index);
        node.value = element;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        Node<T> node = findNodeByIndex(index);
        return node.value;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        checkElementsExist();
        return first.value;
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        checkElementsExist();
        return last.value;
    }

    private void checkElementsExist() {
        if (first == null) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        T deletedElement = null;
        if (index == 0) {
            Objects.checkIndex(index, size);
            deletedElement = first.value;
            removeHead();
        } else {
            Node<T> previousNode = findNodeByIndex(index - 1);
            deletedElement = previousNode.next.value;
            previousNode.next = previousNode.next.next;
            if (index == size - 1) {
                last = previousNode;
            }
        }
        size--;
        return deletedElement;
    }

    private void removeHead() {
        first = first.next;
        if (first == null) {
            last = null;
        }
    }

    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        Node<T> currentNode = first;
        while (currentNode != null) {
            if (currentNode.value.equals(element)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        first = last = null;
        size = 0;
    }

    static class Node<T> {
        private T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }

        public static <T> Node<T> valueOf(T value) {
            return new Node<>(value);
        }
    }
}
