package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;
import java.util.stream.Stream;

/**
 * {@link ArrayList} is an implementation of {@linkedList} interface. This resizable data structure
 * based on an array and is simplified version of {@link java.util.ArrayList}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @author Serhii Hryhus
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 5;
    private Object[] elementData;
    private static int size = 0;

    /**
     * This constructor creates an instance of {@link ArrayList} with a specific capacity of an array inside.
     *
     * @param initCapacity - the initial capacity of the list
     * @throws IllegalArgumentException – if the specified initial capacity is negative or 0.
     */
    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        elementData = new Object[initCapacity];
        size = 0;
    }

    /**
     * This constructor creates an instance of {@link ArrayList} with a default capacity of an array inside.
     * A default size of inner array is 5;
     */
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Creates and returns an instance of {@link ArrayList} with provided elements
     *
     * @param elements to add
     * @return new instance
     */
    public static <T> List<T> of(T... elements) {
        ArrayList<T> list = new ArrayList<>(elements.length);
        Arrays.stream(elements).forEach(list::add);
        size = elements.length;
        return list;
    }

    /**
     * Adds an element to the array.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        if (introducedValueIfArrayHasNull(element)) return;
        elementData = Arrays.copyOf(elementData, size + 1);
        elementData[size] = element;
        size ++;
    }
    private boolean introducedValueIfArrayHasNull(T element){
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == null) {
                elementData[i] = element;
                size++;
                return true;
            }
        }
        return false;
    }
    /**
     * Adds an element to the specific position in the array where
     *
     * @param index   index of position
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        if (0 > index || index > size) throw new IndexOutOfBoundsException();
        elementData = Arrays.copyOf(elementData, size + 1);
        System.arraycopy(elementData,index,elementData,index+1,size-index);
        elementData[index] = element;
        size ++;
    }

    /**
     * Retrieves an element by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index index of element
     * @return en element
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= size){
            throw new IndexOutOfBoundsException();
        }
        return (T) elementData[index];
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    @SuppressWarnings("unchecked")
    public T getFirst() {
        if (size == 0) throw new NoSuchElementException();
        return (T) elementData[0];
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    @SuppressWarnings("unchecked")
    public T getLast() {
        if (size == 0) throw new NoSuchElementException();
        return (T) elementData[size-1];
    }

    /**
     * Changes the value of array at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   position of value
     * @param element a new value
     */
    @Override
    public void set(int index, T element) {
        if (0 >= index || index > size) throw new IndexOutOfBoundsException();
        elementData[index] = element;
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
        if (0 > index || index > size) throw new IndexOutOfBoundsException();
        T removed = this.get(index);
        System.arraycopy(elementData,index+1,elementData,index,size-(index+1));
        size --;
        return removed;
    }

    /**
     * Checks for existing of a specific element in the list.
     *
     * @param element is element
     * @return If element exists method returns true, otherwise it returns false
     */
    @Override
    public boolean contains(T element) {
        return Stream.of(elementData).anyMatch(e -> e == element);
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return amount of saved elements
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
        elementData = null;
        size = 0;
    }
}
