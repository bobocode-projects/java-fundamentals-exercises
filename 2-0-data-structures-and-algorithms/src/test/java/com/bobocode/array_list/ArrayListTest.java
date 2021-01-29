package com.bobocode.array_list;

import com.bobocode.linked_list.List;
import net.bytebuddy.description.field.FieldList;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArrayListTest {

    private List<Integer> arrayList = new ArrayList<>();

    @Test
    @Order(1)
    public void add() {
        arrayList.add(10);
        arrayList.add(15);
        arrayList.add(20);

        Object[] elements = (Object[]) getObjectByName("elementData");

        assertThat(elements[0]).isEqualTo(10);
        assertThat(elements[1]).isEqualTo(15);
        assertThat(elements[2]).isEqualTo(20);
    }

    @Test
    @Order(2)
    public void sizeOfEmptyArrayWrapper() {
        assertThat(arrayList.size()).isEqualTo(0);
    }

    @Test
    @Order(3)
    void size() {
        try {
            getFieldByName("size").set(arrayList, 3);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        assertThat(arrayList.size()).isEqualTo(3);
    }

    @Test
    @Order(4)
    public void getElementsByIndex() {
        try {
            getFieldByName("elementData").set(arrayList, new Object[]{10, 15, 20});
            getFieldByName("size").set(arrayList, 3);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        assertThat(arrayList.get(0)).isEqualTo(10);
        assertThat(arrayList.get(1)).isEqualTo(15);
        assertThat(arrayList.get(2)).isEqualTo(20);
        assertThat(arrayList.size()).isEqualTo(3);
    }

    @Test
    @Order(5)
    public void getFirstElement() {
        try {
            getFieldByName("size").set(arrayList, 2);
            getFieldByName("elementData").set(arrayList, new Object[]{31, 24});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        assertThat(arrayList.getFirst()).isEqualTo(31);
    }

    @Test
    @Order(6)
    public void getLastElement() {
        try {
            getFieldByName("size").set(arrayList, 2);
            getFieldByName("elementData").set(arrayList, new Object[]{31, 34});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        assertThat(arrayList.getLast()).isEqualTo(34);
    }

    @Test
    @Order(7)
    public void getFirstOfEmptyList() {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> arrayList.getFirst());
    }

    @Test
    @Order(8)
    public void getLastOfEmptyList() {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> arrayList.getLast());
    }

    @Test
    @Order(9)
    public void listWithSpecificCapacity() {
        arrayList = new ArrayList<>(8);

        try {
            Object[] elements = new Object[8];
            elements[0] = 10;
            elements[1] = 15;
            elements[2] = 20;
            getFieldByName("size").set(arrayList, elements.length);
            getFieldByName("elementData").set(arrayList, elements);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        assertThat(arrayList.get(0)).isEqualTo(10);
        assertThat(arrayList.get(1)).isEqualTo(15);
        assertThat(arrayList.get(2)).isEqualTo(20);
    }

    @Test
    @Order(10)
    public void listWithWrongCapacity() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> arrayList = new ArrayList<>(-2));
    }

    @Test
    @Order(11)
    public void addElements() {
        arrayList = ArrayList.of(15, 69, 58, 78);
        Object[] elements = (Object[]) getObjectByName("elementData");
        int size = (int) getObjectByName("size");

        assertThat(elements[0]).isEqualTo(15);
        assertThat(elements[1]).isEqualTo(69);
        assertThat(elements[2]).isEqualTo(58);
        assertThat(elements[3]).isEqualTo(78);
        assertThat(size).isEqualTo(4);
    }

    @Test
    @Order(12)
    public void resizeDefaultCapacity() {
        arrayList = new ArrayList<>();

        arrayList.add(15);
        arrayList.add(69);
        arrayList.add(58);
        arrayList.add(78);
        arrayList.add(6);
        arrayList.add(33);
        arrayList.add(21);

        int size = (int) getObjectByName("size");
        Object[] elements = (Object[]) getObjectByName("elementData");

        assertThat(elements[6]).isEqualTo(21);
        assertThat(size).isEqualTo(7);
    }

    @Test
    @Order(13)
    public void resizeSpecificCapacity() {
        arrayList = new ArrayList<>(4);

        arrayList.add(15);
        arrayList.add(69);
        arrayList.add(58);
        arrayList.add(78);
        arrayList.add(6);
        arrayList.add(33);
        arrayList.add(21);

        int size = (int) getObjectByName("size");
        Object[] elements = (Object[]) getObjectByName("elementData");

        assertThat(elements[6]).isEqualTo(21);
        assertThat(size).isEqualTo(7);
    }

    @Test
    @Order(14)
    public void addElementByIndex() {
        try {
            getFieldByName("elementData").set(arrayList, new Object[]{15, 69, 58, 78, 68});
            getFieldByName("size").set(arrayList, 5);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        arrayList.add(50);
        arrayList.add(2, 10);

        Object[] elements = (Object[]) getObjectByName("elementData");
        int size = (int) getObjectByName("size");

        assertThat(elements[2]).isEqualTo(10);
        assertThat(elements[5]).isEqualTo(68);
        assertThat(size).isEqualTo(7);
    }

    @Test
    @Order(15)
    public void addElementByNegativeIndex() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.add(-1, 66));

    }

    @Test
    @Order(16)
    public void addElementByIndexLargerThanListSize() {
//        arrayList = ArrayList.of(4, 6, 11, 9);
        try {
            getFieldByName("size").set(arrayList, 4);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.add(5, 88));
    }

    @Test
    @Order(17)
    public void addElementByIndexEqualToSize() {
//        arrayList = ArrayList.of(1, 2, 3, 4, 5); // size = 5
        try {
            getFieldByName("size").set(arrayList, 5);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        arrayList.add(5, 111);

        assertThat(arrayList.get(5)).isEqualTo(111);
        assertThat(arrayList.size()).isEqualTo(6);
    }

    @Test
    @Order(18)
    public void getFirstElementFromEmptyList() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.get(0));
    }

    @Test
    @Order(19)
    public void getElementByNegativeIndex() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.get(-1));
    }

    @Test
    @Order(20)
    public void getElementByIndexEqualToListSize() {
        try {
            getFieldByName("size").set(arrayList, 4);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.get(4));
    }

    @Test
    @Order(21)
    public void setElementByIndex() {
        try {
            getFieldByName("elementData").set(arrayList, new Object[]{15, 69, 58, 78});
            getFieldByName("size").set(arrayList, 4);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        arrayList.set(2, 10);

        Object[] elements = (Object[]) getObjectByName("elementData");
        int size = (int) getObjectByName("size");

        assertThat(elements[2]).isEqualTo(10);
        assertThat(elements[3]).isEqualTo(78);
        assertThat(size).isEqualTo(4);
    }

    @Test
    @Order(22)
    public void setElementByIndexEqualToSize() {
        try {
            getFieldByName("elementData").set(arrayList, new Object[]{15, 69, 58, 78});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.set(4, 10));
    }

    @Test
    @Order(23)
    public void setFirstElementOnEmptyTree() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.set(0, 34));
    }

    @Test
    @Order(24)
    public void removeElementByIndex() {
        try {
            getFieldByName("elementData").set(arrayList, new Object[]{15, 69, 58, 78, 100});
            getFieldByName("size").set(arrayList, 5);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int removedElement = arrayList.remove(2);

        Object[] elements = (Object[]) getObjectByName("elementData");
        int size = (int) getObjectByName("size");

        assertThat(elements[2]).isEqualTo(78);
        assertThat(elements[1]).isEqualTo(69);
        assertThat(size).isEqualTo(4);
        assertThat(removedElement).isEqualTo(58);
    }

    @Test
    @Order(25)
    public void removeElementByIndexEqualToSize() {
        try {
            Object[] elements = new Object[]{15, 69, 58, 78};
            getFieldByName("elementData").set(arrayList, elements);
            getFieldByName("size").set(arrayList, elements.length);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.remove(4));
    }

    @Test
    @Order(26)
    public void removeLastElementByIndex() {
        Object[] initArray = new Object[]{15, 69, 58, 78, 100};

        try {
            getFieldByName("elementData").set(arrayList, initArray);
            getFieldByName("size").set(arrayList, initArray.length);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int removedElement = arrayList.remove(4);

        Object[] elements = (Object[]) getObjectByName("elementData");
        int size = (int) getObjectByName("size");

        assertThat(elements[3]).isEqualTo(78);
        assertThat(size).isEqualTo(4);
        assertThat(removedElement).isEqualTo(100);
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.get(4));
    }

    @Test
    @Order(27)
    public void removeElementByIndexOutOfBounds() {
        try {
            getFieldByName("elementData").set(arrayList, new Object[]{15, 69, 58, 78, 100});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.remove(6));
    }

    @Test
    @Order(28)
    public void containsOnEmptyList() {
        assertThat(arrayList.contains(8)).isEqualTo(false);
    }

    @Test
    @Order(29)
    public void containsElement() {
        Object[] initArray = new Object[]{15, 69, 58, 78, 100};

        try {
            getFieldByName("elementData").set(arrayList, initArray);
            getFieldByName("size").set(arrayList, initArray.length);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        assertThat(arrayList.contains(58)).isEqualTo(true);
    }

    @Test
    @Order(30)
    void containsNotExistingWhenArrayIsNotFilled() {
        try {
            Object[] elements = new Object[100];
            elements[0] = 5;
            elements[1] = 8;
            getFieldByName("elementData").set(arrayList, elements);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        boolean result = arrayList.contains(3);

        assertThat(result).isFalse();
    }

    @Test
    @Order(31)
    public void findNotExistingElement() {
        Object[] initArray = new Object[] {15, 69, 58, 78, 100};
        try {
            getFieldByName("elementData").set(arrayList, initArray);
            getFieldByName("size").set(arrayList, initArray.length);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        assertThat(arrayList.contains(200)).isEqualTo(false);
    }

    @Test
    @Order(32)
    public void isEmptyOnEmptyList() {
        assertThat(arrayList.isEmpty()).isEqualTo(true);
    }

    @Test
    @Order(33)
    public void isEmpty() {
        try {
            getFieldByName("size").set(arrayList, 3);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        assertThat(arrayList.isEmpty()).isEqualTo(false);
    }

    @Test
    @Order(34)
    public void clearOnEmptyList() {
        assertThat(arrayList.isEmpty()).isEqualTo(true);
    }

    @Test
    @Order(35)
    public void clearChangesTheSize() {
        try {
            getFieldByName("size").set(arrayList, 100);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        arrayList.clear();

        assertThat(arrayList.size()).isEqualTo(0);
    }

    @Test
    @Order(36)
    public void clearRemovesElements() {
        try {
            getFieldByName("elementData").set(arrayList, new Object[]{4, 5, 6});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        arrayList.clear();

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.get(0));
    }

    private Object getObjectByName(String name) {

        Object element = null;

        try {
            Field field = arrayList.getClass().getDeclaredField(name);
            field.setAccessible(true);
            element = field.get(arrayList);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return element;
    }

    private Field getFieldByName(String name) {
        Field field = null;
        try {
            field = arrayList.getClass().getDeclaredField(name);
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return field;
    }
}
