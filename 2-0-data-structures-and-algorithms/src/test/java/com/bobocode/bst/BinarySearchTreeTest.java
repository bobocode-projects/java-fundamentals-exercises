package com.bobocode.bst;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BinarySearchTreeTest {

    private static final Predicate<Field> SIZE_FIELD = field -> field.getName().equals("size") || field.getName().equals("length");
    private static final Predicate<Field> NODE_FIELD = field -> field.getType().getSimpleName().equals("Node");
    private static final Predicate<Field> ELEMENT_FIELD = field -> field.getName().equals("element")
            || field.getName().equals("item")
            || field.getName().equals("value");
    private static final Predicate<Field> LEFT_FIELD = field -> field.getName().equals("left");
    private static final Predicate<Field> RIGHT_FIELD = field -> field.getName().equals("right");

    private static final Integer[] someElements = {10, 9, 11, 8, 12, 7};
    private BinarySearchTree<Integer> tree;

    @Test
    @Order(1)
    void checkProperNodeClassName() {
        tree = new RecursiveBinarySearchTree<>();
        Class<?> innerClass = getInnerClass();
        String name = innerClass.getSimpleName();

        assertThat(name).isEqualTo("Node");
    }

    @Test
    @Order(2)
    void checkProperTreeFields() {
        tree = new RecursiveBinarySearchTree<>();
        Class<?> treeClass = tree.getClass();

        boolean hasSizeField = Arrays.stream(treeClass.getDeclaredFields())
                .anyMatch(SIZE_FIELD);

        boolean hasNodeField = Arrays.stream(treeClass.getDeclaredFields())
                .anyMatch(NODE_FIELD);

        assertThat(hasSizeField).isTrue();
        assertThat(hasNodeField).isTrue();
    }

    @Test
    @Order(3)
    void checkProperNodeFields() {
        tree = new RecursiveBinarySearchTree<>();
        Class<?> innerClass = getInnerClass();

        boolean isElement = Arrays.stream(innerClass.getDeclaredFields())
                .anyMatch(ELEMENT_FIELD);

        boolean isLeft = Arrays.stream(innerClass.getDeclaredFields())
                .anyMatch(LEFT_FIELD);

        boolean isRight = Arrays.stream(innerClass.getDeclaredFields())
                .anyMatch(RIGHT_FIELD);

        assertThat(isElement).isTrue();
        assertThat(isLeft).isTrue();
        assertThat(isRight).isTrue();
    }

    @Test
    @Order(4)
    void shouldCreateTreeFromListOfElements() {
        tree = RecursiveBinarySearchTree.of(someElements);
        for (var e : someElements) {
            assertThat(contains(getRootObject(), e)).isTrue();
        }

        assertThat(getInnerSize()).isEqualTo(someElements.length);
    }

    @Test
    @Order(5)
    void shouldThrowNullPointerExceptionIfInsertNull() {
        fillTestTree(someElements);
        assertThatNullPointerException().isThrownBy(() -> tree.insert(null));
    }

    @Test
    @Order(6)
    void shouldInsertUniqueElements() {
        tree = new RecursiveBinarySearchTree<>();
        for (Integer e : someElements) {
            assertThat(contains(getRootObject(), e)).isFalse(); //does not contain
            assertThat(tree.insert(e)).isTrue(); //do insert
            assertThat(contains(getRootObject(), e)).isTrue(); //and contains
        }
    }

    @Test
    @Order(7)
    void shouldIncreaseSizeWhenInsertUnique() {
        tree = new RecursiveBinarySearchTree<>();

        tree.insert(10);
        tree.insert(15);
        tree.insert(20);

        assertThat(getInnerSize()).isEqualTo(3);
    }

    @Test
    @Order(8)
    void shouldNotInsertNonUniqueElements() {
        fillTestTree(someElements);

        assertThat(tree.insert(10)).isFalse();
        assertThat(tree.insert(11)).isFalse();
        assertThat(tree.insert(12)).isFalse();
    }

    @Test
    @Order(9)
    void shouldNotIncreaseSizeWhenInsertNotUnique() {
        fillTestTree(someElements);

        assertThat(getInnerSize()).isEqualTo(someElements.length);

        tree.insert(10);
        tree.insert(11);
        tree.insert(12);

        assertThat(getInnerSize()).isEqualTo(someElements.length);
    }

    @Order(10)
    @ParameterizedTest
    @MethodSource("depthArguments")
    void depth(Integer[] elements, int depth) {
        fillTestTree(elements);
        assertThat(tree.depth()).isEqualTo(depth);
    }

    @Test
    @Order(11)
    void inorderTraversal() {
        fillTestTree(someElements);
        Integer[] sortedElements = Arrays.copyOf(someElements, someElements.length);
        Arrays.sort(sortedElements);

        List<Integer> traversedElements = new ArrayList<>(getInnerSize());
        tree.inOrderTraversal(traversedElements::add);

        assertThat(traversedElements).isEqualTo(List.of(sortedElements));
    }

    @Test
    @Order(12)
    void shouldInsertToRootIfEmpty() {
        tree = new RecursiveBinarySearchTree<>();
        assertThat(getRootObject()).isNull();
        tree.insert(10);
        assertThat(getRootObject()).isNotNull();
    }

    @Test
    @Order(13)
    void shouldInsertLeftIfLessThanRoot() {
        tree = new RecursiveBinarySearchTree<>();

        tree.insert(10); //root
        tree.insert(5); //left

        assertThat(getLeftNode(getRootObject())).isNotNull();
    }

    @Test
    @Order(14)
    void shouldInsertRightIfGreaterThanRoot() {
        tree = new RecursiveBinarySearchTree<>();

        tree.insert(10); //root
        tree.insert(15); //right

        assertThat(getRightNode(getRootObject())).isNotNull();
    }

    @Test
    @Order(15)
    void depthShouldGrowWhenInsert() {
        tree = new RecursiveBinarySearchTree<>();

        tree.insert(13);
        tree.insert(11);
        tree.insert(12);
        tree.insert(15);
        tree.insert(-15);

        assertThat(tree.depth()).isGreaterThan(0);
    }

    @Test
    @Order(16)
    void depthShouldBeZeroIfRootNull () {
        tree = new RecursiveBinarySearchTree<>();
        assertThat(tree.depth()).isEqualTo(0);
    }

    @Test
    @Order(17)
    void shouldReturnTrueIfContains() {
        fillTestTree(someElements);

        assertThat(tree.contains(10)).isTrue();
        assertThat(tree.contains(9)).isTrue();
        assertThat(tree.contains(11)).isTrue();
    }

    @Test
    @Order(18)
    void shouldReturnFalseIfNotContains() {
        fillTestTree(someElements);
        assertThat(tree.contains(100)).isFalse();
    }

    @Test
    @Order(19)
    void shouldThrowNullPointerExceptionIfContainsParameterIsNull() {
        tree = new RecursiveBinarySearchTree<>();
        assertThatNullPointerException().isThrownBy(() -> tree.contains(null));
    }

    public static Stream<Arguments> depthArguments() {
        return Stream.of(
                //empty tree
                arguments(new Integer[]{}, 0),
                //tree with a single element
                arguments(new Integer[]{24}, 0),
                /*
                 * .......10
                 * ....../  \
                 * .....5   15
                 * ..../      \
                 * ...1       20
                 */
                arguments(new Integer[]{10, 5, 15, 1, 20}, 2),
                /*
                 * ..1
                 * ...\
                 * ....2
                 * .....\
                 * ..... 3
                 * .......\
                 * ........4
                 * .........\
                 * ..........5
                 */
                arguments(new Integer[]{1, 2, 3, 4, 5}, 4));
    }

    @SneakyThrows
    private int getInnerSize() {
        return (int) getInnerSizeField().get(tree);
    }

    private Field getInnerSizeField() {
        Field sizeField = Arrays.stream(tree.getClass().getDeclaredFields())
                .filter(SIZE_FIELD)
                .findAny()
                .orElseThrow();
        sizeField.setAccessible(true);
        return sizeField;
    }

    private Class<?> getInnerClass() {
        return Arrays.stream(tree.getClass().getDeclaredClasses())
                .filter(Class::isMemberClass)
                .findAny()
                .orElseThrow();
    }

    @SneakyThrows
    private Field getRootField() {
        Field nodeField = Arrays.stream(tree.getClass().getDeclaredFields())
                .filter(NODE_FIELD)
                .findAny()
                .orElseThrow();
        nodeField.setAccessible(true);
        return nodeField;
    }

    @SneakyThrows
    private Object getRootObject() {
        Field nodeField = Arrays.stream(tree.getClass().getDeclaredFields())
                .filter(NODE_FIELD)
                .findAny()
                .orElseThrow();
        nodeField.setAccessible(true);
        return nodeField.get(tree);
    }

    @SneakyThrows
    private boolean contains(Object node, int element) {
        return findNodeByElement(node, element) != null;
    }

    private Object findNodeByElement(Object node, int element) {
        if (node == null) {
            return null;
        }
        if (element == getElement(node)) {
            return node;
        } else if (element < getElement(node)) {
            return findNodeByElement(getLeftNode(node), element);
        } else if (element > getElement(node)) {
            return findNodeByElement(getRightNode(node), element);
        } else {
            return node;
        }
    }

    @SneakyThrows
    private Field getNodesField(Object node, Predicate<Field> option) {
        Field field = Arrays.stream(node.getClass().getDeclaredFields())
                .filter(option)
                .findAny()
                .orElseThrow();
        field.setAccessible(true);
        return field;
    }

    @SneakyThrows
    private int getElement(Object node) {
        return (int) getNodesField(node, ELEMENT_FIELD).get(node);
    }

    @SneakyThrows
    private Object getLeftNode(Object node) {
        return getNodesField(node, LEFT_FIELD).get(node);
    }

    @SneakyThrows
    private Object getRightNode(Object node) {
        return getNodesField(node, RIGHT_FIELD).get(node);
    }

    @SneakyThrows
    private Object newNode(int element) {
        Object nodeInstance;
        Constructor<?> constructor = getInnerClass().getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        nodeInstance = constructor.newInstance(element);
        return nodeInstance;
    }

    @SneakyThrows
    private void insertElement(int element) {

        Object root = getRootObject();

        if (root == null) {
            getRootField().set(tree, newNode(element));
        } else {
            insertToSubtree(root, element);
        }
    }

    private boolean insertToSubtree(Object node, int element) {
        if (element < getElement(node)) {
            return insertLeft(node, element);
        } else if (element > getElement(node)) {
            return insertRight(node, element);
        } else {
            return false;
        }
    }

    @SneakyThrows
    private boolean insertLeft(Object node, int element) {
        if (getLeftNode(node) != null) {
            return insertToSubtree(getLeftNode(node), element);
        } else {
            getNodesField(node, LEFT_FIELD).set(node, newNode(element));
            return true;
        }
    }

    @SneakyThrows
    private boolean insertRight(Object node, int element) {
        if (getRightNode(node) != null) {
            return insertToSubtree(getRightNode(node), element);
        } else {
            getNodesField(node, RIGHT_FIELD).set(node, newNode(element));
            return true;
        }
    }

    @SneakyThrows
    private void fillTestTree(Integer[] elements) {
        tree = new RecursiveBinarySearchTree<>();
        for (Integer e : elements) {
            insertElement(e);
        }
        getInnerSizeField().set(tree, elements.length);
    }
}
