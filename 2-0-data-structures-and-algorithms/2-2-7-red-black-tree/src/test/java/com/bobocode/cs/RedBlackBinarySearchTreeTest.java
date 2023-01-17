package com.bobocode.cs;

import static java.lang.reflect.Modifier.isStatic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import org.assertj.core.api.Condition;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.bobocode.reflection.ReflectionUtils;

import lombok.SneakyThrows;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@DisplayName("RedBlack Tree")
class RedBlackBinarySearchTreeTest {

    @Nested
    @Order(1)
    @DisplayName("1. Nested classes tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class NodeClassTest {

        @Test
        @Order(1)
        @DisplayName("1. Color is a enum")
        @SneakyThrows
        void colorIsEnum() {
            var colorClass = Class.forName(String.format("%s.%s$%s", PACKAGE_NAME, TEST_CLASS_NAME, COLOR_CLASS_NAME));

            assertTrue(colorClass.isEnum());
        }

        @Test
        @Order(2)
        @DisplayName("2. Color has two values 'RED' and 'BLACK'")
        @SneakyThrows
        void colorHasTwoValues() {
            var colorClass = Class.forName(String.format("%s.%s$%s", PACKAGE_NAME, TEST_CLASS_NAME, COLOR_CLASS_NAME));
            var declaredFields = colorClass.getDeclaredFields();

            assertThat(declaredFields).hasSize(3);
            assertThat(declaredFields).anyMatch(f -> f.getName().equals(COLOR_RED))
              .anyMatch(f -> f.getName().equals(COLOR_BLACK));
        }

        @Test
        @Order(3)
        @DisplayName("3. Node is a static nested class")
        @SneakyThrows
        void nodeIsStaticClass() {
            var nodeClass = Class.forName(String.format("%s.%s$%s", PACKAGE_NAME, TEST_CLASS_NAME, NODE_CLASS_NAME));

            assertTrue(isStatic(nodeClass.getModifiers()));
        }

        @Test
        @Order(4)
        @DisplayName("4. Node class has one type parameter")
        @SneakyThrows
        void nodeHasOneTypeParameter() {
            var nodeClass = Class.forName(String.format("%s.%s$%s", PACKAGE_NAME, TEST_CLASS_NAME, NODE_CLASS_NAME));
            var typeParametersList = nodeClass.getTypeParameters();

            assertThat(typeParametersList).hasSize(1);
        }

        @Test
        @Order(5)
        @DisplayName("5. Node class has field that holds value object")
        @SneakyThrows
        void nodeHasElementField() {
            var nodeClass = Class.forName(String.format("%s.%s$%s", PACKAGE_NAME, TEST_CLASS_NAME, NODE_CLASS_NAME));
            var fields = Arrays.stream(nodeClass.getDeclaredFields()).toList();

            assertThat(fields).withFailMessage(String.format("Expected one of the %s for value field",
              NODE_ELEMENT_FIELD_NAME_LIST)).anyMatch(NODE_ELEMENT_FIELD_NAME_VALIDATOR);
        }

        @Test
        @Order(6)
        @DisplayName("6. Node class has field references to left and right nodes")
        @SneakyThrows
        void nodeHasLeftAndRightFields() {
            var nodeClass = Class.forName(String.format("%s.%s$%s", PACKAGE_NAME, TEST_CLASS_NAME, NODE_CLASS_NAME));
            var fields = Arrays.stream(nodeClass.getDeclaredFields()).toList();

            assertThat(fields).withFailMessage(String.format("Expected the next field names for left and right nodes %s, %s",
              NODE_LEFT_FIELD_NAME_LIST, NODE_RIGHT_FIELD_NAME_LIST))
              .anyMatch(NODE_LEFT_FIELD_NAME_VALIDATOR).anyMatch(NODE_RIGHT_FIELD_NAME_VALIDATOR);
        }

        @Test
        @Order(7)
        @DisplayName("7. Node class has 'parent' field")
        @SneakyThrows
        void nodeHasParentField() {
            var nodeClass = Class.forName(String.format("%s.%s$%s", PACKAGE_NAME, TEST_CLASS_NAME, NODE_CLASS_NAME));
            var fields = Arrays.stream(nodeClass.getDeclaredFields()).toList();

            assertThat(fields).withFailMessage("Expected %s for parent field name", NODE_PARENT_FIELD_NAME_LIST)
              .anyMatch(NODE_PARENT_FIELD_NAME_VALIDATOR);
        }

        @Test
        @Order(8)
        @DisplayName("8. Node class has 'color' field")
        @SneakyThrows
        void nodeHasColorField() {
            var nodeClass = Class.forName(String.format("%s.%s$%s", PACKAGE_NAME, TEST_CLASS_NAME, NODE_CLASS_NAME));
            var fields = Arrays.stream(nodeClass.getDeclaredFields()).toList();

            assertThat(fields).withFailMessage("Expected %s for color field name", NODE_COLOR_FIELD_NAME_LIST)
              .anyMatch(NODE_COLOR_FIELD_NAME_VALIDATOR);
        }
    }


    @Nested
    @Order(2)
    @DisplayName("2. RB-tree fields tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class RedBlackBinarySearchTreeFieldsTest {

        @Order(1)
        @DisplayName("1. RB-tree has 'root' field")
        @Test
        public void hasRootField() {
            var redBlackTreeClass = RedBlackBinarySearchTree.class;
            var declaredFields = redBlackTreeClass.getDeclaredFields();

            assertThat(declaredFields).anyMatch(f -> f.getName().equals(SIZE_FIELD_NAME));
        }

        @Order(2)
        @DisplayName("2. RB-tree has 'size' field")
        @Test
        public void hasSizeField() {
            var redBlackTreeClass = RedBlackBinarySearchTree.class;
            var declaredFields = redBlackTreeClass.getDeclaredFields();

            assertThat(declaredFields).anyMatch(f -> f.getName().equals(SIZE_FIELD_NAME));
        }
    }

    @Nested
    @Order(3)
    @DisplayName("3. RB-tree insertion method and re-balancing tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class RedBlackBinarySearchTreeInsertTest {

        @Order(1)
        @DisplayName("1. RB-tree returns true when element is inserted")
        @Test
        public void returnsTrueWhenElementIsInserted() {
            var tree = new RedBlackBinarySearchTree<Integer>();
            var isInserted = tree.insert(1);

            assertTrue(isInserted);
        }

        @Order(2)
        @DisplayName("2. RB-tree throws exception when element is null")
        @Test
        public void throwsExceptionWhenElementIsNull() {
            var tree = new RedBlackBinarySearchTree<Integer>();

            assertThatNullPointerException().isThrownBy(() -> tree.insert(null));
        }

        @Order(3)
        @DisplayName("3. RB-tree returns false when duplicate element is inserted")
        @Test
        public void returnsFalseWhenInsertedElementIsDuplication() {
            var tree = new RedBlackBinarySearchTree<Integer>();
            var firstInsert = tree.insert(10);
            var secondInsert = tree.insert(10);

            assertTrue(firstInsert);
            assertFalse(secondInsert);
        }

        @Order(4)
        @DisplayName("4. RB-tree containing elements after insertion")
        @Test
        public void elementsContainingInTreeAfterInsertion() {
            var tree = new RedBlackBinarySearchTree<Integer>();
            int first = 42, second = 24, third = 33;
            tree.insert(first);
            tree.insert(second);
            tree.insert(third);

            assertNotNull(getElementFromTree(tree, first));
            assertNotNull(getElementFromTree(tree, second));
            assertNotNull(getElementFromTree(tree, third));
        }

        @Order(5)
        @DisplayName("5. RB-tree size growing after insertion")
        @Test
        public void sizeIsGrowingAfterInsertion() {
            var tree = new RedBlackBinarySearchTree<Integer>();
            int first = 1, second = 2;
            tree.insert(first);
            tree.insert(second);

            var internalSizeValue = getInternalSizeValue(tree);
            assertThat(internalSizeValue).isEqualTo(2);
        }


        @Order(6)
        @DisplayName("6. After insertion of a new element the recoloring of the nodes occurs during tree re-balancing")
        @Test
        public void recoloringOccurs() {
            //Insert 10 -> 5 -> 15
            int first = 10, second = 5, third = 15, fourth  = 20;
            var integerTree = new RedBlackBinarySearchTree<Integer>();

            integerTree.insert(first);
            integerTree.insert(second);
            integerTree.insert(third);

            //The root is black
            var rootNode = getNodeByElement(integerTree, first);
            assertThat(rootNode.color()).isEqualTo(COLOR_BLACK);

            //Children of the root is RED
            var leftChild = rootNode.left();
            var rightChild = rootNode.right();
            assertThat(leftChild.color()).isEqualTo(COLOR_RED);
            assertThat(rightChild.color()).isEqualTo(COLOR_RED);

            //Inserting new element(20) should invoke recolouring
            //......10(B).......................10(B).........
            //...../.....\...................../....\........
            //....5(R)...15(R)........---->...5(B)...15(B)...
            ///............\............................\....
            //..............20(R).......................20(R)
            integerTree.insert(fourth);
            var insertedNode = getNodeByElement(integerTree, fourth);

            assertThat(rootNode.color()).isEqualTo(COLOR_BLACK);
            assertThat(leftChild.color()).isEqualTo(COLOR_BLACK);
            assertThat(rightChild.color()).isEqualTo(COLOR_BLACK);
            assertThat(insertedNode.color()).isEqualTo(COLOR_RED);
            assertThat(rightChild.right().target()).isEqualTo(insertedNode.target());
        }

        @Order(7)
        @DisplayName("7. After insertion of a new element the tree is balanced through right rotation")
        @Test
        public void elementInsertedAsLeftChildOfParentAndParentIsLeftChildOfGrandParent() {
            int first = 10, second = 5, third = 0;

            var integerTree = new RedBlackBinarySearchTree<Integer>();
            integerTree.insert(first);
            integerTree.insert(second);

            var startingRoot = getNodeByElement(integerTree, first);
            var startingRootLeftChild = getNodeByElement(integerTree, second);
            assertThat(startingRoot.color()).isEqualTo(COLOR_BLACK);
            assertThat(startingRootLeftChild.color()).isEqualTo(COLOR_RED);
            assertThat(startingRoot.left().target()).isEqualTo(startingRootLeftChild.target());
            
            //Inserting new node should invoke right-rotation over a current root node
            //Insertion: 10 -> 5 -> 0
            //Re-balancing through right rotation: Rotate right over the node(10). Recolor node(10) and node(5)
            //......10(B)............5(B)......
            //...../................/....\.....
            //....5(R).......-->...0(R)...10(R)
            ///../.............................
            //.0(R)............................
            integerTree.insert(third);
            var insertedNode = getNodeByElement(integerTree, third);

            //Node(5) should become new root, the newly inserted node will be left child of new root
            // and old root becomes right child of new root
            var newRoot = getInternalRootField(integerTree);
            assertThat(newRoot.target()).isEqualTo(startingRootLeftChild.target());
            assertThat(newRoot.left().target()).isEqualTo(insertedNode.target());
            assertThat(newRoot.right().target()).isEqualTo(startingRoot.target());
            assertThat(newRoot.color()).isEqualTo(COLOR_BLACK);
            assertThat(startingRoot.color()).isEqualTo(COLOR_RED);
            assertThat(insertedNode.color()).isEqualTo(COLOR_RED);
        }


        @Order(8)
        @DisplayName("8. After insertion of a new element the tree is balanced through left-right rotation")
        @Test
        public void elementInsertedAsRightChildOfParentAndParentIsLeftChildOfGrandParent() {
            //Insertion: 10 -> 5 -> 7
            int first = 10, second = 5, third = 7;
            var integerTree = new RedBlackBinarySearchTree<Integer>();

            integerTree.insert(first);
            integerTree.insert(second);

            var startingRoot = getNodeByElement(integerTree, first);
            var startingRootLeftChild = getNodeByElement(integerTree, second);

            //Inserting new node should invoke left-right rotation
            //Re-balancing through left-right rotation: Rotate left over node(5) and then right rotate over node(10)
            //Recolor node(7) and node(10)
            //.....10(B)............7(B)......
            //..../................/....\.....
            //...5(R).......-->...5(R)...10(R)
            //....\...........................
            //.....7(R).......................
            integerTree.insert(third);
            var newNode = getNodeByElement(integerTree, third);

            // Newly inserted node(7) should become new root, the node(10) will be right child of new root
            // and node(5) will be left child of new root
            var newRoot = getInternalRootField(integerTree);
            assertThat(newRoot.target()).isEqualTo(newNode.target());
            assertThat(newRoot.left().target()).isEqualTo(startingRootLeftChild.target());
            assertThat(newRoot.right().target()).isEqualTo(startingRoot.target());
            assertThat(newRoot.color()).isEqualTo(COLOR_BLACK);
            assertThat(startingRootLeftChild.color()).isEqualTo(COLOR_RED);
            assertThat(startingRoot.color()).isEqualTo(COLOR_RED);
        }


        @Order(9)
        @DisplayName("9. After insertion of a new element the tree is balanced through left rotation")
        @Test
        public void elementInsertedAsARightChildOfParentAndParentIsRightChildOfGrandParent() {
            //Insertion: 0 -> 10 -> 20
            int first = 0, second = 10, third = 20;
            var integerTree = new RedBlackBinarySearchTree<Integer>();

            integerTree.insert(first);
            integerTree.insert(second);

            var startingRoot = getNodeByElement(integerTree, first);
            var startingRootRightChild = getNodeByElement(integerTree, second);
            assertThat(startingRoot.color()).isEqualTo(COLOR_BLACK);
            assertThat(startingRootRightChild.color()).isEqualTo(COLOR_RED);
            assertThat(startingRoot.right().target()).isEqualTo(startingRootRightChild.target());

            //Inserting new node should invoke left-rotation
            //Re-balancing through left rotation: Rotate left over the node(0). Recolor node(0) and node(10)
            //.......0(B)...............10(B).....
            //..........\............../....\.....
            //.........10(R).....-->..0(R)...20(R)
            ///...........\.......................
            //............20(R)...................
            integerTree.insert(third);
            var insertedNode = getNodeByElement(integerTree, third);

            //Node(10) should become new root, node(20) will be right child of new root
            // and node(0) becomes left child of new root
            var newRoot = getInternalRootField(integerTree);
            assertThat(newRoot.target()).isEqualTo(startingRootRightChild.target());
            assertThat(newRoot.right().target()).isEqualTo(insertedNode.target());
            assertThat(newRoot.left().target()).isEqualTo(startingRoot.target());
            assertThat(newRoot.color()).isEqualTo(COLOR_BLACK);
            assertThat(startingRoot.color()).isEqualTo(COLOR_RED);
            assertThat(insertedNode.color()).isEqualTo(COLOR_RED);
        }


        @Order(10)
        @DisplayName("10. After insertion of a new element the tree is balanced through right-left rotation")
        @Test
        public void elementInsertedAsLeftChildOfParentAndParentIsRightChildOfGrandParent() {
            //Insertion: 0 -> 10 -> 5
            int first = 0, second = 10, third = 5;
            var integerTree = new RedBlackBinarySearchTree<Integer>();

            integerTree.insert(first);
            integerTree.insert(second);

            var startingRoot = getNodeByElement(integerTree, first);
            var startingRootRightChild = getNodeByElement(integerTree, second);

            //Inserting new node should invoke right-left rotation
            //Re-balancing through right-left rotation: Rotate right over the node(10) ant then rotate left over node(0).
            // Recolor node(0) and node(5)
            //.......0(B)...............5(B)......
            //..........\............../....\.....
            //.........10(R).....-->..0(R)...10(R)
            ///......./...........................
            //......5(R)..........................
            integerTree.insert(third);
            var newNode = getNodeByElement(integerTree, third);

            // Newly inserted node(5) should become new root, the node(10) will be right child of new root
            // and node(0) will be left child of new root
            var newRoot = getInternalRootField(integerTree);
            assertThat(newRoot.target()).isEqualTo(newNode.target());
            assertThat(newRoot.right().target()).isEqualTo(startingRootRightChild.target());
            assertThat(newRoot.left().target()).isEqualTo(startingRoot.target());
            assertThat(newRoot.color()).isEqualTo(COLOR_BLACK);
            assertThat(startingRootRightChild.color()).isEqualTo(COLOR_RED);
            assertThat(startingRoot.color()).isEqualTo(COLOR_RED);
        }


        @Order(11)
        @DisplayName("11. Recoloring is invoking re-balance on the top recoloured node")
        @Test
        public void rotationOccursAfterRecolouring() {
            //Insertion -> 5, 10, 17, 15, 25, 20, 28
            var startingValues = List.of(5, 10, 17, 15, 25, 20, 28);
            int newValue = 30;
            var integerTree = new RedBlackBinarySearchTree<Integer>();
            for (var value:
                 startingValues) {
                integerTree.insert(value);
            }

            //Insertion of new node(30) should invoke re-coloring and then rotation:
            //...............10(B)..................................................17(B).........................
            //............../.....\.............................................../........\......................
            //............./.......\............................................./......... \.....................
            //...........5(B).....17(R)........................................10(R)....... 25(R).................
            //.................../.....\...............------->............../......\......./....\................
            //................../.......\.................................../........\...../......\...............
            //................15(B).....25(B)..............................5(B)....15(B)..20(B)...28(B)...........
            //........................./.....\......................................................\.............
            //......................../.......\......................................................\............
            //.......................20(R)...28(R)...................................................30(R)........
            //..................................\.................................................................
            //...................................\................................................................
            //...................................30(R)............................................................
            integerTree.insert(newValue);
            var node30 = getNodeByElement(integerTree, newValue);
            var newRoot = getInternalRootField(integerTree);
            var node17 = getNodeByElement(integerTree, 17);
            var node10 = getNodeByElement(integerTree, 10);
            var node25 = getNodeByElement(integerTree, 25);
            var node15 = getNodeByElement(integerTree, 15);
            var node28 = getNodeByElement(integerTree, 28);

            assertThat(newRoot.target()).isEqualTo(node17.target());
            assertThat(newRoot.left().target()).isEqualTo(node10.target());
            assertThat(newRoot.right().target()).isEqualTo(node25.target());
            assertThat(node10.right().target()).isEqualTo(node15.target());
            assertThat(node30.parent().target()).isEqualTo(node28.target());
            assertThat(newRoot.color()).isEqualTo(COLOR_BLACK);
            assertThat(node10.color()).isEqualTo(COLOR_RED);
            assertThat(node25.color()).isEqualTo(COLOR_RED);
            assertThat(node15.color()).isEqualTo(COLOR_BLACK);
            assertThat(node28.color()).isEqualTo(COLOR_BLACK);
        }

        
        @Order(12)
        @DisplayName("12. RB-tree root is always black for different inputs")
        @ParameterizedTest
        @ArgumentsSource(RbTreeArgumentProvider.class)
        public <T extends Comparable<T>> void rootIsAlwaysBlack(List<T> elementsList) {
            var tree = fillTree(elementsList);
            NodeProxy root = getInternalRootField(tree);
            var color = root.color();

            assertThat(color).isEqualTo(COLOR_BLACK);
        }

        @Order(13)
        @DisplayName("13. RB-tree has only RED or BLACK nodes for different inputs")
        @ParameterizedTest
        @ArgumentsSource(RbTreeArgumentProvider.class)
        public <T extends Comparable<T>> void allNodesAreEitherRedOrBlack(List<T> elementsList) {
            var tree = fillTree(elementsList);
            NodeProxy root = getInternalRootField(tree);

            Condition<String> colorCondition =
              new Condition<>(s -> s.equalsIgnoreCase(COLOR_BLACK) || s.equalsIgnoreCase(COLOR_RED),
                "Color condition");

            assertNodeColors(root, colorCondition);
        }

        @Order(14)
        @DisplayName("14. RB-tree RED nodes doesn't have RED children for different inputs")
        @ParameterizedTest
        @ArgumentsSource(RbTreeArgumentProvider.class)
        public <T extends Comparable<T>> void redNodesDoesntHaveRedChildren(List<T> elementsList) {
            var tree = fillTree(elementsList);
            NodeProxy root = getInternalRootField(tree);

            Predicate<NodeProxy> assertionPredicate = n -> n.color().equals(COLOR_BLACK) || (n.left() == null ||
              n.left().color().equals(COLOR_BLACK) &&
                (n.right() == null || n.right().color().equals(COLOR_BLACK)));

            traverseTreeAndAssertThatRedNodesDoesntHaveRedChildren(root, assertionPredicate);
        }

        @Order(15)
        @DisplayName("15. RB-tree each paths from a node to any of it's descending leaves has the same number " +
          "of black nodes for different inputs")
        @SneakyThrows
        @ParameterizedTest
        @ArgumentsSource(RbTreeArgumentProvider.class)
        public <T extends Comparable<T>> void allPathsFromRootToLeavesContainsTheSameNumberOfBlackNodes(List<T> elementsList) {
            var tree = fillTree(elementsList);
            NodeProxy root = getInternalRootField(tree);

            var nodeToDepthPairList = new ArrayList<Pair<NodeProxy, Integer>>();
            countDepthForEachLeaf(root, nodeToDepthPairList, 0);
            var allLeavesDepth = nodeToDepthPairList.stream().map(Pair::getValue).toList();

            assertThat(allLeavesDepth).containsOnly(allLeavesDepth.get(0));
        }

        private void assertNodeColors(NodeProxy iterator, Condition<String> colorCondition) {
            if (iterator != null) {
                assertNodeColors(iterator.left(), colorCondition);

                assertNotNull(iterator.color());
                assertThat((String) iterator.color()).is(colorCondition);

                assertNodeColors(iterator.right(), colorCondition);
            }
        }

        private void traverseTreeAndAssertThatRedNodesDoesntHaveRedChildren(NodeProxy root, Predicate<NodeProxy> assertionPredicate) {
            if (root != null) {
                traverseTreeAndAssertThatRedNodesDoesntHaveRedChildren(root.left(), assertionPredicate);

                assertThat(assertionPredicate).overridingErrorMessage(
                  String.format("Violation of RB-tree properties: The next RED node {%s} has at least one RED child",
                    root)).accepts(root);

                traverseTreeAndAssertThatRedNodesDoesntHaveRedChildren(root.right(), assertionPredicate);
            }
        }

        private void countDepthForEachLeaf(NodeProxy node, List<Pair<NodeProxy, Integer>> nodeToDepthCounter,
                                           int depthCounter) {
            if (node != null) {
                if (node.color().equals(COLOR_BLACK)) {
                    ++depthCounter;
                }
                countDepthForEachLeaf(node.left(), nodeToDepthCounter, depthCounter);

                if (node.left() == null || node.right() == null) {
                    nodeToDepthCounter.add(Pair.of(node, depthCounter));
                }

                countDepthForEachLeaf(node.right(), nodeToDepthCounter, depthCounter);
            }
        }

    }

    @Nested
    @Order(4)
    @DisplayName("4. RB-tree other methods tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class RedBlackBinarySearchTreeSizeTest {

        private static RedBlackBinarySearchTree<String> stringTree = new RedBlackBinarySearchTree<>();

        @BeforeAll
        public static void prepareTree() {
            stringTree.insert("Jon");
            stringTree.insert("Bon");
            stringTree.insert("Jovi");
        }

        @Order(1)
        @DisplayName("1. size() method. RB-tree returns size == 0 when tree is empty")
        @Test
        public void returnsZeroSizeWhenTreeIsEmpty() {
            var tree = new RedBlackBinarySearchTree<Integer>();
            var size = tree.size();

            assertThat(size).isEqualTo(0);
        }

        @Order(2)
        @DisplayName("2. size() method. RB-tree returns proper size after insertion")
        @Test
        public void returnsProperSizeAfterInsertion() {
            var tree = new RedBlackBinarySearchTree<String>();
            tree.insert("R2-D2");
            tree.insert("C-3PO");
            tree.insert("Chewie");
            tree.insert("Obi Wan");

            var size = tree.size();

            assertThat(size).isEqualTo(4);
        }


        @Order(3)
        @DisplayName("3. contains() method. RB-tree returns true when element is present")
        @Test
        public void returnsTrueWhenElementIsPresent() {
            assertTrue(stringTree.contains("Bon"));
        }

        @Order(4)
        @DisplayName("4. contains() method. RB-tree returns false when element is not present")
        @Test
        public void returnsFalseWhenElementIsNotPresent() {
            assertFalse(stringTree.contains("Betty"));
        }

        @Order(5)
        @DisplayName("5. contains() method. RB-tree throws exception when searching element is null")
        @Test
        public void throwsExceptionWhenElementIsNull() {
            assertThatNullPointerException().isThrownBy(() -> stringTree.contains(null));
        }

        @Order(6)
        @DisplayName("6. of() method. RB-tree of() method creates new tree with proper size")
        @Test
        public void ofMethodCreatesTreeWithProperSize() {
            var stringTree = RedBlackBinarySearchTree.of("Tinky-Winky", "Dipsy", "Laa Laa", "Po");

            assertThat(stringTree.size()).isEqualTo(4);

        }

        @Order(7)
        @DisplayName("7. depth() method. RB-tree returns depth == 0 when tree is empty")
        @Test
        public void returnsZeroDepthWhenTreeIsEmpty() {
            var localTimeRedBlackTree = new RedBlackBinarySearchTree<LocalTime>();

            assertThat(localTimeRedBlackTree.depth()).isEqualTo(0);
        }

        @Order(8)
        @DisplayName("8. depth() method. RB-tree depth tests")
        @ParameterizedTest
        @MethodSource("depthTestArguments")
        public void returnsProperDepthWithReBalanceAfterInsertion(int depth, Integer... elements) {
            var rbTree = RedBlackBinarySearchTree.of(elements);

            assertThat(rbTree.depth()).isEqualTo(depth);
        }

        @Order(9)
        @DisplayName("9. inOrderTraversal() method. RB-tree has correct storing order")
        @SneakyThrows
        @ParameterizedTest
        @ArgumentsSource(RbTreeArgumentProvider.class)
        public <T extends Comparable<T>> void hasCorrectStoredOrder(List<T> elementsList) {
            var tree = fillTree(elementsList);
            var container = new CopyOnWriteArrayList<T>();
            Consumer<T> consumer = container::add;
            tree.inOrderTraversal(consumer);
            MatcherAssert.assertThat(container, isInAscendingOrder());
        }

        private <T extends Comparable<T>> Matcher<List<T>> isInAscendingOrder() {
            return new TypeSafeMatcher<>() {
                @Override
                protected boolean matchesSafely(List<T> comparables) {
                    for (int i = 0; i < comparables.size() - 1; i++) {
                        if (comparables.get(i).compareTo(comparables.get(i + 1)) >= 0) {
                            return false;
                        }
                    }
                    return true;
                }

                @Override
                public void describeTo(Description description) {
                    description.appendText("The order is not correct. Elements should appear in ascending order");
                }
            };
        }


        public static Stream<Arguments> depthTestArguments() {
            return Stream.of(Arguments.of(0, new Integer[] {10}), Arguments.of(1, new Integer[] {1, 2, 3}),
              Arguments.of(2, new Integer[] {10, 20, 40, 50}),
              Arguments.of(3, new Integer[] {-11, 7, 15, 2, 4, 900, 77, 345, 789, 1000}));
        }

    }

    public static final String NODE_CLASS_NAME = "Node";
    public static final String COLOR_CLASS_NAME = "Color";
    public static final String PACKAGE_NAME = "com.bobocode.cs";
    public static final String TEST_CLASS_NAME = "RedBlackBinarySearchTree";
    public static final String SIZE_FIELD_NAME = "size";
    public static final String ROOT_FIELD_NAME = "root";
    public static final List<String> NODE_PARENT_FIELD_NAME_LIST = List.of("parent");
    public static final List<String> NODE_COLOR_FIELD_NAME_LIST = List.of("color");
    public static final List<String> NODE_LEFT_FIELD_NAME_LIST = List.of("left", "leftChild");
    public static final List<String> NODE_RIGHT_FIELD_NAME_LIST = List.of("right", "rightChild");
    public static final List<String> NODE_ELEMENT_FIELD_NAME_LIST = List.of("element", "data", "value", "key");
    public static final Predicate<Field> NODE_PARENT_FIELD_NAME_VALIDATOR = f -> NODE_PARENT_FIELD_NAME_LIST.contains(f.getName());
    public static final Predicate<Field> NODE_LEFT_FIELD_NAME_VALIDATOR = f -> NODE_LEFT_FIELD_NAME_LIST.contains(f.getName());
    public static final Predicate<Field> NODE_RIGHT_FIELD_NAME_VALIDATOR = f -> NODE_RIGHT_FIELD_NAME_LIST.contains(f.getName());
    public static final Predicate<Field> NODE_ELEMENT_FIELD_NAME_VALIDATOR = f -> NODE_ELEMENT_FIELD_NAME_LIST.contains(f.getName());
    public static final Predicate<Field> NODE_COLOR_FIELD_NAME_VALIDATOR = f -> NODE_COLOR_FIELD_NAME_LIST.contains(f.getName());
    public static final String COLOR_RED = "RED";
    public static final String COLOR_BLACK = "BLACK";


    private static class ColorProxy {

        private Class<?> targetClass;
        private Object target;
        private Method method;

        @SneakyThrows
        public ColorProxy(Object target) {
            this.targetClass = Class.forName(String.format("%s.%s$%s", PACKAGE_NAME, TEST_CLASS_NAME, COLOR_CLASS_NAME));
            this.target = target;
            this.method = targetClass.getMethod("name");
        }

        @SneakyThrows
        public Object getColor() {
            return method.invoke(target);
        }



    }
    private static class NodeProxy {
        private Class<?> targetClass;

        private Object target;
        private Field elementField;
        private Field parentField;
        private Field colorField;
        private Field leftField;
        private Field rightField;
        @SneakyThrows
        public NodeProxy(Object target) {
            Objects.requireNonNull(target);
            this.targetClass = Class.forName(String.format("%s.%s$%s", PACKAGE_NAME, TEST_CLASS_NAME, NODE_CLASS_NAME));
            this.target = targetClass.cast(target);
            this.elementField = ReflectionUtils.getAccessibleFieldByPredicate(targetClass, NODE_ELEMENT_FIELD_NAME_VALIDATOR);
            this.parentField = ReflectionUtils.getAccessibleFieldByPredicate(targetClass, NODE_PARENT_FIELD_NAME_VALIDATOR);
            this.leftField = ReflectionUtils.getAccessibleFieldByPredicate(targetClass, NODE_LEFT_FIELD_NAME_VALIDATOR);
            this.rightField = ReflectionUtils.getAccessibleFieldByPredicate(targetClass, NODE_RIGHT_FIELD_NAME_VALIDATOR);
            this.colorField = ReflectionUtils.getAccessibleFieldByPredicate(targetClass, NODE_COLOR_FIELD_NAME_VALIDATOR);
        }
        @SneakyThrows
        public Object element() {
            return elementField.get(target);
        }

        @SneakyThrows
        public Object color() {
            return new ColorProxy(colorField.get(target)).getColor();
        }

        @SneakyThrows
        public NodeProxy parent() {
            return Optional.ofNullable(parentField.get(target))
              .map(NodeProxy::new)
              .orElse(null);
        }

        @SneakyThrows
        public NodeProxy left() {
            return Optional.ofNullable(leftField.get(target))
              .map(NodeProxy::new)
              .orElse(null);
        }

        @SneakyThrows
        public NodeProxy right() {
            return Optional.ofNullable(rightField.get(target))
              .map(NodeProxy::new)
              .orElse(null);
        }

        public Object target() {
            return target;
        }


    }
    @SneakyThrows
    private NodeProxy getInternalRootField(RedBlackBinarySearchTree<?> tree) {
        var node = ReflectionUtils.getAccessibleFieldByPredicate(tree.getClass(), f -> f.getName().equals(ROOT_FIELD_NAME)).get(tree);
        return new NodeProxy(node);
    }

    @SneakyThrows
    private int getInternalSizeValue(RedBlackBinarySearchTree<?> tree) {
        return (int) ReflectionUtils.getAccessibleFieldByPredicate(tree.getClass(), f -> f.getName().equals(SIZE_FIELD_NAME)).get(tree);
    }

    private <T extends Comparable<T>> NodeProxy getNodeByElement(RedBlackBinarySearchTree<?> tree, T element) {
        var node = getInternalRootField(tree);
        while (node != null) {
            if (node.element().equals(element)) {
                return node;
            } else if (((Comparable<T>) node.element()).compareTo(element) < 0) {
                node = node.right();
            } else {
                node = node.left();
            }
        }
        return null;
    }

    private <T extends Comparable<T>> T getElementFromTree(RedBlackBinarySearchTree<?> tree, T element) {
        var internalRootField = getInternalRootField(tree);
        return findElementInTree(internalRootField, element);
    }

    private <T extends Comparable<T>> T findElementInTree(NodeProxy node, T element) {
        while (node != null) {
            if (node.element().equals(element)) {
                return element;
            } else if (((Comparable<T>) node.element()).compareTo(element) < 0) {
                node = node.right();
            } else {
                node = node.left();
            }
        }
        return null;
    }

    private static <T extends Comparable<T>> RedBlackBinarySearchTree<T> fillTree(List<T> elementList) {
        var tree = new RedBlackBinarySearchTree<T>();
        for (var element:
          elementList) {
            tree.insert(element);
        }
        return tree;
    }

    static class RbTreeArgumentProvider implements ArgumentsProvider {


        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(Arguments.of(List.of(1, 2, 3)), Arguments.of(
                List.of("Twilight Sparkle", "Fluttershy", "Pinkie Pie", "Applejack", "Rainbow Dash",
                  "Princess Luna", "Sunset Shimmer", "Wonderbolts", "Firecracker Burst", "Princess Celestia")),
              Arguments.of(List.of(17, 23, 36, 10, 5, 18, 44, 7, 0, 64, 3, 99)),
              Arguments.of(List.of(7.62, 5.56, 12.7, 152d, 11.43, 9.0, 155d, 5.45, 75d, 80d)),
              Arguments.of(
                List.of(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.TUESDAY, DayOfWeek.SATURDAY,
                  DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.SUNDAY)), Arguments.of(
                IntStream.iterate(-10_000, i -> i + 1).limit(20_000).boxed().toList()));
        }
    }
}