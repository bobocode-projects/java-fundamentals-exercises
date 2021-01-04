package com.bobocode.linked_list;


import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<E>}.
 *
 * @param <E> generic type parameter
 */
public class LinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <E>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <E> List<E> of(E... elements) {
        LinkedList<E> linkedList = new LinkedList<>();
        Stream.of(elements).forEach(linkedList::add);
        return linkedList;
    }

    /**
     * Adds an element to the end of the list. This operation is performed in constant time O(1)
     *
     * @param element element to add
     */
    @Override
    public void add(E element) {
        add(size, element);
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, E element) {
        Node<E> newNode = Node.valueOf(element);
        if (index == 0) {
            addAsHead(newNode);
        } else if (index == size) {
            addAsTail(newNode);
        } else {
            add(index, newNode);
        }
        size++;
    }

    private void addAsHead(Node<E> newNode) {
        newNode.next = head;
        head = newNode;
        if (head.next == null) {
            tail = head;
        }
    }

    private void addAsTail(Node<E> newNode) {
        tail.next = newNode;
        tail = newNode;
    }

    private void add(int index, Node<E> newNode) {
        Node<E> node = findNodeByIndex(index - 1);
        newNode.next = node.next;
        node.next = newNode;
    }

    private Node<E> findNodeByIndex(int index) {
        Objects.checkIndex(index, size);
        if (index == size - 1) {
            return tail;
        } else {
            return nodeAt(index);
        }
    }

    private Node<E> nodeAt(int index) {
        Node<E> currentNode = head;
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
    public void set(int index, E element) {
        Node<E> node = findNodeByIndex(index);
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
    public E get(int index) {
        Node<E> node = findNodeByIndex(index);
        return node.value;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public E getFirst() {
        checkElementsExist();
        return head.value;
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public E getLast() {
        checkElementsExist();
        return tail.value;
    }

    private void checkElementsExist() {
        if (head == null) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public void remove(int index) {
        if (index == 0) {
            Objects.checkIndex(index, size);
            removeHead();
        } else {
            Node<E> previousNode = findNodeByIndex(index - 1);
            previousNode.next = previousNode.next.next;
            if (index == size - 1) {
                tail = previousNode;
            }
        }
        size--;
    }

    private void removeHead() {
        head = head.next;
        if (head == null) {
            tail = null;
        }
    }

    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(E element) {
        Node<E> currentNode = head;
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
        return head == null;
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
        head = tail = null;
        size = 0;
    }

    static class Node<E> {
        private E value;
        private Node<E> next;

        private Node(E value) {
            this.value = value;
        }

        public static <T> Node<T> valueOf(T value) {
            return new Node<>(value);
        }
    }
}
