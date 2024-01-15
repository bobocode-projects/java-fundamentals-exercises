package com.bobocode.tdd;


import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class LinkedListTest {

    private List<Integer> intList = new LinkedList<>();

    @Test
    void addIntoEmptyList() {
        intList.add(41);

        int element = intList.get(0);

        assertThat(element).isEqualTo(41);
        assertThat(intList.size()).isEqualTo(1);
    }

    @Test
    void getFirstElementFromSingleElementList() {
        intList.add(25);

        int element = intList.get(0);

        assertThat(element).isEqualTo(25);
    }

    @Test
    void addElements() {
        intList = LinkedList.of(43, 233, 54);

        assertThat(intList.size()).isEqualTo(3);
        assertThat(intList.get(0)).isEqualTo(43);
        assertThat(intList.get(1)).isEqualTo(233);
        assertThat(intList.get(2)).isEqualTo(54);
    }

    @Test
    void size() {
        intList = LinkedList.of(4, 7, 9, 0, 7);

        int size = intList.size();

        assertThat(size).isEqualTo(5);
    }

    @Test
    void getFirstElement() {
        intList = LinkedList.of(31, 32);

        int firstElement = intList.getFirst();
        assertThat(firstElement).isEqualTo(31);
    }

    @Test
    void getLastElement() {
        intList = LinkedList.of(41, 42);

        int lastElement = intList.getLast();

        assertThat(lastElement).isEqualTo(42);
    }

    @Test
    void getFirstOfEmptyList() {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> intList.getFirst());
    }

    @Test
    void getLastOfEmptyList() {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> intList.getLast());
    }


    @Test
    void getElements() {
        intList = LinkedList.of(25, 87, 45);

        int firstElement = intList.get(0);
        int secondElement = intList.get(1);
        int thirdElement = intList.get(2);

        assertThat(firstElement).isEqualTo(25);
        assertThat(secondElement).isEqualTo(87);
        assertThat(thirdElement).isEqualTo(45);

    }

    @Test
    void addElementByZeroIndexIntoEmptyList() {
        intList.add(0, 45);

        int element = intList.get(0);

        assertThat(element).isEqualTo(45);
        assertThat(intList.size()).isEqualTo(1);
    }

    @Test
    void addElementByIndexToTheEndOfList() {
        intList = LinkedList.of(98, 64, 23, 1, 3, 4);

        int newElementIndex = intList.size();
        intList.add(newElementIndex, 44);

        assertThat(intList.get(newElementIndex)).isEqualTo(44);
        assertThat(intList.size()).isEqualTo(7);
    }

    @Test
    void addElementToTheHeadOfNonEmptyList() {
        intList = LinkedList.of(4, 6, 8, 9, 0, 2);

        intList.add(0, 53);

        assertThat(intList.get(0)).isEqualTo(53);
        assertThat(intList.get(1)).isEqualTo(4);
        assertThat(intList.size()).isEqualTo(7);
    }

    @Test
    void addElementByIndex() {
        intList = LinkedList.of(43, 5, 6, 8);

        int newElementIdx = 2;
        intList.add(newElementIdx, 66);

        assertThat(intList.get(newElementIdx)).isEqualTo(66);
        assertThat(intList.get(0)).isEqualTo(43);
        assertThat(intList.get(1)).isEqualTo(5);
        assertThat(intList.get(3)).isEqualTo(6);
        assertThat(intList.get(4)).isEqualTo(8);
        assertThat(intList.size()).isEqualTo(5);
    }

    @Test
    void addElementByNegativeIndex() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> intList.add(-1, 66));
    }

    @Test
    void addElementByIndexLargerThanListSize() {
        intList = LinkedList.of(4, 6, 11, 9);

        int newElementIdx = 5;

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> intList.add(newElementIdx, 88));
    }

    @Test
    void addElementByIndexEqualToSize() {
        intList = LinkedList.of(1, 2, 3, 4, 5); // size = 5

        intList.add(5, 111);
        int element = intList.get(5);

        assertThat(element).isEqualTo(111);
        assertThat(intList.size()).isEqualTo(6);
    }

    @Test
    void setFirstElementOnEmptyTree() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> intList.set(0, 34));
    }

    @Test
    void setElementByIndexEqualToSize() {
        intList = LinkedList.of(2, 3, 4); // size = 3

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> intList.set(3, 222));
    }

    @Test
    void setElementByIndex() {
        intList = LinkedList.of(34, 78, 9, 8);

        int index = 2; //element = 78
        intList.set(index, 99);

        assertThat(intList.get(index)).isEqualTo(99);
        assertThat(intList.get(0)).isEqualTo(34);
        assertThat(intList.get(1)).isEqualTo(78);
        assertThat(intList.get(3)).isEqualTo(8);
        assertThat(intList.size()).isEqualTo(4);
    }

    @Test
    void getFirstElementFromEmptyList() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> intList.get(0));
    }

    @Test
    void getElementByNegativeIndex() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> intList.get(-1));
    }

    @Test
    void getElementByIndexEqualsToListSize() {
        intList = LinkedList.of(33, 46, 25, 87, 45);
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> intList.get(5));
    }

    @Test
    void removeElementFromEmptyList() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> intList.remove(234));
    }

    @Test
    void removeFirstElement() {
        intList = LinkedList.of(4, 6, 8, 9);

        int deletedElement = intList.remove(0);

        assertThat(intList.get(0)).isEqualTo(6);
        assertThat(intList.size()).isEqualTo(3);
        assertThat(deletedElement).isEqualTo(4);
    }

    @Test
    void removeLastElement() {
        intList = LinkedList.of(4, 6, 8, 9);

        int deletedElement = intList.remove(intList.size() - 1);

        assertThat(intList.get(intList.size() - 1)).isEqualTo(8);
        assertThat(intList.size()).isEqualTo(3);
        assertThat(deletedElement).isEqualTo(9);
    }

    @Test
    void removeElement() {
        intList = LinkedList.of(1, 2, 3, 4, 5);

        int elementIndex = 2;
        int deletedElement = intList.remove(elementIndex); // element = 3

        assertThat(intList.get(elementIndex)).isEqualTo(4);
        assertThat(intList.size()).isEqualTo(4);
        assertThat(deletedElement).isEqualTo(3);
    }

    @Test
    void containsOnEmptyList() {
        boolean contains = intList.contains(34);

        assertThat(contains).isFalse();
    }

    @Test
    void contains() {
        intList = LinkedList.of(45, 6, 3, 6);

        boolean containsExistingElement = intList.contains(3);
        boolean containsNotExistingElement = intList.contains(54);

        assertThat(containsExistingElement).isTrue();
        assertThat(containsNotExistingElement).isFalse();
    }

    @Test
    void isEmptyOnEmptyList() {
        boolean empty = intList.isEmpty();

        assertThat(empty).isTrue();
    }

    @Test
    void isEmpty() {
        intList = LinkedList.of(34, 5, 6);

        boolean empty = intList.isEmpty();

        assertThat(empty).isFalse();
    }

    @Test
    void sizeOnEmptyList() {
        int size = intList.size();

        assertThat(size).isEqualTo(0);
    }

    @Test
    void clearOnEmptyList() {
        intList.clear();

        assertThat(intList.size()).isEqualTo(0);
    }

    @Test
    void clearChangesTheSize() {
        intList = LinkedList.of(4, 5, 6);

        intList.clear();

        assertThat(intList.size()).isEqualTo(0);
    }

    @Test
    void clearRemovesElements() {
        intList = LinkedList.of(4, 5, 6);

        intList.clear();

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> intList.get(0));
    }
}
