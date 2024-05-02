package com.bobocode.cs.binary;

import com.bobocode.cs.AbstractTreeUnitTest;
import com.bobocode.cs.Tree;
import lombok.SneakyThrows;
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
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * A reflection-based test class for {@link ArrayList}.
 * <p>
 * PLEASE NOTE: we use Reflection API only for learning purposes. It should NOT be used for production tests.
 *
 * @author Ivan Virchenko
 * @author Maksym Stasiuk
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BinarySearchTreeTest extends AbstractTreeUnitTest {

    private Tree<Integer> tree = new BinarySearchTree<>();

    @Test
    @Order(1)
    void properNodeClassNameCheck() {
        checkTreeNode(BinarySearchTree.class);
    }

    @Test
    @Order(2)
    void properTreeFieldsCheck() {
        checkTreeFieldsCheck(BinarySearchTree.class);
    }

    @Test
    @Order(3)
    void properNodeFieldsCheck() {
        commonNodeFieldsCheck(BinarySearchTree.class);
    }

    @Test
    @Order(4)
    void of() {
        tree = BinarySearchTree.of(someElements);
        testOfMethod(tree);
    }

    @Test
    @Order(5)
    void insert() {
        testInsertMethod(tree);
    }

    @Test
    @Order(6)
    void insertToRootIfTreeIsEmpty() {
        assertThat(getRootObject(tree)).isNull();
        tree.insert(10);
        assertThat(getRootObject(tree)).isNotNull();
    }

    @Test
    @Order(7)
    void insertLeftIfLessThanRoot() {
        tree.insert(10); //root
        tree.insert(5); //left

        assertThat(getLeftNode(getRootObject(tree))).isNotNull();
    }

    @Test
    @Order(8)
    void insertRightIfGreaterThanRoot() {
        tree.insert(10); //root
        tree.insert(15); //right

        assertThat(getRightNode(getRootObject(tree))).isNotNull();
    }

    @Test
    @Order(9)
    void insertDoesNotAddDuplicateElements() {
        fillTestTree(10, 11, 12);

        assertThat(tree.insert(10)).isFalse();
        assertThat(tree.insert(11)).isFalse();
        assertThat(tree.insert(12)).isFalse();
    }

    @Test
    @Order(10)
    void insertThrowsExceptionWhenArgumentIsNull() {
        fillTestTree(someElements);
        assertThatNullPointerException().isThrownBy(
                () -> tree.insert(null)
        );
    }

    @Test
    @Order(11)
    void containsReturnsTrueIfElementExist() {
        fillTestTree(9, 10, 11);

        assertThat(tree.contains(10)).isTrue();
        assertThat(tree.contains(9)).isTrue();
        assertThat(tree.contains(11)).isTrue();
    }

    @Test
    @Order(12)
    void containsReturnsFalseIfElementDoesntExist() {
        fillTestTree(someElements);
        assertThat(tree.contains(100)).isFalse();
    }

    @Test
    @Order(13)
    void containsThrowsExceptionIFParameterIsNull() {
        assertThatNullPointerException().isThrownBy(() -> tree.contains(null));
    }

    @Test
    @Order(14)
    void sizeIsGrowingWhenInserting() {
        tree.insert(10);
        tree.insert(15);
        tree.insert(20);

        assertThat(getInnerSize(tree)).isEqualTo(3);
    }

    @Test
    @Order(15)
    void sizeDoesNotGrowWhenInsertingNotUnique() {
        fillTestTree(10, 11, 12);

        assertThat(getInnerSize(tree)).isEqualTo(3);

        tree.insert(10);
        tree.insert(11);
        tree.insert(12);

        assertThat(getInnerSize(tree)).isEqualTo(3);
    }

    @Order(16)
    @ParameterizedTest
    @MethodSource("depthArguments")
    void depth(Integer[] elements, int depth) {
        fillTestTree(elements);
        assertThat(tree.depth()).isEqualTo(depth);
    }

    @Test
    @Order(17)
    void depthGrowWhenInsert() {

        tree.insert(13);
        tree.insert(11);
        tree.insert(12);
        tree.insert(15);
        tree.insert(-15);

        assertThat(tree.depth()).isGreaterThan(0);
    }

    @Test
    @Order(18)
    void depthIsZeroIfRootIsNull() {
        assertThat(tree.depth()).isEqualTo(0);
    }

    @Test
    @Order(19)
    void inorderTraversal() {
        fillTestTree(someElements);
        Integer[] sortedElements = Arrays.copyOf(someElements, someElements.length);
        Arrays.sort(sortedElements);

        List<Integer> traversedElements = new ArrayList<>(getInnerSize(tree));
        tree.inOrderTraversal(traversedElements::add);

        assertThat(traversedElements).isEqualTo(List.of(sortedElements));
    }

    @Test
    @Order(20)
    void preOrderTraversal() {
        fillTestTree(someElements);
        Integer[] preOrderOfSomeElements = {10, 9, 8, 7, 11, 12};

        List<Integer> traversedElements = new ArrayList<>(getInnerSize(tree));
        tree.preOrderTraversal(traversedElements::add);

        assertThat(traversedElements).isEqualTo(List.of(preOrderOfSomeElements));
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
                arguments(new Integer[]{1, 2, 3, 4, 5}, 4),
                /*
                 * .........6
                 * ....../.....\
                 * .....2.......7
                 * .../...\......\
                 * ..1.....5......8
                 * ......./........\
                 * ......4..........9
                 * ...../.............
                 * ....3...............
                 */
                arguments(new Integer[]{6, 2, 7, 1, 5, 8, 4, 9, 3}, 4));
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
    private Object newNode(int element) {
        Object nodeInstance;
        Constructor<?>[] constructors = getInnerClass(BinarySearchTree.class).getDeclaredConstructors();
        Constructor<?> constructor;

        constructor = constructors[0];
        constructor.setAccessible(true);
        if (constructor.getParameters().length == 1) {
            nodeInstance = constructor.newInstance(element);
        } else {
            nodeInstance = constructor.newInstance();
            Field nodeElement = getNodesField(nodeInstance, ELEMENT_FIELD);
            nodeElement.set(nodeInstance, element);
        }
        return nodeInstance;
    }

    @SneakyThrows
    private void insertElement(Tree<Integer> tree, int element) {

        Object root = getRootObject(tree);

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
    private void fillTestTree(Integer... elements) {
        tree = new BinarySearchTree<>();
        for (Integer e : elements) {
            insertElement(tree, e);
        }
        getInnerSizeField(tree.getClass()).set(tree, elements.length);
    }
}
