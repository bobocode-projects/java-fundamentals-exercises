package com.bobocode.array_wrapper;

import com.bobocode.linked_list.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArrayWrapperTest {

    private List<Integer> arrayWrapper = new ArrayWrapper<>();

    @Test
    @Order(1)
    public void add() {
        arrayWrapper.add(10);
        arrayWrapper.add(15);
        arrayWrapper.add(20);

        assertThat(arrayWrapper.get(0)).isEqualTo(10);
        assertThat(arrayWrapper.get(1)).isEqualTo(15);
        assertThat(arrayWrapper.get(2)).isEqualTo(20);
    }

    @Test
    @Order(7)
    public void sizeOfEmptyArrayWrapper() {
        assertThat(arrayWrapper.size()).isEqualTo(0);
    }

    @Test
    @Order(4)
    void testSize() {
        arrayWrapper.add(10);
        arrayWrapper.add(15);
        arrayWrapper.add(20);

        assertThat(arrayWrapper.size()).isEqualTo(3);
    }


    @Test
    @Order(2)
    public void getElementsByIndex() {
        arrayWrapper.add(10);
        arrayWrapper.add(15);
        arrayWrapper.add(20);

        assertThat(arrayWrapper.get(0)).isEqualTo(10);
        assertThat(arrayWrapper.get(1)).isEqualTo(15);
        assertThat(arrayWrapper.get(2)).isEqualTo(20);
        assertThat(arrayWrapper.size()).isEqualTo(3);
    }

    @Test
    @Order(5)
    void testGetFirstElement() {
        arrayWrapper = ArrayWrapper.of(31, 32);

        assertThat(arrayWrapper.getFirst()).isEqualTo(31);

    }

    @Test
    @Order(6)
    void testGetLastElement() {
        arrayWrapper = ArrayWrapper.of(21, 34);

        assertThat(arrayWrapper.getLast()).isEqualTo(34);
    }

    @Test
    @Order(7)
    void testGetFirstOfEmptyList() {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> arrayWrapper.getFirst());
    }

    @Test
    @Order(8)
    void testGetLastOfEmptyList() {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> arrayWrapper.getLast());
    }

    @Test
    @Order(4)
    public void createListWithSpecificCapacity() {
        arrayWrapper = new ArrayWrapper<>(8);

        arrayWrapper.add(10);
        arrayWrapper.add(15);
        arrayWrapper.add(20);

        assertThat(arrayWrapper.get(0)).isEqualTo(10);
        assertThat(arrayWrapper.get(1)).isEqualTo(15);
        assertThat(arrayWrapper.get(2)).isEqualTo(20);
    }

    @Test
    @Order(5)
    public void createListWithWrongCapacity() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> arrayWrapper = new ArrayWrapper<>(-2));
    }

    @Test
    @Order(6)
    public void addElements() {
        arrayWrapper = ArrayWrapper.of(15, 69, 58, 78);

        assertThat(arrayWrapper.get(0)).isEqualTo(15);
        assertThat(arrayWrapper.get(1)).isEqualTo(69);
        assertThat(arrayWrapper.get(2)).isEqualTo(58);
        assertThat(arrayWrapper.get(3)).isEqualTo(78);
        assertThat(arrayWrapper.size()).isEqualTo(4);
    }

    @Test
    @Order(8)
    public void resizeDefaultCapacity() {
        arrayWrapper = new ArrayWrapper<>();

        arrayWrapper.add(15);
        arrayWrapper.add(69);
        arrayWrapper.add(58);
        arrayWrapper.add(78);
        arrayWrapper.add(6);
        arrayWrapper.add(33);
        arrayWrapper.add(21);

        assertThat(arrayWrapper.get(6)).isEqualTo(21);
        assertThat(arrayWrapper.size()).isEqualTo(7);
    }

    @Test
    @Order(9)
    public void resizeSpecificCapacity() {
        arrayWrapper = new ArrayWrapper<>(4);

        arrayWrapper.add(15);
        arrayWrapper.add(69);
        arrayWrapper.add(58);
        arrayWrapper.add(78);
        arrayWrapper.add(6);
        arrayWrapper.add(33);
        arrayWrapper.add(21);

        assertThat(arrayWrapper.get(6)).isEqualTo(21);
        assertThat(arrayWrapper.size()).isEqualTo(7);
    }

    @Test
    @Order(10)
    public void addElementByIndex() {
        arrayWrapper = ArrayWrapper.of(15, 69, 58, 78, 68);

        arrayWrapper.add(50);
        arrayWrapper.add(2, 10);

        assertThat(arrayWrapper.get(2)).isEqualTo(10);
        assertThat(arrayWrapper.get(5)).isEqualTo(68);
        assertThat(arrayWrapper.size()).isEqualTo(7);
    }

    @Test
    @Order(14)
    void testAddElementByNegativeIndex() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayWrapper.add(-1, 66));

    }

    @Test
    @Order(15)
    void testAddElementByIndexLargerThanListSize() {
        arrayWrapper = ArrayWrapper.of(4, 6, 11, 9);

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayWrapper.add(5, 88));
    }

    @Test
    @Order(16)
    void testAddElementByIndexEqualToSize() {
        arrayWrapper = ArrayWrapper.of(1, 2, 3, 4, 5); // size = 5

        arrayWrapper.add(5, 111);

        assertThat(arrayWrapper.get(5)).isEqualTo(111);
        assertThat(arrayWrapper.size()).isEqualTo(6);
    }

    @Test
    @Order(11)
    public void getFirstElementFromEmptyList() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayWrapper.get(0));
    }

    @Test
    @Order(12)
    public void getElementByNegativeIndex() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayWrapper.get(-1));
    }

    @Test
    @Order(13)
    public void getElementByIndexEqualToListSize() {
        arrayWrapper = ArrayWrapper.of(15, 69, 58, 78);

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayWrapper.get(4));
    }

    @Test
    @Order(14)
    public void setElementByIndex() {
        arrayWrapper = ArrayWrapper.of(15, 69, 58, 78);

        arrayWrapper.set(2, 10);

        assertThat(arrayWrapper.get(2)).isEqualTo(10);
        assertThat(arrayWrapper.get(3)).isEqualTo(78);
        assertThat(arrayWrapper.size()).isEqualTo(4);
    }

    @Test
    @Order(15)
    public void setElementByIndexEqualToSize() {
        arrayWrapper = ArrayWrapper.of(15, 69, 58, 78);

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayWrapper.set(4, 10));
    }

    @Test
    @Order(17)
    void testSetFirstElementOnEmptyTree() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayWrapper.set(0, 34));
    }

    @Test
    @Order(16)
    public void removeElementByIndex() {
        arrayWrapper = ArrayWrapper.of(15, 69, 58, 78, 100);

        arrayWrapper.remove(2);

        assertThat(arrayWrapper.get(2)).isEqualTo(78);
        assertThat(arrayWrapper.get(1)).isEqualTo(69);
        assertThat(arrayWrapper.size()).isEqualTo(4);
    }

    @Test
    @Order(17)
    public void removeElementByIndexEqualToSize() {
        arrayWrapper = ArrayWrapper.of(15, 69, 58, 78);

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayWrapper.remove(4));
    }

    @Test
    @Order(18)
    public void removeLastElementByIndex() {
        arrayWrapper = ArrayWrapper.of(15, 69, 58, 78, 100);

        arrayWrapper.remove(4);

        assertThat(arrayWrapper.get(3)).isEqualTo(78);
        assertThat(arrayWrapper.size()).isEqualTo(4);
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayWrapper.get(4));
    }

    @Test
    @Order(19)
    public void removeElementByIndexOutOfBounds() {
        arrayWrapper = ArrayWrapper.of(15, 69, 58, 78, 100);

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayWrapper.remove(6));
    }

    @Test
    @Order(27)
    void testContainsOnEmptyList() {
        assertThat(arrayWrapper.contains(8)).isEqualTo(false);
    }

    @Test
    @Order(20)
    public void containsElement() {
        arrayWrapper = ArrayWrapper.of(15, 69, 58, 78, 100);

        assertThat(arrayWrapper.contains(58)).isEqualTo(true);
    }

    @Test
    @Order(21)
    public void findNotExistingElement() {
        arrayWrapper = ArrayWrapper.of(15, 69, 58, 78, 100);

        assertThat(arrayWrapper.contains(200)).isEqualTo(false);
    }

    @Test
    @Order(29)
    void testIsEmptyOnEmptyList() {
        assertThat(arrayWrapper.isEmpty()).isEqualTo(true);
    }

    @Test
    @Order(30)
    void testIsEmpty() {
        arrayWrapper = ArrayWrapper.of(34, 5, 6);

        assertThat(arrayWrapper.isEmpty()).isEqualTo(false);
    }

    @Test
    @Order(32)
    void testClearOnEmptyList() {
        assertThat(arrayWrapper.isEmpty()).isEqualTo(true);
    }

    @Test
    @Order(33)
    void testClearChangesTheSize() {
        arrayWrapper = ArrayWrapper.of(4, 5, 6);

        arrayWrapper.clear();

        assertThat(arrayWrapper.size()).isEqualTo(0);
    }

    @Test
    @Order(34)
    void testClearRemovesElements() {
        arrayWrapper = ArrayWrapper.of(4, 5, 6);

        arrayWrapper.clear();

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayWrapper.get(0));
    }
}
