package com.bobocode.cs;

import lombok.SneakyThrows;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

/**
 * A reflection-based test class for {@link ArrayList}.
 * <p>
 * PLEASE NOTE: we use Reflection API only for learning purposes. It should NOT be used for production tests.
 *
 * @author Serhii Hryhus
 * @author Ivan Virchenko
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArrayListTest {

    private List<Integer> arrayList = new ArrayList<>();

    @Test
    @Order(1)
    void add() {
        arrayList.add(10);
        arrayList.add(15);
        arrayList.add(20);

        Object[] internalArray = getTestArray();

        assertThat(internalArray[0]).isEqualTo(10);
        assertThat(internalArray[1]).isEqualTo(15);
        assertThat(internalArray[2]).isEqualTo(20);
    }

    @Test
    @Order(2)
    void sizeOfEmptyArrayWrapper() {
        assertThat(arrayList.size()).isEqualTo(0);
    }

    @Test
    @Order(3)
    void size() {
        setTestSize(3);
        assertThat(arrayList.size()).isEqualTo(3);
    }

    @Test
    @Order(4)
    void getElementsByIndex() {
        fillTestArray(10, 15, 20);

        assertThat(arrayList.get(0)).isEqualTo(10);
        assertThat(arrayList.get(1)).isEqualTo(15);
        assertThat(arrayList.get(2)).isEqualTo(20);
        assertThat(arrayList.size()).isEqualTo(3);
    }

    @Test
    @Order(5)
    void getFirstElement() {
        fillTestArray(31, 24);
        assertThat(arrayList.getFirst()).isEqualTo(31);
    }

    @Test
    @Order(6)
    void getLastElement() {
        fillTestArray(31, 34);
        assertThat(arrayList.getLast()).isEqualTo(34);
    }

    @Test
    @Order(7)
    void getFirstOfEmptyList() {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> arrayList.getFirst());
    }

    @Test
    @Order(8)
    void getLastOfEmptyList() {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> arrayList.getLast());
    }

    @Test
    @Order(9)
    void createListWithSpecificArrayCapacity() {
        arrayList = new ArrayList<>(8);
        assertThat(getTestArray().length).isEqualTo(8);
    }

    @Test
    @Order(10)
    void createListWithWrongCapacity() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> arrayList = new ArrayList<>(-2));
    }

    @Test
    @Order(11)
    void addElements() {
        arrayList = ArrayList.of(15, 69, 58, 78);

        assertThat(getTestArray()[0]).isEqualTo(15);
        assertThat(getTestArray()[1]).isEqualTo(69);
        assertThat(getTestArray()[2]).isEqualTo(58);
        assertThat(getTestArray()[3]).isEqualTo(78);
        assertThat(getTestSize()).isEqualTo(4);
    }

    @Test
    @Order(12)
    void addShouldResizeDefaultCapacityWhenArrayIsFull() {
        arrayList = new ArrayList<>();
        int defaultCapacity = getTestArray().length;

        arrayList.add(15);
        arrayList.add(69);
        arrayList.add(58);
        arrayList.add(78);
        arrayList.add(6);
        arrayList.add(33);
        arrayList.add(21);

        assertThat(getTestArray().length).isGreaterThan(defaultCapacity);
        assertThat(getTestSize()).isEqualTo(7);
    }

    @Test
    @Order(13)
    void addShouldResizeSpecificCapacityWhenArrayIsFull() {
        arrayList = new ArrayList<>(4);

        arrayList.add(15);
        arrayList.add(69);
        arrayList.add(58);
        arrayList.add(78);
        arrayList.add(6);
        arrayList.add(33);
        arrayList.add(21);

        assertThat(getTestArray().length).isGreaterThan(4);
        assertThat(getTestSize()).isEqualTo(7);
    }

    @Test
    @Order(14)
    void addElementByIndex() {
        fillTestArray(15, 69, 58, 78, 68);

        arrayList.add(50);
        arrayList.add(2, 10);

        Object[] internalArray = getTestArray();

        assertThat(internalArray[2]).isEqualTo(10);
        assertThat(internalArray[5]).isEqualTo(68);
        assertThat(getTestSize()).isEqualTo(7);
    }

    @Test
    @Order(15)
    void addElementByNegativeIndex() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.add(-1, 66));
    }

    @Test
    @Order(16)
    void addElementByIndexLargerThanListSize() {
        setTestSize(4);
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.add(5, 88));
    }

    @Test
    @Order(17)
    void addElementByIndexEqualToSize() {
        fillTestArray(1, 2, 3, 4, 5);

        arrayList.add(5, 111);

        Object[] internalArray = getTestArray();

        assertThat(internalArray[5]).isEqualTo(111);
        assertThat(getTestSize()).isEqualTo(6);
    }

    @Test
    @Order(18)
    void getFirstElementFromEmptyList() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.get(0));
    }

    @Test
    @Order(19)
    void getElementByNegativeIndex() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.get(-1));
    }

    @Test
    @Order(20)
    void getElementByIndexThrowsExceptionWhenIndexIsOutOfBound() {
        fillTestArray(1, 2, 3, 4);
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.get(4));
    }

    @Test
    @Order(21)
    void setElementByIndex() {
        fillTestArray(15, 69, 58, 78);
        Object[] internalArray = getTestArray();

        arrayList.set(2, 10);

        assertThat(internalArray[2]).isEqualTo(10);
        assertThat(internalArray[3]).isEqualTo(78);
        assertThat(getTestSize()).isEqualTo(4);
    }

    @Test
    @Order(22)
    void setElementByIndexThrowsExceptionWhenIndexIsOutOfBound() {
        fillTestArray(15, 69, 58, 78);
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.set(4, 10));
    }

    @Test
    @Order(23)
    void setFirstElementOnEmptyTree() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.set(0, 34));
    }

    @Test
    @Order(24)
    void removeElementByIndex() {
        fillTestArray(15, 69, 58, 78, 100);
        Object[] internalArray = getTestArray();

        int removedElement = arrayList.remove(2);

        assertThat(internalArray[2]).isEqualTo(78);
        assertThat(internalArray[1]).isEqualTo(69);
        assertThat(getTestSize()).isEqualTo(4);
        assertThat(removedElement).isEqualTo(58);
    }

    @Test
    @Order(25)
    void removeElementByIndexThrowsExceptionWhenIndexEqualsSize() {
        fillTestArray(15, 69, 58, 78);
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.remove(4));
    }

    @Test
    @Order(26)
    void removeLastElementByIndex() {
        fillTestArray(15, 69, 58, 78, 100);
        Object[] internalArray = getTestArray();

        int removedElement = arrayList.remove(4);

        assertThat(internalArray[3]).isEqualTo(78);
        assertThat(getTestSize()).isEqualTo(4);
        assertThat(removedElement).isEqualTo(100);
    }

    @Test
    @Order(27)
    void removeElementByIndexThrowsExceptionWhenIndexIsOutOfBounds() {
        fillTestArray(15, 69, 58, 78, 100);

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.remove(6));
    }

    @Test
    @Order(28)
    void containsOnEmptyList() {
        assertThat(arrayList.contains(8)).isEqualTo(false);
    }

    @Test
    @Order(29)
    void containsElement() {
        fillTestArray(15, 69, 58, 78, 100);
        assertThat(arrayList.contains(58)).isEqualTo(true);
    }

    @Test
    @Order(30)
    void containsNotExistingWhenArrayIsNotFilled() {
        arrayList = new ArrayList<>(100);
        Object[] internalArray = getTestArray();
        internalArray[0] = 5;
        internalArray[1] = 10;

        boolean result = arrayList.contains(3);

        assertThat(result).isFalse();
    }

    @Test
    @Order(31)
    void findNotExistingElement() {
        fillTestArray(15, 69, 58, 78, 100);
        assertThat(arrayList.contains(200)).isEqualTo(false);
    }

    @Test
    @Order(32)
    void isEmptyOnEmptyList() {
        assertThat(arrayList.isEmpty()).isEqualTo(true);
    }

    @Test
    @Order(33)
    void isEmpty() {
        setTestSize(3);
        assertThat(arrayList.isEmpty()).isEqualTo(false);
    }

    @Test
    @Order(34)
    void clearOnEmptyList() {
        assertThat(arrayList.isEmpty()).isEqualTo(true);
    }

    @Test
    @Order(35)
    void clearChangesTheSize() {
        setTestSize(100);
        arrayList.clear();

        assertThat(arrayList.size()).isEqualTo(0);
    }

    @Test
    @Order(36)
    void clearRemovesElements() {
        fillTestArray(4, 5, 6);

        arrayList.clear();

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.get(0));
    }

    @SneakyThrows
    private void setTestSize(int size) {
        Field sizeField = arrayList.getClass().getDeclaredField("size");
        sizeField.setAccessible(true);
        sizeField.set(arrayList, size);
    }

    @SneakyThrows
    private int getTestSize() {
        Field testSize = arrayList.getClass().getDeclaredField("size");
        testSize.setAccessible(true);
        return (int) testSize.get(arrayList);
    }

    @SneakyThrows
    private Object[] getTestArray() {
        Field field = arrayList.getClass().getDeclaredField(getTestArrayName());
        field.setAccessible(true);
        return (Object[]) field.get(arrayList);
    }

    private String getTestArrayName() {
        Field[] fields = arrayList.getClass().getDeclaredFields();
        String name = null;
        for (Field field : fields) {
            if (field.getType().isArray()) {
                field.setAccessible(true);
                name = field.getName();
            }
        }
        return name;
    }

    @SneakyThrows
    private void fillTestArray(Object... elements) {
        Field arrayField = arrayList.getClass().getDeclaredField(getTestArrayName());
        Field sizeField = arrayList.getClass().getDeclaredField("size");
        arrayField.setAccessible(true);
        sizeField.setAccessible(true);
        arrayField.set(arrayList, elements);
        sizeField.set(arrayList, elements.length);
    }
}
