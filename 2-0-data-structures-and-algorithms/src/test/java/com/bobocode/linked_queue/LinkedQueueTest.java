package com.bobocode.linked_queue;

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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LinkedQueueTest {
    private static final String NODE_NAME = "Node";
    private static final String SIZE_NAME = "size";

    private static final Predicate<Field> NODE_FIELD = field -> field
            .getType().getSimpleName().equals(NODE_NAME)
            & (field.getName().contains("next"));

    private static final Predicate<Field> ELEMENT_FIELD = field -> field
            .getGenericType()
            .getTypeName()
            .equals("T")
            & (field.getName().contains("elem")
            | field.getName().contains("value")
            | field.getName().contains("item"));

    private static final Predicate<Field> NEXT_FIELD = field -> field
            .getGenericType()
            .getTypeName()
            .equals("Node<T>")
            & (field.getName().contains("next"));

    private static final Predicate<Field> SIZE_FIELD = field -> field
            .getType()
            .getSimpleName()
            .equals("int")
            & (field.getName().equals(SIZE_NAME));

    private static final Predicate<Field> HEAD_FIELD = field -> field
            .getType().getSimpleName().equals(NODE_NAME)
            & (field.getName().contains("head")
            | field.getName().contains("first"));

    private static final Predicate<Field> TAIL_FIELD = field -> field
            .getType().getSimpleName().equals(NODE_NAME)
            & (field.getName().contains("tail")
            | field.getName().contains("last"));

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
    void chekFieldsNameInNodeClass() {
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
    void sizeReturnsZeroWhenQueueIsEmpty() {
        int size = getInternalSize();

        assertThat(size).isEqualTo(0);
    }

    @Test
    @Order(5)
    void addRisesSize() {
        addIntElementToQueue(1);
        addIntElementToQueue(2);
        addIntElementToQueue(3);
        int size = getInternalSize();

        assertThat(size).isEqualTo(3);
    }

    @Test
    @Order(5)
    void addExpensesSize() {
        addIntElementToQueue(1);
        addIntElementToQueue(2);
        addIntElementToQueue(3);
        addIntElementToQueue(4);

        int sizeBeforePollOperation = getInternalSize();
        pollElementFromQueue();
        pollElementFromQueue();
        int sizeAfterPollOperation = getInternalSize();

        assertThat(sizeBeforePollOperation).isEqualTo(4);
        assertThat(sizeAfterPollOperation).isEqualTo(2);
    }

    @Test
    @Order(6)
    void isEmptyReturnsFalseWhenQueueIsEmpty() {
        boolean isEmpty = this.integerQueue.isEmpty();
        boolean isEmptyByReflection = isEmptyQueue();

        assertThat(isEmpty).isEqualTo(isEmptyByReflection);
    }

    @Test
    @Order(7)
    void isEmptyReturnsFalseWhenIsPolledLastElement() {
        addIntElementToQueue(1);
        addIntElementToQueue(2);

        boolean isEmptyBeforePoll = isEmptyQueue();
        pollElementFromQueue();
        pollElementFromQueue();
        boolean isEmptyAfterPoll = isEmptyQueue();

        assertThat(isEmptyBeforePoll).isEqualTo(false);
        assertThat(isEmptyAfterPoll).isEqualTo(true);
    }


    @Test
    @Order(8)
    void pollNullWhenQueueIsEmpty() {
        boolean isEmpty = isEmptyQueue();
        Integer firstElement = this.integerQueue.poll();

        assertThat(isEmpty).isEqualTo(true);
        assertThat(firstElement).isEqualTo(null);
    }

    @Test
    @Order(9)
    void poll() {
        addIntElementToQueue(11);
        addIntElementToQueue(111);

        boolean isEmpty = isEmptyQueue();
        Integer firstElement = this.integerQueue.poll();

        assertThat(isEmpty).isEqualTo(false);
        assertThat(firstElement).isEqualTo(11);
    }

    @Test
    @Order(10)
    void pollMakesQueueEmptyWhenSingleElement() {
        addIntElementToQueue(12);
        addIntElementToQueue(10);

        int sizeBeforePollOperation = getInternalSize();
        boolean isEmptyBeforePoll = isEmptyQueue();
        Integer firstElement = this.integerQueue.poll();
        Integer secondElement = this.integerQueue.poll();
        boolean isEmptyAfterPoll = isEmptyQueue();
        int sizeAfterPollOperation = getInternalSize();

        assertThat(isEmptyBeforePoll).isEqualTo(false);
        assertThat(isEmptyAfterPoll).isEqualTo(true);

        assertThat(sizeBeforePollOperation).isEqualTo(2);
        assertThat(sizeAfterPollOperation).isEqualTo(0);

        assertThat(firstElement).isEqualTo(12);
        assertThat(secondElement).isEqualTo(10);
    }


    @Test
    @Order(11)
    void addFillsQueueWhenItIsEmpty() {
        int sizeBeforeAdd = getInternalSize();
        boolean isEmptyBeforeAdd = isEmptyQueue();

        integerQueue.add(228);
        int sizeAfterAdd = getInternalSize();
        boolean isEmptyAfterAdd = isEmptyQueue();
        Integer elementValue = (Integer) pollElementFromQueue();

        assertThat(sizeBeforeAdd).isEqualTo(0);
        assertThat(sizeAfterAdd).isEqualTo(1);

        assertThat(isEmptyBeforeAdd).isEqualTo(true);
        assertThat(isEmptyAfterAdd).isEqualTo(false);

        assertThat(elementValue).isEqualTo(228);
    }

    @Test
    @Order(12)
    void addFillsQueueWhenItIsNotEmpty() {
        addIntElementToQueue(12);

        int sizeBeforeAdd = getInternalSize();
        boolean isEmptyBeforeAdd = isEmptyQueue();

        integerQueue.add(111);
        int sizeAfterAdd = getInternalSize();
        boolean isEmptyAfterAdd = isEmptyQueue();
        Integer firstValue = (Integer) pollElementFromQueue();
        Integer secondValue = (Integer) pollElementFromQueue();

        assertThat(sizeBeforeAdd).isEqualTo(1);
        assertThat(sizeAfterAdd).isEqualTo(2);

        assertThat(isEmptyBeforeAdd).isEqualTo(false);
        assertThat(isEmptyAfterAdd).isEqualTo(false);

        assertThat(firstValue).isEqualTo(12);
        assertThat(secondValue).isEqualTo(111);
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
        Class<?> innerClass = getInnerStaticNodeClass();
        Constructor constructor = innerClass.getDeclaredConstructor(Object.class);
        constructor.setAccessible(true);
        Object nodeObj = constructor.newInstance(value);

        Object head = getAccessibleFieldByPredicate(this.integerQueue,
                HEAD_FIELD)
                .get(this.integerQueue);

        Object tail = getAccessibleFieldByPredicate(this.integerQueue,
                TAIL_FIELD)
                .get(this.integerQueue);

        Integer size = (Integer) getAccessibleFieldByPredicate(this.integerQueue,
                SIZE_FIELD)
                .get(this.integerQueue);

        if (head == null) {
            setHead(nodeObj);
            setTail(nodeObj);
        } else {
            setNextInNode(tail, nodeObj);
            setTail(nodeObj);
        }

        if (size == null) {
            setInternalSize(1);
        } else {
            int tmpInt = size;
            tmpInt++;
            setInternalSize(tmpInt);
        }
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
    private void setNextInNode(Object target, Object obj) {
        Field[] nodeFields = target.getClass().getDeclaredFields();
        /*`nodeFields[1]` is the `next` filed*/
        nodeFields[1].setAccessible(true);
        nodeFields[1].set(target, obj);
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
