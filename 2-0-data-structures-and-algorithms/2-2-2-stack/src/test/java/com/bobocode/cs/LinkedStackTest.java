package com.bobocode.cs;

import com.bobocode.cs.exception.EmptyStackException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * A reflection-based test class for {@link LinkedStack}.
 * <p>
 * PLEASE NOTE: we use Reflection API only for learning purposes. It should NOT be used for production tests.
 *
 * @author Ivan Virchenko
 * @author Taras Boychuk
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LinkedStackTest {
    private static final String PROPER_CLASSNAME = "Node";

    private static final Predicate<Field> NODE_FIELD_PREDICATE = field ->
            field.getType().getSimpleName().equals(PROPER_CLASSNAME)
            && field.getName().toLowerCase().contains("head")
            || field.getName().toLowerCase().contains("first");

    private static final Predicate<Field> SIZE_FIELD_PREDICATE = field ->
            field.getName().toLowerCase().contains("size");

    private static final Predicate<Field> NODE_ELEMENT_FIELD_PREDICATE = field ->
            field.getName().toLowerCase().contains("element")
            || field.getName().toLowerCase().contains("value")
            || field.getName().toLowerCase().contains("item");

    private static final Predicate<Field> NODE_NEXT_FIELD_PREDICATE = field ->
            field.getType().getSimpleName().equals(PROPER_CLASSNAME)
            && field.getName().toLowerCase().contains("next");

    private Stack<Integer> intStack = new LinkedStack<>();

    @Test
    @Order(1)
    @DisplayName("Inner class Node is created")
    void checkProperInnerClassName() {
        String name = getInnerClass().getSimpleName();
        assertThat(name).isEqualTo(PROPER_CLASSNAME);
    }

    @Test
    @Order(2)
    @DisplayName("Class Node is a generic class")
    void noteIsAGenericClass() {
        var nodeTypeParams = getInnerClass().getTypeParameters();

        assertThat(nodeTypeParams).hasSize(1);
    }

    @Test
    @Order(3)
    @DisplayName("LinkedStack class has a field that stores a reference to the first(head) element")
    void checkProperHeadFieldName() {
        Field[] fields = LinkedStack.class.getDeclaredFields();

        boolean hasNodeField = Arrays.stream(fields)
                .anyMatch(NODE_FIELD_PREDICATE);

        assertThat(hasNodeField).isTrue();
    }

    @Test
    @Order(4)
    @DisplayName("LinkedStack class has a field to store stack size")
    void checkProperSizeFieldName() {
        Field[] fields = LinkedStack.class.getDeclaredFields();

        boolean hasSizeField = Arrays.stream(fields)
                .anyMatch(SIZE_FIELD_PREDICATE);

        assertThat(hasSizeField).isTrue();
    }

    @Test
    @Order(5)
    @DisplayName("Node class has a field to store a generic element")
    void checkProperElementField() {
        var fields = getInnerClass().getDeclaredFields();

        var elementField = Arrays.stream(fields)
                .filter(NODE_ELEMENT_FIELD_PREDICATE)
                .findAny()
                .orElseThrow();
        var nodeTypeParameter = getInnerClass().getTypeParameters()[0];


        assertThat(elementField.getGenericType().getTypeName()).isEqualTo(nodeTypeParameter.getTypeName());
    }

    @Test
    @Order(6)
    @DisplayName("Node class has a field to store a reference to the next node")
    void checkProperNextField() {
        Field[] fields = getInnerClass().getDeclaredFields();

        boolean hasNext = Arrays.stream(fields)
                .anyMatch(NODE_NEXT_FIELD_PREDICATE);

        assertThat(hasNext).isTrue();
    }

    @Test
    @Order(7)
    @DisplayName("Method of() creates a new LinkedStack of given elements")
    void of() {
        intStack = LinkedStack.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        for (int i = 1; i <= 10; i++) {
            assertThat(contains(i)).isTrue();
        }
    }

    @Test
    @Order(8)
    @DisplayName("Method push() adds new element on top of the stack")
    void push() {
        intStack.push(55);

        assertThat(contains(55)).isTrue();
    }

    @Test
    @Order(9)
    @DisplayName("Method push() adds new element on top of the stack")
    void pushAddsElementWhenStackIsEmpty() {
        intStack.push(243);

        assertThat(contains(243)).isTrue();
    }

    @Test
    @Order(10)
    @DisplayName("Method push() adds new element on top of the stack when it's empty")
    void pushAddsElementToHeadWhenStackIsEmpty() {
        intStack.push(10);

        Object head = getHeadObject();
        int innerElement = getNodeElementInt(head);

        assertThat(innerElement).isEqualTo(10);
    }

    @Test
    @Order(11)
    @DisplayName("Method push() adds new element on top of the stack when it's not empty")
    void pushAddsElementToHeadWhenStackIsNotEmpty() {
        intStack.push(10);
        intStack.push(15);
        intStack.push(20);

        Object head = getHeadObject();
        int innerElement = getNodeElementInt(head);

        assertThat(innerElement).isEqualTo(20);
    }

    @Test
    @Order(12)
    @DisplayName("Method push() links new element with the previous top (head) element")
    void pushPutsHeadToNextOfNewHead() {
        fillTestStack(10, 15, 20);

        assertThat(getNodeElementInt(getHeadObject())).isEqualTo(20);

        intStack.push(30);

        Object head = getHeadObject();
        Object next = getNodeNextObject(head);
        int nextElement = getNodeElementInt(next);

        assertThat(nextElement).isEqualTo(20);
    }

    @Test
    @Order(13)
    @DisplayName("Method push() throws exception when element is null")
    void pushThrowsExceptionWhenElementIsNull() {
        assertThatNullPointerException().isThrownBy(() -> intStack.push(null));
    }

    @Test
    @Order(14)
    @DisplayName("Method pop() throws exception when stack is empty")
    void popElementWhenStackIsEmpty() {
        assertThrows(EmptyStackException.class, () -> intStack.pop());
    }

    @Test
    @Order(15)
    @DisplayName("Method pop() retrieves top element from the stack (LIFO)")
    void pop() {
        fillTestStack(55, 17, 66, 234);

        int lastElement = intStack.pop();

        assertThat(lastElement).isEqualTo(234);
    }

    @Test
    @Order(16)
    @DisplayName("Method pop() assigns next element to be a head")
    void popResetsHeadFromNextOfOldHead() {
        fillTestStack(10, 15, 20);
        Object head = getHeadObject();

        assertThat(getNodeElementInt(head)).isEqualTo(20);

        intStack.pop();
        head = getHeadObject();

        assertThat(getNodeElementInt(head)).isEqualTo(15);
    }

    @Test
    @Order(17)
    @DisplayName("Method size() returns 0 when stack is empty")
    void sizeWhenStackIsEmpty() {
        int actualSize = getInnerSize();

        assertThat(actualSize).isEqualTo(0);
    }

    @Test
    @Order(18)
    @DisplayName("Method size() returns number of element in the stack")
    void size() {
        fillTestStack(1, 5, 7);

        assertThat(intStack.size()).isEqualTo(3);
    }

    @Test
    @Order(19)
    @DisplayName("Method size() returns correct value when stack was created via method of()")
    void sizeIncreasesWhenUseOfMethod() {
        intStack = LinkedStack.of(1, 2, 3, 4, 5, 6, 7, 8);

        assertThat(intStack.size()).isEqualTo(8);
    }

    @Test
    @Order(20)
    @DisplayName("Method size() correct value when elements were added via push()")
    void sizeIncreasesWhenPush() {
        intStack.push(1);
        intStack.push(2);
        intStack.push(3);

        assertThat(intStack.size()).isEqualTo(3);
    }

    @Test
    @Order(21)
    @DisplayName("Method size() correct value when elements were removed via pop()")
    void sizeDecreasesWhenPop() {
        fillTestStack(1, 2, 3, 4, 5);
        intStack.pop();

        assertThat(intStack.size()).isEqualTo(4);
    }

    @Test
    @Order(22)
    @DisplayName("Method isEmpty() returns true when stack contains elements")
    void isEmpty() {
        fillTestStack(87, 53, 66);

        boolean stackEmpty = intStack.isEmpty();

        assertThat(stackEmpty).isEqualTo(false);
    }

    @Test
    @Order(23)
    @DisplayName("Method isEmpty() returns false when stack contains no elements")
    void isEmptyWhenStackIsEmpty() {
        boolean stackEmpty = intStack.isEmpty();
        assertThat(stackEmpty).isEqualTo(true);
    }

    private Class<?> getInnerClass() {
        return Arrays.stream(LinkedStack.class.getDeclaredClasses())
                .filter(Class::isMemberClass)
                .findAny().orElseThrow();
    }

    private Field getHeadField() {
        Field headField = Arrays.stream(LinkedStack.class.getDeclaredFields())
                .filter(NODE_FIELD_PREDICATE)
                .findAny()
                .orElseThrow();
        headField.setAccessible(true);
        return headField;
    }

    private Field getNodeElementField(Object node) {
        Field fieldElement = Arrays.stream(node.getClass().getDeclaredFields())
                .filter(NODE_ELEMENT_FIELD_PREDICATE)
                .findAny()
                .orElseThrow();
        fieldElement.setAccessible(true);
        return fieldElement;
    }

    private Field getNodeNextField(Object node) {
        Field field = Arrays.stream(node.getClass().getDeclaredFields())
                .filter(NODE_NEXT_FIELD_PREDICATE)
                .findAny()
                .orElseThrow();
        field.setAccessible(true);
        return field;
    }

    @SneakyThrows
    private Object getHeadObject() {
        return getHeadField().get(intStack);
    }

    @SneakyThrows
    private int getNodeElementInt(Object node) {
        return (int) getNodeElementField(node).get(node);
    }

    @SneakyThrows
    private Object getNodeNextObject(Object node) {
        return getNodeNextField(node).get(node);
    }

    private boolean contains(int element) {
        Object head = getHeadObject();
        if (head == null) {
            return false;
        }

        if (getNodeNextObject(head) != null) {
            return checkNext(head, element);
        } else {
            return getNodeElementInt(head) == element;
        }
    }

    private boolean checkNext(Object node, int element) {
        if (getNodeElementInt(node) == element) {
            return true;
        } else {
            return checkNext(getNodeNextObject(node), element);
        }
    }

    @SneakyThrows
    private Object newNode(int element) {
        Constructor<?> constructor = getInnerClass().getDeclaredConstructors()[0];
        constructor.setAccessible(true);

        if (constructor.getParameters().length == 1) {
            return constructor.newInstance(element);
        } else if (constructor.getParameters().length == 2) {
            return constructor.newInstance(element, null);
        } else {
            Object node = constructor.newInstance();
            getNodeElementField(node).set(node, element);
            return node;
        }
    }

    private void fillTestStack(int... elements) {
        for (int element : elements) {
            addToStack(element);
        }
        setInnerSize(elements.length);
    }

    @SneakyThrows
    private void addToStack(int element) {
        Object newNode = newNode(element);
        if (getHeadObject() != null) {
            getNodeNextField(newNode).set(newNode, getHeadObject());
        }
        getHeadField().set(intStack, newNode);
    }

    private Field getInnerSizeField() {
        Field size = Arrays.stream(LinkedStack.class.getDeclaredFields())
                .filter(SIZE_FIELD_PREDICATE)
                .findAny()
                .orElseThrow();
        size.setAccessible(true);
        return size;
    }

    @SneakyThrows
    private void setInnerSize(int size) {
        getInnerSizeField().set(intStack, size);
    }

    @SneakyThrows
    private int getInnerSize() {
        return (int) getInnerSizeField().get(intStack);
    }
}
