package com.bobocode.cs;


import com.bobocode.util.ExerciseNotCompletedException;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedList<T> implements List<T> {
    private static class Node<T> {
        T element;
        Node<T> next = null;

        private Node(T element) {
            this.element = element;
        }
    }
    private Node<T> head;
    private Node<T> tail;

    Node<T> temp;
    private int size = 0;

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
        if(head == null) {
            head = new Node<>(element);
            tail = head;
        } else {
            tail.next = new Node<>(element);
            tail = tail.next;
        }
        size ++;
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
        if (0 <= index && index <= size){
            if(index == 0){
                temp = head;
                head = new Node<>(element);
                head.next = temp;
                size ++;
                return;
            }
            Node<T> current = head;
            int i = 0;
            while(i < index-1){
                current = current.next;
                i++;
            }
            temp = current.next;
            current.next = new Node<>(element);
            current.next.next = temp;
            size ++;
        } else {
            throw new IndexOutOfBoundsException();
        }

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
        if (0 <= index && index < size){
            if(head == null && index == 0){
                head = new Node<>(element);
                size ++;
                return;
            }
            Node<T> current = head;
            int i = 0;
            while(i < index){
                current = current.next;
                i++;
            }
            current.element = element;

        } else {
            throw new IndexOutOfBoundsException();
        }
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
        if (0 <= index && index < size){
            Node<T> current = head;
            int i = 0;
            while(i < index){
                current = current.next;
                i++;
            }
            return current.element;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        if (head != null) return head.element;
        else throw new NoSuchElementException();
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        if (head != null) return tail.element;
        else throw new NoSuchElementException();
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
        if (0 <= index && index < size){
            if (index == 0) {
                temp = head;
                head = head.next;
                size --;
                return temp.element;
            }
            int i = 0;
            Node<T> current = head;
            while(i < index-1){
                current = current.next;
                i++;
            }
            temp = current.next;
            current.next = current.next.next;
            size --;
        } else {
            throw new IndexOutOfBoundsException();
        }
        return temp.element;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        if (head == null) {
            return false;
        }
        Node<T> current = head;
        while(current.next != null){
            if (current.element == element) return true;
            current = current.next;
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
        head = null;
        size = 0;
    }
}
