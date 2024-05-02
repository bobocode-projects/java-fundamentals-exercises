package com.bobocode.cs.redblack;

import com.bobocode.cs.AbstractTreeUnitTest;
import com.bobocode.cs.Tree;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.lang.reflect.Modifier.isStatic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestClassOrder(OrderAnnotation.class)
@DisplayName("Red Black Tree Test")
public class RedBlackTreeTest extends AbstractTreeUnitTest {

    private final Predicate<Field> COLOR_FIELD = field ->
            field.getName().toLowerCase().contains("color")
                    && field.getType().getSimpleName().equals("boolean");

    private final Predicate<Field> PARENT_FIELD = field ->
            field.getName().toLowerCase().contains("parent")
                    && field.getType().getSimpleName().equals("Node");
    private final Predicate<Field> VALUE_FIELD = field -> field.getName().toLowerCase().contains("value");

    @Nested
    @Order(1)
    @DisplayName("1. Node Test")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class NodeClassTest {

        private static final String NODE_PATH = "com.bobocode.cs.redblack.RedBlackSearchTree$Node";

        @Test
        @Order(1)
        @DisplayName("A nested class Node exists")
        void nodeClassExists() {
            checkTreeNode(RedBlackSearchTree.class);
        }

        @Test
        @Order(2)
        @DisplayName("Node is a static nested class")
        @SneakyThrows
        void nodeIsStaticClass() {
            var nodeClass = Class.forName(NODE_PATH);
            assertTrue(isStatic(nodeClass.getModifiers()));
        }

        @Test
        @Order(3)
        @DisplayName("Node class has 'element', 'left' and 'right'  fields")
        void nodeHasDefaultTreeElements() {
            commonNodeFieldsCheck(RedBlackSearchTree.class);
        }


        @Test
        @Order(4)
        @DisplayName("Node class has 'parent'")
        void nodeHasParentAndColorField() {
            Class<?> innerClass = getInnerClass(RedBlackSearchTree.class);
            boolean parentFieldPresent = Arrays.stream(innerClass.getDeclaredFields()).anyMatch(PARENT_FIELD);
            Assertions.assertTrue(parentFieldPresent);
        }

        @Test
        @Order(5)
        @DisplayName("Node class's 'color' is boolean")
        void nodeColorFieldIsBoolean() {
            Class<?> innerClass = getInnerClass(RedBlackSearchTree.class);
            boolean colorFieldPresent = Arrays.stream(innerClass.getDeclaredFields()).anyMatch(COLOR_FIELD);
            Assertions.assertTrue(colorFieldPresent);
        }
    }

    @Nested
    @Order(2)
    @DisplayName("2. Tree Class Test")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class TreeClassTest {
        private static final Predicate<Method> ROTATE_RIGHT_METHOD_CHECK = method ->
                method.getName().equalsIgnoreCase("rotateRight");
        private static final Predicate<Method> ROTATE_LEFT_METHOD_CHECK = method ->
                method.getName().equalsIgnoreCase("rotateLeft");
        private static final Predicate<Method> REPAIR_METHOD_CHECK = method ->
                method.getName().equalsIgnoreCase("repair");
        private static final BiPredicate<String, Field> RED_BLACK_COLOR_FIELD_EXISTS = (color, field) -> {
            try {
                field.setAccessible(true);
                boolean booleanColor = field.getBoolean(null);
                // red static field is true
                if (color.equalsIgnoreCase("red") && !booleanColor) {
                    return false;
                }
                // black static field is false
                if (color.equalsIgnoreCase("black") && booleanColor) {
                    return false;
                }
                return field.getName().equalsIgnoreCase(color) && Modifier.isStatic(field.getModifiers());
            } catch (IllegalAccessException e) {
                return false;
            }
        };


        @Test
        @Order(1)
        @DisplayName("Size and Node field")
        void properTreeFieldsCheck() {
            checkTreeFieldsCheck(RedBlackSearchTree.class);
        }

        @Test
        @Order(2)
        @DisplayName("RotateRight method exists")
        void treeHasRotateRightMethod() {
            boolean containsRightMethod = Arrays.stream(RedBlackSearchTree.class.getDeclaredMethods())
                    .anyMatch(ROTATE_RIGHT_METHOD_CHECK);
            Assertions.assertTrue(containsRightMethod);
        }

        @Test
        @Order(3)
        @DisplayName("RotateLeft method exists")
        void treeHasRotateLetMethod() {
            boolean containsLeftMethod = Arrays.stream(RedBlackSearchTree.class.getDeclaredMethods())
                    .anyMatch(ROTATE_LEFT_METHOD_CHECK);
            Assertions.assertTrue(containsLeftMethod);
        }

        @Test
        @Order(4)
        @DisplayName("Repair method exists")
        void treeHasRepairMethod() {
            boolean repairMethod = Arrays.stream(RedBlackSearchTree.class.getDeclaredMethods())
                    .anyMatch(REPAIR_METHOD_CHECK);
            Assertions.assertTrue(repairMethod);
        }

        @Test
        @Order(5)
        @DisplayName("Tree has static Red And Black field")
        void treeHasPrivateStaticREDTrueAndBlackFalseField() {
            boolean redColorFieldExists = Arrays.stream(RedBlackSearchTree.class.getDeclaredFields())
                    .filter(field -> field.getName().equalsIgnoreCase("red"))
                    .anyMatch(field -> RED_BLACK_COLOR_FIELD_EXISTS.test("red", field));
            Assertions.assertTrue(redColorFieldExists);

            boolean blackColorFieldExists = Arrays.stream(RedBlackSearchTree.class.getDeclaredFields())
                    .filter(field -> field.getName().equalsIgnoreCase("black"))
                    .anyMatch(field -> RED_BLACK_COLOR_FIELD_EXISTS.test("black", field));
            Assertions.assertTrue(blackColorFieldExists);
        }
    }

    @Nested
    @Order(3)
    @DisplayName("3.Test Insert Contains And Depth Function")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class InsertContainsDepthFunctionsTest {

        private static final boolean RED = true;
        private static final boolean BLACK = false;

        private static Stream<Arguments> expectedTreeResults() {
            return Stream.of(
                    //tree with a single element black element
                    /*
                     * .....50(B)
                     */
                    arguments(
                            new Integer[]{50}, // values to insert
                            new Integer[]{50}, // expected pre-order traversal
                            new Integer[]{}, // expected parent nodes pre-order traversal
                            new Boolean[]{BLACK}, // expected color inorder traversal
                            new Boolean[]{BLACK}, // expected color pre-order traversal
                            0
                    ),
                    /*
                     * .......50(B)
                     * ....../
                     * ...35(R)
                     */
                    arguments(
                            new Integer[]{50, 35}, // values to insert
                            new Integer[]{50, 35}, // expected pre-order traversal
                            new Integer[]{    50}, // expected parent nodes pre-order traversal
                            new Boolean[]{RED, BLACK}, // expected color inorder traversal
                            new Boolean[]{BLACK, RED}, // expected color pre-order traversal
                            1 // depth
                    ),
                    /*
                     * .......35(B)
                     * ....../  \
                     * ...10(R) 50(R)
                     */
                    arguments(
                            new Integer[]{50, 35, 10}, // values to insert
                            new Integer[]{35, 10, 50}, // expected pre-order traversal
                            new Integer[]{    35, 35}, // expected parent nodes pre-order traversal
                            new Boolean[]{RED, BLACK, RED}, // expected color inorder traversal
                            new Boolean[]{BLACK, RED, RED}, // expected color pre-order traversal
                            1 // depth
                    ),
                    /* repair case 1 left uncle is red -> recolor parent grandparent and uncle
                     * .......35(B)
                     * ....../  \
                     * ...10(B) 50(B)
                     * ..........\
                     * .........120(R)
                     */
                    arguments(
                            new Integer[]{50, 35, 10, 120}, // values to insert
                            new Integer[]{35, 10, 50, 120}, // expected pre-order traversal
                            new Integer[]{    35, 35, 50}, // expected parent nodes pre-order traversal
                            new Boolean[]{BLACK, BLACK, BLACK, RED}, // expected color inorder traversal
                            new Boolean[]{BLACK, BLACK, BLACK, RED}, // expected color pre-order traversal
                            2// depth
                    ),

                    /* repair case 1 right uncle is red -> recolor parent grandparent and uncle
                     * .......35(B)
                     * ....../  \
                     * ...10(B) 50(B)
                     * .../
                     *  5(R)
                     */
                    arguments(
                            new Integer[]{50, 35, 10, 5}, // values to insert
                            new Integer[]{35, 10, 5,  50 }, // expected pre-order traversal
                            new Integer[]{    35, 10, 35}, // expected parent nodes pre-order traversal
                            new Boolean[]{RED, BLACK, BLACK, BLACK}, // expected color inorder traversal
                            new Boolean[]{BLACK, BLACK, RED, BLACK}, // expected color pre-order traversal
                            2 // depth
                    ),
                    /* repair case 2 right triangle is formed and uncle is black -> rotate parent
                     * .......35(B)
                     * ....../  \
                     * ... 8(B) 50(B)
                     * .../  \
                     *  5(R)  10(R)
                     */
                    arguments(
                            new Integer[]{50, 35, 10, 5, 8}, // values to insert
                            new Integer[]{35, 8,  5, 10, 50}, // expected pre-order traversal
                            new Integer[]{    35, 8, 8,  35 }, // expected parent nodes pre-order traversal
                            new Boolean[]{RED, BLACK, RED, BLACK, BLACK}, // expected color inorder traversal
                            new Boolean[]{BLACK, BLACK, RED, RED, BLACK}, // expected color pre-order traversal
                            2 // depth
                    ),
                    /* repair case 3 flat line is formed to right and uncle is black -> rotate parent and recolor
                     * ............35(B)
                     * ......... /     \
                     * ...... 8(B)     120(B)
                     * ..... /  \       /   \
                     * ...5(R)  10(R) 50(R) 200(R)
                     */
                    arguments(
                            new Integer[]{50, 35, 10, 5, 8, 120, 200}, // values to insert
                            new Integer[]{35, 8,  5, 10, 120, 50,  200}, // expected pre-order traversal
                            new Integer[]{    35, 8, 8,  35,  120, 120}, // expected parent nodes pre-order traversal
                            new Boolean[]{RED, BLACK, RED, BLACK, RED, BLACK, RED}, // expected color inorder traversal
                            new Boolean[]{BLACK, BLACK, RED, RED, BLACK, RED, RED}, // expected color pre-order traversal
                            2 // depth
                    ),
                    /* repair case 1 uncle is red -> recolor parent, grandparent and uncle
                     * ............35(B)
                     * ......... /     \
                     * ...... 8(R)     120(B)
                     * ..... /  \      /   \
                     * ...5(B)  10(B) 50(R) 200(R)
                     * ../
                     *  3(R)
                     */
                    arguments(
                            new Integer[]{50, 35, 10, 5, 8, 120, 200, 3}, // values to insert
                            new Integer[]{35, 8,  5, 3, 10, 120, 50,  200}, // expected pre-order traversal
                            new Integer[]{    35, 8, 5, 8,  35,  120, 120 }, // expected parent nodes pre-order traversal

                            new Boolean[]{RED, BLACK, RED, BLACK, BLACK, RED, BLACK, RED}, // expected color inorder traversal
                            new Boolean[]{BLACK, RED, BLACK, RED, BLACK, BLACK, RED, RED}, // expected color pre-order traversal
                            3 // depth
                    ),
                    /* nothing to repair
                     * ............35(B)
                     * ......... /     \
                     * ...... 8(R)     120(B)
                     * ..... /  \      /   \
                     * ...5(B)  10(B) 50(R) 200(R)
                     * ../   \
                     *  3(R)  6(R)
                     */
                    arguments(
                            new Integer[]{50, 35, 10, 5, 8, 120, 200, 3, 6}, // values to insert
                            new Integer[]{35, 8,  5, 3, 6, 10, 120, 50,  200}, // expected pre-order traversal
                            new Integer[]{    35, 8, 5, 5, 8,  35,  120, 120 }, // expected parent nodes pre-order traversal
                            new Boolean[]{RED, BLACK, RED, RED, BLACK, BLACK, RED, BLACK, RED}, // expected color inorder traversal
                            new Boolean[]{BLACK, RED, BLACK, RED, RED, BLACK, BLACK, RED, RED}, // expected color pre-order traversal
                            3 // depth
                    ),

                    /*
                     * ............8(B)
                     * ......... /     \
                     * ...... 5(R)     35(R)
                     * ..... /  \      /  \
                     * ...3(B)  6(B) 10(B) 120(B)
                     *           \          /   \
                     *           7(R)     50(R) 200(R)
                     */
                    arguments(
                            new Integer[]{50, 35, 10, 5, 8, 120, 200, 3, 6, 7}, // values to insert
                            new Integer[]{8,  5, 3, 6, 7, 35, 10, 120, 50,  200}, // expected pre-order traversal
                            new Integer[]{    8, 5, 5, 6, 8,  35, 35,  120, 120}, // expected parent nodes pre-order traversal
                            new Boolean[]{BLACK, RED, BLACK, RED, BLACK, BLACK, RED, RED, BLACK, RED}, // expected color inorder traversal
                            new Boolean[]{BLACK, RED, BLACK, BLACK, RED, RED, BLACK, BLACK, RED, RED}, // expected color pre-order traversal
                            3 // depth
                    ),
                    /*
                     * ............8(B)
                     * ......... /     \
                     * ...... 5(R)     35(R)
                     * ..... /  \      /  \
                     * ...3(B)  6(B) 10(B) 120(B)
                     *           \          /   \
                     *           7(R)     50(R) 200(R)
                     */
                    arguments(
                            new Integer[]{50, 35, 10, 5, 8, 120, 200, 3, 6, 7}, // values to insert
                            new Integer[]{8,  5, 3, 6, 7, 35, 10, 120, 50, 200}, // expected pre-order traversal
                            new Integer[]{    8, 5, 5, 6, 8,  35, 35,  120, 120}, // expected parent nodes pre-order traversal
                            new Boolean[]{BLACK, RED, BLACK, RED, BLACK, BLACK, RED, RED, BLACK, RED}, // expected color inorder traversal
                            new Boolean[]{BLACK, RED, BLACK, BLACK, RED, RED, BLACK, BLACK, RED, RED}, // expected color pre-order traversal
                            3 // depth
                    ),
                    /*
                     * ............ 8(B)
                     * ......... /      \
                     * ...... 5(R)      35(R)
                     * ..... /  \       /    \
                     * ...3(B)  6(B)  10(B)   120(B)
                     *           \       \      / \
                     *           7(R)    20(R) 50(R) 200(R)
                     */
                    arguments(
                            new Integer[]{50, 35, 10, 5, 8, 120, 200, 3, 6, 7, 20}, // values to insert
                            new Integer[]{8,  5, 3, 6, 7, 35, 10, 20, 120, 50,  200}, // expected pre-order traversal
                            new Integer[]{    8, 5, 5, 6, 8,  35, 10, 35,  120, 120}, // expected parent nodes pre-order traversal

                            new Boolean[]{BLACK, RED, BLACK, RED, BLACK, BLACK, RED, RED, RED, BLACK, RED}, // expected color inorder traversal
                            new Boolean[]{BLACK, RED, BLACK, BLACK, RED, RED, BLACK, RED, BLACK, RED, RED}, // expected color pre-order traversal
                            3 // depth
                    ),
                    /*
                     * ............ 8(B)
                     * ......... /      \
                     * ...... 5(R)      35(R)
                     * ..... /  \       /    \
                     * ...3(B)  6(B)  10(B)   120(B)
                     *           \       \      / \
                     *           7(R)    20(R) 50(R) 200(R)
                     */
                    arguments(
                            new Integer[]{50, 35, 10, 5, 8, 120, 200, 3, 6, 7, 20}, // values to insert
                            new Integer[]{8,  5, 3, 6, 7, 35, 10, 20, 120, 50,  200}, // expected pre-order traversal
                            new Integer[]{    8, 5, 5, 6, 8,  35, 10, 35,  120, 120}, // expected parent nodes pre-order traversal
                            new Boolean[]{BLACK, RED, BLACK, RED, BLACK, BLACK, RED, RED, RED, BLACK, RED}, // expected color inorder traversal
                            new Boolean[]{BLACK, RED, BLACK, BLACK, RED, RED, BLACK, RED, BLACK, RED, RED}, // expected color pre-order traversal
                            3 // depth
                    ),
                    /*
                     * ..............8(B)
                     * .........../     \
                     * ...... 5(R)        35(R)
                     * ....  /  \         /    \
                     * ..3(B)  6(B)      20(B)  120(B)
                     *           \      /    \    /  \
                     *......... 7(R) 10(R) 30(R) 50(R) 200(R)
                     */
                    arguments(
                            new Integer[]{50, 35, 10, 5, 8, 120, 200, 3, 6, 7, 20, 30}, // values to insert
                            new Integer[]{8,  5, 3, 6, 7, 35, 20, 10, 30, 120, 50,  200}, // expected pre-order traversal
                            new Integer[]{    8, 5, 5, 6, 8,  35, 20, 20, 35,  120, 120 }, // expected parent nodes pre-order traversal
                            new Boolean[]{BLACK, RED, BLACK, RED, BLACK, RED, BLACK, RED, RED, RED, BLACK, RED}, // expected color inorder traversal
                            new Boolean[]{BLACK, RED, BLACK, BLACK, RED, RED, BLACK, RED, RED, BLACK, RED, RED}, // expected color pre-order traversal
                            3 // depth
                    ),
                    /*
                     * ..............8(B)
                     * .........../       \
                     * ...... 5(B)        35(B)
                     * ....  /  \         /    \
                     * ..3(B)  6(B)      20(R)  120(B)
                     *           \      /    \    /  \
                     *......... 7(R) 10(B) 30(B) 50(R) 200(R)
                     * ......................\
                     * .....................33(R)
                     */
                    arguments(
                            new Integer[]{50, 35, 10, 5, 8, 120, 200, 3, 6, 7, 20, 30, 33}, // values to insert
                            new Integer[]{8, 5, 3, 6, 7, 35, 20, 10, 30, 33, 120, 50,  200}, // expected pre-order traversal
                            new Integer[]{   8, 5, 5, 6, 8,  35, 20, 20, 30, 35,  120, 120 }, // expected parent nodes pre-order traversal
                            new Boolean[]{BLACK, BLACK, BLACK, RED, BLACK, BLACK, RED, BLACK, RED, BLACK, RED, BLACK, RED}, // expected color inorder traversal
                            new Boolean[]{BLACK, BLACK, BLACK, BLACK, RED, BLACK, RED, BLACK, BLACK, RED, BLACK, RED, RED}, // expected color pre-order traversal
                            4 // depth
                    )
            );
        }

        @Test
        @Order(1)
        @DisplayName("Null insert is not allowed")
        void insertThrowsExceptionWhenArgumentIsNull() {
            final Tree<Integer> redBlackSearchTree = RedBlackSearchTree.of(someElements);
            assertThatNullPointerException().isThrownBy(() -> redBlackSearchTree.insert(null));
        }

        @Test
        @Order(2)
        @DisplayName("Null contains is not allowed")
        void containsThrowsExceptionWhenArgumentIsNull() {
            final Tree<Integer> redBlackSearchTree = RedBlackSearchTree.of(someElements);
            assertThatNullPointerException().isThrownBy(() -> redBlackSearchTree.contains(null));
        }

        @Test
        @Order(3)
        void containsReturnsTrueIfElementExist() {
            final Tree<Integer> redBlackSearchTree = RedBlackSearchTree.of(someElements);
            assertThat(redBlackSearchTree.contains(10)).isTrue();
            assertThat(redBlackSearchTree.contains(9)).isTrue();
            assertThat(redBlackSearchTree.contains(11)).isTrue();
        }

        @Test
        @Order(4)
        @DisplayName("Of function works")
        void of() {
            final Tree<Integer> redBlackSearchTree = RedBlackSearchTree.of(someElements);
            testOfMethod(redBlackSearchTree);
        }

        @Test
        @Order(5)
        @DisplayName("Insert function works")
        void insert() {
            final Tree<Integer> redBlackSearchTree = new RedBlackSearchTree<>();
            testInsertMethod(redBlackSearchTree);
        }

        @Test
        @Order(6)
        @DisplayName("Only unique values are allowed")
        void sizeDoesNotGrowWhenInsertingNotUnique() {
            final Tree<Integer> redBlackSearchTree = RedBlackSearchTree.of(10, 11, 12);


            assertThat(getInnerSize(redBlackSearchTree)).isEqualTo(3);

            redBlackSearchTree.insert(10);
            redBlackSearchTree.insert(11);
            redBlackSearchTree.insert(12);

            assertThat(getInnerSize(redBlackSearchTree)).isEqualTo(3);
        }

        @ParameterizedTest
        @Order(7)
        @DisplayName("Insert method re-balances tree correctly")
        @MethodSource("expectedTreeResults")
        void testBalancingInInsertMethod(Integer[] valuesToInsert,
                                         Integer[] expectedPreOrderValueSequence,
                                         Integer[] expectedParentPreOrderValueSequence,
                                         Boolean[] expectedInorderColorSequence,
                                         Boolean[] expectedPreorderColorSequence,
                                         Integer expectedDepth
        ) {
            final Tree<Integer> redBlackSearchTree = RedBlackSearchTree.of(valuesToInsert);


            Integer[] expectedInorderValueSequence = Arrays.copyOf(valuesToInsert, valuesToInsert.length);
            Arrays.sort(expectedInorderValueSequence);

            List<Integer> inOrderTraversedElements = new ArrayList<>(getInnerSize(redBlackSearchTree));
            redBlackSearchTree.inOrderTraversal(inOrderTraversedElements::add);


            List<Integer> preOrderTraversedElements = new ArrayList<>(getInnerSize(redBlackSearchTree));
            redBlackSearchTree.preOrderTraversal(preOrderTraversedElements::add);

            List<Integer> preOrderParentTraversedElements = new ArrayList<>(getInnerSize(redBlackSearchTree));
            preorderTraverseParentNode(getRootObject(redBlackSearchTree), parentValue -> preOrderParentTraversedElements.add((Integer) parentValue), PARENT_FIELD, VALUE_FIELD);


            List<Boolean> inOrderTraversedColor = new ArrayList<>(getInnerSize(redBlackSearchTree));
            inorderTraverseTree(getRootObject(redBlackSearchTree), color -> inOrderTraversedColor.add((Boolean) color), COLOR_FIELD);

            List<Boolean> preOrderTraversedColor = new ArrayList<>(getInnerSize(redBlackSearchTree));
            preorderTraverseTree(getRootObject(redBlackSearchTree), color -> preOrderTraversedColor.add((Boolean) color), COLOR_FIELD);
            // size and depth
            assertEquals(redBlackSearchTree.depth(), expectedDepth);
            assertEquals(redBlackSearchTree.size(), valuesToInsert.length);
            // elements
            assertThat(inOrderTraversedElements).isEqualTo(List.of(expectedInorderValueSequence));
            assertThat(preOrderTraversedElements).isEqualTo(List.of(expectedPreOrderValueSequence));
            // colors
            assertThat(inOrderTraversedColor).isEqualTo(List.of(expectedInorderColorSequence));
            assertThat(preOrderTraversedColor).isEqualTo(List.of(expectedPreorderColorSequence));
            // parent values
            assertThat(preOrderParentTraversedElements).isEqualTo(List.of(expectedParentPreOrderValueSequence));
        }


        @SneakyThrows
        @SuppressWarnings("unchecked")
        private <R> void inorderTraverseTree(Object node, Consumer<R> consumer, Predicate<Field> fieldPredicate) {
            if (node == null) {
                return;
            }
            inorderTraverseTree(getLeftNode(node), consumer, fieldPredicate);
            consumer.accept((R) getNodesField(node, fieldPredicate).get(node));
            inorderTraverseTree(getRightNode(node), consumer, fieldPredicate);
        }

        @SneakyThrows
        @SuppressWarnings("unchecked")
        private <R> void preorderTraverseTree(Object node, Consumer<R> consumer, Predicate<Field> fieldPredicate) {
            if (node == null) {
                return;
            }
            R element = (R) getNodesField(node, fieldPredicate).get(node);
            if (element != null){
                consumer.accept(element);
            }
            preorderTraverseTree(getLeftNode(node), consumer, fieldPredicate);
            preorderTraverseTree(getRightNode(node), consumer, fieldPredicate);
        }

        @SneakyThrows
        @SuppressWarnings("unchecked")
        private <R> void preorderTraverseParentNode(Object node, Consumer<R> consumer, Predicate<Field> parentNodePredicate, Predicate<Field> valueNodePredicate) {
            if (node == null) {
                return;
            }
            Object parentNode =  getNodesField(node, parentNodePredicate).get(node);
            if (parentNode != null){
                consumer.accept((R) getNodesField(parentNode, valueNodePredicate).get(parentNode));
            }
            preorderTraverseParentNode(getLeftNode(node), consumer, parentNodePredicate, valueNodePredicate);
            preorderTraverseParentNode(getRightNode(node), consumer, parentNodePredicate, valueNodePredicate);
        }
    }
}