package com.bobocode.cs;

import com.bobocode.cs.exception.EmptyStackException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StackTest {

    private static final String PROPER_CLASSNAME = "Node";

    private static final Predicate<Field> NODE_FIELD_PREDICATE = field -> field.getType().getSimpleName().equals(PROPER_CLASSNAME)
            && field.getName().toLowerCase().contains("head") || field.getName().toLowerCase().contains("first");
    private static final Predicate<Field> SIZE_FIELD_PREDICATE = field -> field.getName().toLowerCase().contains("size");
    private static final Predicate<Field> NODE_ELEMENT_FIELD = field -> field.getName().toLowerCase().contains("element")
            || field.getName().toLowerCase().contains("value")
            || field.getName().toLowerCase().contains("item");
    private static final Predicate<Field> NODE_NEXT_FIELD = field -> field.getType().getSimpleName().equals(PROPER_CLASSNAME)
            && field.getName().toLowerCase().contains("next");

    private Stack<Integer> intStack = new LinkedStack<>();

    @Test
    @Order(1)
    void checkProperInnerClassName() {
        String name = getInnerClass().getSimpleName();
        assertThat(name).isEqualTo(PROPER_CLASSNAME);
    }

    @Test
    @Order(2)
    void checkProperStackFieldNames() {
        Field[] fields = LinkedStack.class.getDeclaredFields();

        boolean hasSizeField = Arrays.stream(fields)
                .anyMatch(SIZE_FIELD_PREDICATE);
        boolean hasNodeField = Arrays.stream(fields)
                .anyMatch(NODE_FIELD_PREDICATE);

        assertThat(hasNodeField).isTrue();
        assertThat(hasSizeField).isTrue();
    }

    @Test
    @Order(3)
    void checkProperNodeFields() {
        Field[] fields = getInnerClass().getDeclaredFields();

        boolean hasElement = Arrays.stream(fields)
                .anyMatch(NODE_ELEMENT_FIELD);
        boolean hasNext = Arrays.stream(fields)
                .anyMatch(NODE_NEXT_FIELD);

        assertThat(hasElement).isTrue();
        assertThat(hasNext).isTrue();
    }

    @Test
    @Order(4)
    void of() {
        intStack = LinkedStack.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        for (int i = 1; i <= 10; i++) {
            assertThat(contains(i)).isTrue();
        }
    }

    @Test
    @Order(5)
    void push() {
        intStack.push(55);

        assertThat(contains(55)).isTrue();
    }

    @Test
    @Order(6)
    void pushAddsElementWhenStackIsEmpty() {
        intStack.push(243);

        assertThat(contains(243)).isTrue();
    }

    @Test
    @Order(7)
    void pushAddsElementToHeadWhenStackIsEmpty() {
        intStack.push(10);

        Object head = getHeadObject();
        int innerElement = getNodeElementInt(head);

        assertThat(innerElement).isEqualTo(10);
    }

    @Test
    @Order(8)
    void pushAddsElementToHeadWhenStackIsNotEmpty() {
        intStack.push(10);
        intStack.push(15);
        intStack.push(20);

        Object head = getHeadObject();
        int innerElement = getNodeElementInt(head);

        assertThat(innerElement).isEqualTo(20);
    }

    @Test
    @Order(9)
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
    @Order(10)
    void pushThrowsExceptionWhenElementIsNull() {
        assertThatNullPointerException().isThrownBy(() -> intStack.push(null));
    }

    @Test
    @Order(11)
    void popElementWhenStackIsEmpty() {
        assertThrows(EmptyStackException.class, () -> intStack.pop());
    }

    @Test
    @Order(12)
    void pop() {
        fillTestStack(55, 17, 66, 234);

        int lastElement = intStack.pop();

        assertThat(lastElement).isEqualTo(234);
    }

    @Test
    @Order(13)
    void popResetsHeadFromNextOfOldHead() {
        fillTestStack(10, 15, 20);
        Object head = getHeadObject();

        assertThat(getNodeElementInt(head)).isEqualTo(20);

        intStack.pop();
        head = getHeadObject();

        assertThat(getNodeElementInt(head)).isEqualTo(15);
    }

    @Test
    @Order(14)
    void sizeWhenStackIsEmpty() {
        int actualSize = getInnerSize();

        assertThat(actualSize).isEqualTo(0);
    }

    @Test
    @Order(15)
    void size() {
        fillTestStack(1, 5, 7);

        assertThat(intStack.size()).isEqualTo(3);
    }

    @Test
    @Order(16)
    void sizeIncreasesWhenUseOfMethod() {
        intStack = LinkedStack.of(1, 2, 3, 4, 5, 6, 7, 8);

        assertThat(intStack.size()).isEqualTo(8);
    }

    @Test
    @Order(17)
    void sizeIncreasesWhenPush() {
        intStack.push(1);
        intStack.push(2);
        intStack.push(3);

        assertThat(intStack.size()).isEqualTo(3);
    }

    @Test
    @Order(18)
    void sizeDecreasesWhenPop() {
        fillTestStack(1, 2, 3, 4, 5);
        intStack.pop();

        assertThat(intStack.size()).isEqualTo(4);
    }

    @Test
    @Order(19)
    void isEmpty() {
        fillTestStack(87, 53, 66);

        boolean stackEmpty = intStack.isEmpty();

        assertThat(stackEmpty).isEqualTo(false);
    }

    @Test
    @Order(20)
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
                .filter(NODE_ELEMENT_FIELD)
                .findAny()
                .orElseThrow();
        fieldElement.setAccessible(true);
        return fieldElement;
    }

    private Field getNodeNextField(Object node) {
        Field field = Arrays.stream(node.getClass().getDeclaredFields())
                .filter(NODE_NEXT_FIELD)
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
