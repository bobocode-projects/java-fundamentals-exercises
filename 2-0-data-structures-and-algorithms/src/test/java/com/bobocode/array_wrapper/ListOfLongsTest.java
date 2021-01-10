package com.bobocode.array_wrapper;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ListOfLongsTest {

    private ListOfLongs listOfLongs = new ArrayListOfLongs();

    @Test
    @Order(1)
    public void addAndSize() {
        listOfLongs.add(10);
        listOfLongs.add(15);
        listOfLongs.add(20);

        int size = listOfLongs.size();

        assertEquals(3, size);
    }

    @Test
    @Order(2)
    public void getElementsByIndex() {
        listOfLongs.add(10);
        listOfLongs.add(15);
        listOfLongs.add(20);

        long getA = listOfLongs.get(0);
        long getB = listOfLongs.get(1);
        long getC = listOfLongs.get(2);

        int size = listOfLongs.size();

        assertEquals(10, getA);
        assertEquals(15, getB);
        assertEquals(20, getC);

        assertEquals(3, size);
    }

    @Test
    @Order(3)
    public void addElementIntoEmptyArray() {

        int index = listOfLongs.add(10);
        long element = listOfLongs.get(0);

        assertEquals(10, element);
        assertEquals(0, index);
    }

    @Test
    @Order(4)
    public void createListWithSpecificCapacity() {
        listOfLongs = new ArrayListOfLongs(8);

        int index = listOfLongs.add(10);
        long element = listOfLongs.get(0);

        assertEquals(10, element);
        assertEquals(0, index);
    }

    @Test
    @Order(5)
    public void createListWithWrongCapacity() {
        assertThrows(IllegalArgumentException.class, () -> listOfLongs = new ArrayListOfLongs(-2));
    }

    @Test
    @Order(6)
    public void addElements() {
        ListOfLongs listOfLongs = ArrayListOfLongs.of(15, 69, 58, 78);

        int size = listOfLongs.size();

        assertEquals(15, listOfLongs.get(0));
        assertEquals(69, listOfLongs.get(1));
        assertEquals(58, listOfLongs.get(2));
        assertEquals(78, listOfLongs.get(3));

        assertEquals(4, size);
    }

    @Test
    @Order(7)
    public void sizeOfEmptyArrayWrapper() {
        assertEquals(0, listOfLongs.size());
    }

    @Test
    @Order(8)
    public void resizeDefaultCapacity() {
        ListOfLongs listOfLongs = new ArrayListOfLongs();

        listOfLongs.add(15);
        listOfLongs.add(69);
        listOfLongs.add(58);
        listOfLongs.add(78);
        listOfLongs.add(6);
        listOfLongs.add(33);
        listOfLongs.add(21);

        assertEquals(21, listOfLongs.get(6));

        int size = listOfLongs.size();
        assertEquals(7, size);
    }

    @Test
    @Order(9)
    public void resizeSpecificCapacity() {
        ListOfLongs listOfLongs = new ArrayListOfLongs(4);

        listOfLongs.add(15);
        listOfLongs.add(69);
        listOfLongs.add(58);
        listOfLongs.add(78);
        listOfLongs.add(6);
        listOfLongs.add(33);
        listOfLongs.add(21);

        assertEquals(21, listOfLongs.get(6));

        int size = listOfLongs.size();
        assertEquals(7, size);
    }

    @Test
    @Order(10)
    public void addElementByIndex() {
        listOfLongs = ArrayListOfLongs.of(15, 69, 58, 78, 68);

        listOfLongs.add(50);
        listOfLongs.add(2, 10);
        int size = listOfLongs.size();

        assertEquals(10, listOfLongs.get(2));
        assertEquals(68, listOfLongs.get(5));
        assertEquals(7, size);
    }

    @Test
    @Order(11)
    public void getFirstElementFromEmptyArray() {
        assertThrows(IndexOutOfBoundsException.class, () -> listOfLongs.get(0));
    }

    @Test
    @Order(12)
    public void getElementByNegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> listOfLongs.get(-1));
    }

    @Test
    @Order(13)
    public void getElementByIndexEqualToArraySize() {
        listOfLongs = ArrayListOfLongs.of(15, 69, 58, 78);
        assertThrows(IndexOutOfBoundsException.class, () -> listOfLongs.get(4));
    }

    @Test
    @Order(14)
    public void setElementByIndex() {
        listOfLongs = ArrayListOfLongs.of(15, 69, 58, 78);

        listOfLongs.set(2, 10);

        assertEquals(10, listOfLongs.get(2));
        assertEquals(78, listOfLongs.get(3));
        assertEquals(4, listOfLongs.size());
    }

    @Test
    @Order(15)
    public void setElementByIndexEqualToSize() {
        listOfLongs = ArrayListOfLongs.of(15, 69, 58, 78);

        assertThrows(IndexOutOfBoundsException.class, () -> listOfLongs.set(4, 10));
    }

    @Test
    @Order(16)
    public void removeElementByIndex() {
        listOfLongs = ArrayListOfLongs.of(15, 69, 58, 78, 100);

        listOfLongs.remove(2);

        assertEquals(78, listOfLongs.get(2));
        assertEquals(69, listOfLongs.get(1));
        assertEquals(4, listOfLongs.size());
    }

    @Test
    @Order(17)
    public void removeElementByIndexEqualToSize() {
        listOfLongs = ArrayListOfLongs.of(15, 69, 58, 78);

        assertThrows(IndexOutOfBoundsException.class, () -> listOfLongs.remove(4));
    }

    @Test
    @Order(18)
    public void removeLastElementByIndex() {
        listOfLongs = ArrayListOfLongs.of(15, 69, 58, 78, 100);

        listOfLongs.remove(4);

        assertEquals(78, listOfLongs.get(3));
        assertEquals(4, listOfLongs.size());
        assertThrows(IndexOutOfBoundsException.class, () -> listOfLongs.get(4));
    }

    @Test
    @Order(19)
    public void removeElementByIndexOutOfBounds() {
        listOfLongs = ArrayListOfLongs.of(15, 69, 58, 78, 100);

        assertThrows(IndexOutOfBoundsException.class, () -> listOfLongs.remove(6));
    }

    @Test
    @Order(20)
    public void findIndexOfElement() {
        listOfLongs = ArrayListOfLongs.of(15, 69, 58, 78, 100);

        int index = listOfLongs.find(58);
        assertEquals(2, index);
    }

    @Test
    @Order(21)
    public void findNotExistingElement() {
        listOfLongs = ArrayListOfLongs.of(15, 69, 58, 78, 100);

        int index = listOfLongs.find(60);
        assertEquals(-1, index);
    }

    @Test
    @Order(22)
    public void toArray() {
        listOfLongs = ArrayListOfLongs.of(15, 69, 58, 78, 100);
        listOfLongs.add(265);

        long[] result = listOfLongs.toArray();
        long[] expected = {15, 69, 58, 78, 100, 265};

        assertEquals(Arrays.toString(expected), Arrays.toString(result));
    }

    @Test
    @Order(23)
    public void listToString() {
        listOfLongs = ArrayListOfLongs.of(15, 69, 58, 78, 100);
        listOfLongs.add(265);

        String result = listOfLongs.toString();
        long[] expected = {15, 69, 58, 78, 100, 265};

        assertEquals(Arrays.toString(expected), result);
    }
}
