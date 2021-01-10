package com.bobocode.array_wrapper;

import java.util.Arrays;
import java.util.Objects;

/**
 * {@link ArrayListOfLongs} is an implementation of {@link ListOfLongs} interface. This resizable data structure
 * based on an array and is simplified version of {@link java.util.ArrayList}.
 */
public class ArrayListOfLongs implements ListOfLongs {

    public static final int DEFAULT_CAPACITY = 5;
    private long[] elementData;
    private int size;

    /**
     * This constructor creates an instance of {@link ArrayListOfLongs} with a specific capacity of an array inside.
     *
     * @param initCapacity - the initial capacity of the list
     * @throws IllegalArgumentException – if the specified initial capacity is negative or 0.
     */
    public ArrayListOfLongs(int initCapacity) {
        if (initCapacity > 0) {
            elementData = new long[initCapacity];
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * This constructor creates an instance of {@link ArrayListOfLongs} with a default capacity of an array inside.
     * A default size of inner array is 5;
     */
    public ArrayListOfLongs() {
        elementData = new long[DEFAULT_CAPACITY];
    }

    /**
     * Creates and returns an instance of {@link ArrayListOfLongs} with provided elements
     *
     * @param elements to add
     * @return new instance
     */
    public static ListOfLongs of(long... elements) {
        ListOfLongs listOfLongs = new ArrayListOfLongs(elements.length);
        for (long element : elements) {
            listOfLongs.add(element);
        }
        return listOfLongs;
    }

    /**
     * Adds an element to the array and returns index of position.
     *
     * @param element element to add
     * @return index of element
     */
    @Override
    public int add(long element) {
        increaseDataArrayIfFull();
        elementData[size] = element;
        return size++;
    }

    private void increaseDataArrayIfFull() {
        if (elementData.length <= size) {
            elementData = getTrimmedArrayToSize(elementData.length * 2);
        }
    }

    /**
     * Adds an element to the specific position in the array where
     *
     * @param index   index of position
     * @param element element to add
     */
    @Override
    public void add(int index, long element) {
        increaseDataArrayIfFull();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * Retrieves an element by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index index of element
     * @return en element
     */
    @Override
    public long get(int index) {
        Objects.checkIndex(index, size);
        return elementData[index];
    }

    /**
     * Changes the value of array at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   position of value
     * @param element a new value
     */
    @Override
    public void set(int index, long element) {
        Objects.checkIndex(index, size);
        elementData[index] = element;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     */
    @Override
    public void remove(int index) {
        if (index == size - 1) {
            elementData = getTrimmedArrayToSize(size - 1);
        } else {
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        }
        size--;
    }

    /**
     * Finds an index of the element.
     *
     * @param element is element
     * @return If element exists method returns its first position index, otherwise it returns -1
     */
    @Override
    public int find(long element) {
        int i = 0;
        int index = -1;
        while (i < size) {
            if (elementData[i] == element) {
                index = i;
                break;
            }
            i++;
        }
        return index;
    }

    /**
     * This method retrieves an array with all the elements without empty tail.
     *
     * @return an array
     */
    @Override
    public long[] toArray() {
        return getTrimmedArrayToSize(size);
    }

    private long[] getTrimmedArrayToSize(int size) {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * @return A String implementation of the wrapper
     */
    @Override
    public String toString() {
        return Arrays.toString(getTrimmedArrayToSize(size));
    }

    /**
     * @return amount of the saved elements
     */
    @Override
    public int size() {
        return size;
    }
}
