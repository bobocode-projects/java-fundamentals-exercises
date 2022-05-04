package com.bobocode.cs;


import lombok.SneakyThrows;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

/**
 * A reflection-based test class for {@link LinkedList}.
 * <p>
 * PLEASE NOTE: we use Reflection API only for learning purposes. It should NOT be used for production tests.
 *
 * @author Serhii Hryhus
 * @author Taras Boychuk
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LinkedListTest {
    private static final Predicate<Field> NODE_FIELD = field ->
            field.getType().getSimpleName().equals("Node");

    private static final Predicate<Field> HEAD_NODE_FIELD = field ->
            field.getType().getSimpleName().equals("Node")
            && (field.getName().contains("head") || field.getName().contains("first"));

    private static final Predicate<Field> TAIL_NODE_FIELD = field ->
            field.getType().getSimpleName().equals("Node")
            && (field.getName().equals("tail") || field.getName().contains("last"));

    private static final Predicate<Field> SIZE_FIELD = field ->
            field.getName().equals("size");

    private static final Predicate<Field> ELEMENT_FIELD = field ->
            field.getGenericType().getTypeName().equals("T")
            && (field.getName().contains("elem") || field.getName().contains("value") || field.getName().contains("item"));

    private LinkedList<Integer> intList = new LinkedList<>();

    @Test
    @Order(1)
    void properNodeName() {
        Class<?> innerClass = getInnerClass();

        String name = innerClass.getSimpleName();

        assertThat(name).isEqualTo("Node");
    }

    @Test
    @Order(2)
    void properNodeFields() {
        Class<?> innerClass = getInnerClass();

        boolean hasElementField = Arrays.stream(innerClass.getDeclaredFields())
                .anyMatch(ELEMENT_FIELD);

        boolean hasNodeField = Arrays.stream(innerClass.getDeclaredFields())
                .anyMatch(NODE_FIELD);

        assertThat(hasElementField).isTrue();
        assertThat(hasNodeField).isTrue();
    }

    private Class<?> getInnerClass() {
        return Arrays.stream(intList.getClass().getDeclaredClasses())
                .filter(aClass -> Modifier.isStatic(aClass.getModifiers()))
                .findAny()
                .orElseThrow();
    }

    @Test
    @Order(3)
    void addIntoEmptyList() {
        intList.add(41);

        int element = getInternalElement(0);

        assertThat(element).isEqualTo(41);
    }

    @Test
    @Order(4)
    void addIntoEmptyListChangesSize() {
        intList.add(41);

        int size = getInternalSize();

        assertThat(size).isEqualTo(1);
    }

    @Test
    @Order(5)
    void add() {
        intList.add(41);
        intList.add(30);
        intList.add(75);

        int firstElement = getInternalElement(0);
        int secondElement = getInternalElement(1);
        int thirdElement = getInternalElement(2);

        assertThat(firstElement).isEqualTo(41);
        assertThat(secondElement).isEqualTo(30);
        assertThat(thirdElement).isEqualTo(75);
    }

    @Test
    @Order(6)
    void addChangesSize() {
        intList.add(41);
        intList.add(30);
        intList.add(75);

        int size = getInternalSize();

        assertThat(size).isEqualTo(3);
    }

    @Test
    @Order(7)
    void addByIndex() {
        addInternalElements(43, 5, 6, 8);

        int newElementIdx = 2;
        intList.add(newElementIdx, 66);

        int elementByNewElementIndex = getInternalElement(newElementIdx);
        int size = getInternalSize();

        assertThat(elementByNewElementIndex).isEqualTo(66);
        assertThat(getInternalElement(0)).isEqualTo(43);
        assertThat(getInternalElement(1)).isEqualTo(5);
        assertThat(getInternalElement(3)).isEqualTo(6);
        assertThat(getInternalElement(4)).isEqualTo(8);
        assertThat(size).isEqualTo(5);
    }

    @Test
    @Order(8)
    void addByZeroIndexWhenListIsEmpty() {
        intList.add(0, 45);

        int element = getInternalElement(0);
        int size = getInternalSize();

        assertThat(element).isEqualTo(45);
        assertThat(size).isEqualTo(1);
    }

    @Test
    @Order(9)
    void addByIndexToTheEndOfList() {
        addInternalElements(98, 64, 23, 1, 3, 4);

        int newElementIndex = getInternalSize();
        intList.add(newElementIndex, 44);

        assertThat(getInternalElement(newElementIndex)).isEqualTo(44);
        assertThat(getInternalSize()).isEqualTo(7);
    }

    @Test
    @Order(10)
    void addToHeadWhenListIsNotEmpty() {
        addInternalElements(4, 6, 8, 9, 0, 2);

        intList.add(0, 53);

        int firstElement = getInternalElement(0);
        int secondElement = getInternalElement(1);
        int size = getInternalSize();

        assertThat(firstElement).isEqualTo(53);
        assertThat(secondElement).isEqualTo(4);
        assertThat(size).isEqualTo(7);
    }

    @Test
    @Order(11)
    void addThrowsExceptionWhenIndexIsNegative() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> intList.add(-1, 66));
    }

    @Test
    @Order(12)
    void addThrowsExceptionWhenIndexLargerThanSize() {
        addInternalElements(4, 6, 11, 9);

        int newElementIdx = 5;

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> intList.add(newElementIdx, 88));
    }

    @Test
    @Order(13)
    void addWhenIndexEqualToSize() {
        addInternalElements(1, 2, 3, 4, 5); // size = 5

        intList.add(5, 111);

        int element = getInternalElement(5);
        int size = getInternalSize();

        assertThat(element).isEqualTo(111);
        assertThat(size).isEqualTo(6);
    }

    @Test
    @Order(14)
    void of() {
        intList = LinkedList.of(43, 233, 54);

        assertThat(getInternalElement(0)).isEqualTo(43);
        assertThat(getInternalElement(1)).isEqualTo(233);
        assertThat(getInternalElement(2)).isEqualTo(54);
    }

    @Test
    @Order(15)
    void ofChangeSize() {
        intList = LinkedList.of(43, 233, 54);

        int size = getInternalSize();

        assertThat(size).isEqualTo(3);
    }

    @Test
    @Order(16)
    void setByIndex() {
        addInternalElements(34, 78, 9, 8);

        int index = 2; //element = 78
        intList.set(index, 99);

        int elementOnNewElementIndex = getInternalElement(index);
        int nextElementToNewElementIndex = getInternalElement(3);
        int internalSize = getInternalSize();

        assertThat(elementOnNewElementIndex).isEqualTo(99);
        assertThat(nextElementToNewElementIndex).isEqualTo(8);
        assertThat(getInternalElement(0)).isEqualTo(34);
        assertThat(getInternalElement(1)).isEqualTo(78);
        assertThat(internalSize).isEqualTo(4);
    }

    @Test
    @Order(17)
    void setFirstElementWhenListIsEmpty() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> intList.set(0, 34));
    }

    @Test
    @Order(18)
    void setByIndexEqualToSize() {
        addInternalElements(2, 3, 4); // size = 3

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> intList.set(3, 222));
    }

    @Test
    @Order(19)
    void get() {
        addInternalElements(25, 87, 45);

        int firstElement = intList.get(0);
        int secondElement = intList.get(1);
        int thirdElement = intList.get(2);

        assertThat(firstElement).isEqualTo(25);
        assertThat(secondElement).isEqualTo(87);
        assertThat(thirdElement).isEqualTo(45);

    }

    @Test
    @Order(20)
    void getByZeroIndexWhenListHasSingleElement() {
        addInternalElements(25);

        int element = intList.get(0);

        assertThat(element).isEqualTo(25);
    }

    @Test
    @Order(21)
    void getByZeroIndexWhenListIsEmpty() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> intList.get(0));
    }

    @Test
    @Order(22)
    void getWhenIndexIsNegative() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> intList.get(-1));
    }

    @Test
    @Order(23)
    void getWhenIndexIsEqualToListSize() {
        addInternalElements(33, 46, 25, 87, 45);

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> intList.get(5));
    }

    @Test
    @Order(24)
    void getFirst() {
        addInternalElements(31, 32, 5);

        int firstElement = intList.getFirst();

        assertThat(firstElement).isEqualTo(31);
    }

    @Test
    @Order(25)
    void getLast() {
        addInternalElements(41, 6, 42);

        int lastElement = intList.getLast();

        assertThat(lastElement).isEqualTo(42);
    }

    @Test
    @Order(26)
    void getFirstWhenListIsEmpty() {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> intList.getFirst());
    }

    @Test
    @Order(27)
    void getLastWhenListIsEmpty() {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> intList.getLast());
    }


    @Test
    @Order(28)
    void remove() {
        addInternalElements(1, 2, 3, 4, 5);

        int elementIndex = 2;
        int deletedElement = intList.remove(elementIndex); // element = 3

        int replacedElement = getInternalElement(elementIndex);

        assertThat(deletedElement).isEqualTo(3);
        assertThat(replacedElement).isEqualTo(4);
    }

    @Test
    @Order(29)
    void removeChangesSize() {
        addInternalElements(1, 2, 3, 4, 5);

        int elementIndex = 2;
        int deletedElement = intList.remove(elementIndex); // element = 3

        int size = getInternalSize();

        assertThat(deletedElement).isEqualTo(3);
        assertThat(size).isEqualTo(4);
    }

    @Test
    @Order(30)
    void removeFirst() {
        addInternalElements(4, 6, 8, 9);

        int deletedElement = intList.remove(0);

        int replacedElement = getInternalElement(0);
        int size = getInternalSize();

        assertThat(deletedElement).isEqualTo(4);
        assertThat(replacedElement).isEqualTo(6);
        assertThat(size).isEqualTo(3);
    }

    @Test
    @Order(31)
    void removeLast() {
        addInternalElements(4, 6, 8, 9);

        int deletedElement = intList.remove(getInternalSize() - 1);

        int newLastElement = getInternalElement(getInternalSize() - 1);
        int size = getInternalSize();

        assertThat(deletedElement).isEqualTo(9);
        assertThat(newLastElement).isEqualTo(8);
        assertThat(size).isEqualTo(3);
    }

    @Test
    @Order(32)
    void removeWhenListIsEmpty() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> intList.remove(234));
    }

    @Test
    @Order(33)
    void removeByZeroIndexWhenListIsEmpty() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> intList.remove(0));
    }

    @Test
    @Order(34)
    void size() {
        setInternalSize(5);

        int sizeFromMethod = intList.size();

        assertThat(sizeFromMethod).isEqualTo(5);
    }

    @Test
    @Order(35)
    void sizeWhenListIsEmpty() {
        int size = getInternalSize();

        assertThat(size).isEqualTo(0);
    }

    @Test
    @Order(36)
    void contains() {
        addInternalElements(45, 6, 3, 6);

        boolean containsExistingElement = intList.contains(3);
        boolean containsNotExistingElement = intList.contains(54);

        assertThat(containsExistingElement).isTrue();
        assertThat(containsNotExistingElement).isFalse();
    }

    @Test
    @Order(37)
    void containsWhenListIsEmpty() {
        boolean contains = intList.contains(34);

        assertThat(contains).isFalse();
    }

    @Test
    @Order(38)
    void isEmpty() {
        addInternalElements(34, 5, 6);

        boolean empty = intList.isEmpty();

        assertThat(empty).isFalse();
    }

    @Test
    @Order(39)
    void isEmptyWhenListIsEmpty() {
        boolean empty = intList.isEmpty();

        assertThat(empty).isTrue();
    }

    @Test
    @Order(40)
    void clearWhenListIsEmpty() {
        intList.clear();

        int size = getInternalSize();

        assertThat(size).isEqualTo(0);
    }

    @Test
    @Order(41)
    void clearChangesSize() {
        addInternalElements(4, 5, 6);

        intList.clear();

        int size = getInternalSize();

        assertThat(size).isEqualTo(0);
    }

    @Test
    @Order(42)
    void clearRemovesAllElements() {
        addInternalElements(4, 5, 6);

        intList.clear();

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> getInternalElement(0));
    }

    @SneakyThrows
    private int getInternalElement(int index) {

        Object head = getAccessibleFieldByPredicate(intList, HEAD_NODE_FIELD).get(intList);

        for (int j = 0; j < index; j++) {
            head = getAccessibleFieldByPredicate(head, NODE_FIELD).get(head);
        }
        return (int) getAccessibleFieldByPredicate(head, ELEMENT_FIELD).get(head);
    }

    @SneakyThrows
    private int getInternalSize() {
        return (int) getAccessibleFieldByPredicate(intList, SIZE_FIELD).get(intList);
    }

    @SneakyThrows
    private void addInternalElements(int... elements) {
        Field nodeField = getInternalHeadField();
        Field tailNode = getInternalTailField();
        Class<?> nodeType = nodeField.getType();

        Object previousObject = intList;
        Object nodeObject = null;

        for (int element : elements) {
            nodeObject = createNodeObjectWithInternalElement(nodeType, element);
            nodeField.set(previousObject, nodeObject);
            nodeField = getAccessibleFieldByPredicate(nodeObject, NODE_FIELD);
            previousObject = nodeObject;
        }

        tailNode.set(intList, nodeObject);
        setInternalSize(elements.length);
    }

    private Field getInternalHeadField() {
        return getAccessibleFieldByPredicate(intList, HEAD_NODE_FIELD);
    }

    private Field getInternalTailField() {
        return getAccessibleFieldByPredicate(intList, TAIL_NODE_FIELD);
    }

    @SneakyThrows
    private void setInternalSize(int size) {
        Field sizeField = getAccessibleFieldByPredicate(intList, SIZE_FIELD);
        sizeField.setInt(intList, size);
    }

    @SneakyThrows
    private Object createNodeObjectWithInternalElement(Class<?> nodeClass, int element) {
        Object nodeObject;
        Constructor<?>[] declaredConstructors = nodeClass.getDeclaredConstructors();
        Constructor<?> constructor;

        constructor = declaredConstructors[0];
        constructor.setAccessible(true);
        if (constructor.getParameterTypes().length == 1) {
            nodeObject = constructor.newInstance(element);
        } else {
            nodeObject = createNodeByConstructorWithoutParameters(element, constructor);
        }
        return nodeObject;
    }

    @SneakyThrows
    private Object createNodeByConstructorWithoutParameters(int element, Constructor<?> constructor) {
        Object nodeObject;
        nodeObject = constructor.newInstance();
        Field nodeElement = getAccessibleFieldByPredicate(nodeObject, ELEMENT_FIELD);
        nodeElement.set(nodeObject, element);
        return nodeObject;
    }

    private Field getAccessibleFieldByPredicate(Object object, Predicate<Field> predicate) {
        Field field = Arrays.stream(object.getClass().getDeclaredFields())
                .filter(predicate)
                .findAny()
                .orElseThrow();
        field.setAccessible(true);
        return field;
    }
}