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
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A reflection-based test class for {@link LinkedQueue}.
 * <p>
 * PLEASE NOTE: we use Reflection API only for learning purposes. It should NOT be used for production tests.
 *
 * @author Victor Kuzma
 * @author Taras Boychuk
 * @author Ivan Virchenko
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LinkedQueueTest {
    private static final String NODE_NAME = "Node";
    private static final String SIZE_NAME = "size";

    private static final Predicate<Field> NODE_FIELD = field ->
            field.getType().getSimpleName().equals(NODE_NAME) && (field.getName().contains("next"));

    private static final Predicate<Field> ELEMENT_FIELD = field ->
            field.getGenericType().getTypeName().equals("T")
            && (field.getName().contains("elem") || field.getName().contains("value") || field.getName().contains("item"));

    private static final Predicate<Field> NEXT_FIELD = field ->
            field.getGenericType().getTypeName().endsWith("Node<T>") && (field.getName().contains("next"));

    private static final Predicate<Field> SIZE_FIELD = field ->
            field.getType().getSimpleName().equals("int") && (field.getName().equals(SIZE_NAME));

    private static final Predicate<Field> HEAD_FIELD = field ->
            field.getType().getSimpleName().equals(NODE_NAME)
            && (field.getName().contains("head") || field.getName().contains("first"));

    private static final Predicate<Field> TAIL_FIELD = field ->
            field.getType().getSimpleName().equals(NODE_NAME)
            && (field.getName().contains("tail") || field.getName().contains("last"));

    private Queue<Integer> integerQueue = new LinkedQueue<>();

    @Test
    @Order(1)
    void createNodeClass() {
        Class<?> innerClass = getInnerStaticNodeClass();
        String name = innerClass.getSimpleName();

        assertThat(name).isEqualTo(NODE_NAME);
    }

    @Test
    @Order(2)
    void checkFieldsNameInNodeClass() {
        Class<?> innerClass = getInnerStaticNodeClass();
        boolean hasElementField = hasField(innerClass, ELEMENT_FIELD);
        boolean hasNodeField = hasField(innerClass, NODE_FIELD);

        assertThat(hasElementField).isTrue();
        assertThat(hasNodeField).isTrue();
    }

    @Test
    @Order(3)
    void checkFieldsInQueueCLass() {
        Class<?> baseClass = this.integerQueue.getClass();
        boolean hasHeadField = hasField(baseClass, HEAD_FIELD);
        boolean hasSizeFiled = hasField(baseClass, SIZE_FIELD);
        boolean hasTailFiled = hasField(baseClass, TAIL_FIELD);

        assertThat(hasHeadField).isTrue();
        assertThat(hasSizeFiled).isTrue();
        assertThat(hasTailFiled).isTrue();
    }

    @Test
    @Order(4)
    void addFillsQueueWhenItIsEmpty() {
        integerQueue.add(1);
        integerQueue.add(228);
        int size = getInternalSize();
        boolean isEmpty = isEmptyQueue();
        Integer firstElement = (Integer) pollElementFromQueue();
        Integer secondElement = (Integer) pollElementFromQueue();

        assertThat(size).isEqualTo(2);
        assertThat(isEmpty).isEqualTo(false);
        assertThat(firstElement).isEqualTo(1);
        assertThat(secondElement).isEqualTo(228);
    }

    @Test
    @Order(5)
    void addFillsQueueWhenItIsNotEmpty() {
        addIntElementToQueue(12);
        addIntElementToQueue(13);
        integerQueue.add(111);
        int size = getInternalSize();
        boolean isEmpty = isEmptyQueue();
        Integer firstElement = (Integer) pollElementFromQueue();
        Integer secondElement = (Integer) pollElementFromQueue();
        Integer tailValue = (Integer) getNodeValue(TAIL_FIELD);

        assertThat(size).isEqualTo(3);
        assertThat(isEmpty).isEqualTo(false);
        assertThat(firstElement).isEqualTo(12);
        assertThat(secondElement).isEqualTo(13);
        assertThat(tailValue).isEqualTo(111);
    }

    @Test
    @Order(6)
    void addIncreasesQueueSize() {
        integerQueue.add(1);
        integerQueue.add(2);
        int size = getInternalSize();

        assertThat(size).isEqualTo(2);
    }

    @Test
    @Order(7)
    void pollReturnsNullWhenQueueIsEmpty() {
        Integer firstElement = this.integerQueue.poll();

        assertThat(firstElement).isEqualTo(null);
    }

    @Test
    @Order(8)
    void pollDeletesElementFromHead() {
        addIntElementToQueue(11);
        addIntElementToQueue(111);
        Integer firstElement = this.integerQueue.poll();
        Integer secondElement = this.integerQueue.poll();
        boolean isEmpty = isEmptyQueue();

        assertThat(isEmpty).isEqualTo(true);
        assertThat(firstElement).isEqualTo(11);
        assertThat(secondElement).isEqualTo(111);
    }

    @Test
    @Order(9)
    void pollDecreasesQueueSize() {
        addIntElementToQueue(11);
        addIntElementToQueue(111);
        this.integerQueue.poll();
        int size = getInternalSize();

        assertThat(size).isEqualTo(1);
    }

    @Test
    @Order(10)
    void pollMakesSizeZeroWhenQueueHasSingleElement() {
        addIntElementToQueue(12);
        Integer element = this.integerQueue.poll();
        int size = getInternalSize();

        assertThat(size).isEqualTo(0);
        assertThat(element).isEqualTo(12);
    }

    @Test
    @SneakyThrows
    @Order(11)
    void pollMakesQueueEmptyWhenQueueHasSingleElement() {
        addIntElementToQueue(1);
        this.integerQueue.poll();
        boolean isEmpty = isEmptyQueue();

        Object tail = getAccessibleFieldByPredicate(integerQueue, TAIL_FIELD).get(integerQueue);
        Object head = getAccessibleFieldByPredicate(integerQueue, HEAD_FIELD).get(integerQueue);

        assertThat(isEmpty).isEqualTo(true);
        assertThat(tail).isNull();
        assertThat(head).isNull();
    }

    @Test
    @Order(12)
    void sizeReturnsZeroWhenQueueIsEmpty() {
        int size = this.integerQueue.size();

        assertThat(size).isEqualTo(0);
    }

    @Test
    @Order(13)
    void size() {
        addIntElementToQueue(1);
        int size = this.integerQueue.size();

        assertThat(size).isEqualTo(1);
    }

    @Test
    @Order(14)
    void isEmptyReturnsTrueWhenQueueIsEmpty() {
        boolean isEmpty = this.integerQueue.isEmpty();

        assertThat(isEmpty).isEqualTo(true);
    }

    @Test
    @Order(15)
    void isEmpty() {
        addIntElementToQueue(1);
        boolean isEmpty = integerQueue.isEmpty();

        assertThat(isEmpty).isEqualTo(false);
    }


    private Class<?> getInnerStaticNodeClass() {
        return Arrays.stream(integerQueue.getClass().getDeclaredClasses())
                .filter(aClass -> Modifier.isStatic(aClass.getModifiers()))
                .findAny()
                .orElseThrow();
    }

    private boolean hasField(Class<?> classToSearch, Predicate targetField) {
        return Arrays.stream(classToSearch.getDeclaredFields())
                .anyMatch(targetField);
    }

    @SneakyThrows
    private int getInternalSize() {
        return (int) getAccessibleFieldByPredicate(this.integerQueue, SIZE_FIELD)
                .get(this.integerQueue);
    }


    @SneakyThrows
    private Object pollElementFromQueue() {
        Object element;
        Object nextElement;
        Object tail;
        Object head = getAccessibleFieldByPredicate(this.integerQueue,
                HEAD_FIELD)
                .get(this.integerQueue);

        Integer size = (Integer) getAccessibleFieldByPredicate(this.integerQueue,
                SIZE_FIELD)
                .get(this.integerQueue);

        if (head != null) {
            element = getAccessibleFieldByPredicate(head, ELEMENT_FIELD)
                    .get(head);

            nextElement = getAccessibleFieldByPredicate(head, NODE_FIELD)
                    .get(head);

            head = nextElement;
            setHead(head);

            if (head == null) {
                tail = null;
                setTail(tail);
            }

            if (size != null) {
                int tmpInt = size;
                tmpInt--;
                setInternalSize(tmpInt);
            }

            return element;
        } else {
            return null;
        }
    }

    @SneakyThrows
    private void addIntElementToQueue(int value) {
        Object newNode = createNode(value);
        Object head = getAccessibleFieldByPredicate(this.integerQueue, HEAD_FIELD).get(this.integerQueue);
        Object tail = getAccessibleFieldByPredicate(this.integerQueue, TAIL_FIELD).get(this.integerQueue);
        Integer size = (Integer) getAccessibleFieldByPredicate(this.integerQueue, SIZE_FIELD).get(this.integerQueue);

        if (head == null) {
            setHead(newNode);
        } else {
            setNextNode(tail, newNode);
        }
        setTail(newNode);

        if (size == null) {
            setInternalSize(1);
        } else {
            int tmpInt = size;
            tmpInt++;
            setInternalSize(tmpInt);
        }
    }

    @SneakyThrows
    private Object createNode(int value) {
        Object nodeObject;
        Class<?> innerClass = getInnerStaticNodeClass();
        Constructor<?>[] declaredConstructors = innerClass.getDeclaredConstructors();
        Constructor<?> constructor = declaredConstructors[0];
        constructor.setAccessible(true);

        if (constructor.getParameterTypes().length == 1) {
            nodeObject = constructor.newInstance(value);
        } else {
            nodeObject = constructor.newInstance();
            Field nodeElement = getAccessibleFieldByPredicate(nodeObject, ELEMENT_FIELD);
            nodeElement.set(nodeObject, value);
        }

        return nodeObject;
    }

    @SneakyThrows
    private boolean isEmptyQueue() {
        Object head = getAccessibleFieldByPredicate(this.integerQueue,
                HEAD_FIELD)
                .get(this.integerQueue);
        return head == null;
    }

    @SneakyThrows
    private void setInternalSize(int size) {
        Field sizeField = getAccessibleFieldByPredicate(this.integerQueue,
                SIZE_FIELD);
        sizeField.setInt(this.integerQueue, size);
    }

    @SneakyThrows
    private void setHead(Object obj) {
        Field head = getAccessibleFieldByPredicate(this.integerQueue, HEAD_FIELD);
        head.set(this.integerQueue, obj);
    }

    @SneakyThrows
    private void setTail(Object obj) {
        Field tail = getAccessibleFieldByPredicate(this.integerQueue, TAIL_FIELD);
        tail.set(this.integerQueue, obj);
    }

    @SneakyThrows
    private void setNextNode(Object current, Object next) {
        Field nodeNextField = getAccessibleFieldByPredicate(current, NEXT_FIELD);
        nodeNextField.set(current, next);
    }

    private Field getAccessibleFieldByPredicate(Object object, Predicate<Field> predicate) {
        Field field = Arrays.stream(object.getClass().getDeclaredFields())
                .filter(predicate)
                .findAny()
                .orElseThrow();
        field.setAccessible(true);
        return field;
    }

    @SneakyThrows
    private Object getNodeValue(Predicate<Field> predicate) {
        Object field = getAccessibleFieldByPredicate(integerQueue, predicate).get(integerQueue);
        final Field value = getAccessibleFieldByPredicate(field, ELEMENT_FIELD);
        value.setAccessible(true);
        return value.get(field);
    }
}
