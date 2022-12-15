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
import org.junit.platform.engine.support.hierarchical.Node;

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
        @DisplayName("Color is a enum")
        @SneakyThrows
        void colorIsEnum() {
            var colorClass = Class.forName(String.format("%s.%s$%s", PACKAGE_NAME, TEST_CLASS_NAME, COLOR_CLASS_NAME));

            assertTrue(colorClass.isEnum());
        }

        @Test
        @Order(2)
        @DisplayName("Color has two values 'RED' and 'BLACK'")
        @SneakyThrows
        void colorHasTwoValues() {
            var colorClass = Class.forName(String.format("%s.%s$%s", PACKAGE_NAME, TEST_CLASS_NAME, COLOR_CLASS_NAME));
            var declaredFields = colorClass.getDeclaredFields();

            assertThat(declaredFields).hasSize(3);
            assertThat(declaredFields).anyMatch(f -> f.getName().equals("RED"))
              .anyMatch(f -> f.getName().equals("BLACK"));
        }

        @Test
        @Order(3)
        @DisplayName("Node is a static nested class")
        @SneakyThrows
        void nodeIsStaticClass() {
            var nodeClass = Class.forName(String.format("%s.%s$%s", PACKAGE_NAME, TEST_CLASS_NAME, NODE_CLASS_NAME));

            assertTrue(isStatic(nodeClass.getModifiers()));
        }

        @Test
        @Order(4)
        @DisplayName("Node class has one type parameter")
        @SneakyThrows
        void nodeHasOneTypeParameter() {
            var nodeClass = Class.forName(String.format("%s.%s$%s", PACKAGE_NAME, TEST_CLASS_NAME, NODE_CLASS_NAME));
            var typeParametersList = nodeClass.getTypeParameters();

            assertThat(typeParametersList).hasSize(1);
        }

        @Test
        @Order(5)
        @DisplayName("Node class has 'element', 'left','right', 'parent', 'color' fields")
        @SneakyThrows
        void nodeHasLeftAndRightFields() {
            var nodeClass = Class.forName(String.format("%s.%s$%s", PACKAGE_NAME, TEST_CLASS_NAME, NODE_CLASS_NAME));
            var fieldNames = Arrays.stream(nodeClass.getDeclaredFields()).map(Field::getName).toList();

            assertThat(fieldNames).contains(NODE_ELEMENT_FIELD_NAME, NODE_RIGHT_FIELD_NAME, NODE_LEFT_FIELD_NAME,
              NODE_COLOR_FIELD_NAME, NODE_PARENT_FIELD_NAME);
        }
    }


    @Nested
    @Order(2)
    @DisplayName("2. RB-tree fields tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class RedBlackBinarySearchTreeFieldsTest {

        @Order(1)
        @DisplayName("1. RB-tree has 'root' and 'size' fields")
        @Test
        public void hasRootAndSizeFields() {
            var redBlackTreeClass = RedBlackBinarySearchTree.class;
            var declaredFields = redBlackTreeClass.getDeclaredFields();

            assertThat(declaredFields).anyMatch(f -> f.getName().equals(SIZE_FIELD_NAME))
              .anyMatch(f -> f.getName().equals(ROOT_FIELD_NAME));
        }
    }

    @Nested
    @Order(3)
    @DisplayName("3. RB-tree insertion() method tests")
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
        @DisplayName("3. RB-tree returns false when duplicate element is inserted and size not growing")
        @Test
        public void returnsFalseWhenInsertedElementIsDuplication() {
            var tree = new RedBlackBinarySearchTree<Integer>();
            var firstInsert = tree.insert(10);
            var secondInsert = tree.insert(10);

            assertTrue(firstInsert);
            assertFalse(secondInsert);
            assertThat(tree.size()).isEqualTo(1);
        }

        @Order(4)
        @DisplayName("4. RB-tree size is growing after insertion")
        @Test
        public void returnsProperSizeAfterElementsAreInserted() {
            var tree = new RedBlackBinarySearchTree<Integer>();
            tree.insert(42);
            tree.insert(24);
            tree.insert(33);
            var internalSizeValue = getInternalSizeValue(tree);

            assertThat(internalSizeValue).isEqualTo(3);
        }
    }

    @Nested
    @Order(4)
    @DisplayName("4. RB-tree size() method tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class RedBlackBinarySearchTreeSizeTest {

        @Order(1)
        @DisplayName("1. RB-tree returns size == 0 when tree is empty")
        @Test
        public void returnsZeroSizeWhenTreeIsEmpty() {
            var tree = new RedBlackBinarySearchTree<Integer>();
            var size = tree.size();

            assertThat(size).isEqualTo(0);
        }

        @Order(2)
        @DisplayName("2. RB-tree returns proper size after insertion")
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
    }

    @Nested
    @Order(5)
    @DisplayName("5. RB-tree contains() method tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class RedBlackBinarySearchTreeContainsTest {

        private static RedBlackBinarySearchTree<String> stringTree = new RedBlackBinarySearchTree<>();

        @BeforeAll
        public static void prepareTree() {
            stringTree.insert("Jon");
            stringTree.insert("Bon");
            stringTree.insert("Jovi");
        }


        @Order(1)
        @DisplayName("1. RB-tree returns true when element is present")
        @Test
        public void returnsTrueWhenElementIsPresent() {
            assertTrue(stringTree.contains("Bon"));
        }

        @Order(2)
        @DisplayName("2. RB-tree returns false when element is not present")
        @Test
        public void returnsFalseWhenElementIsNotPresent() {
            assertFalse(stringTree.contains("Betty"));
        }

        @Order(3)
        @DisplayName("3. RB-tree throws exception when searching element is null")
        @Test
        public void throwsExceptionWhenElementIsNull() {
            assertThatNullPointerException().isThrownBy(() -> stringTree.contains(null));
        }
    }

    @Nested
    @Order(6)
    @DisplayName("6. RB-tree static of() method tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class RedBlackBinarySearchTreeOfTest {

        @Order(1)
        @DisplayName("1. RB-tree of method inserts elements and size is growing")
        @Test
        public void returnsTrueWhenElementIsPresent() {
            var stringTree = RedBlackBinarySearchTree.of("Tinky-Winky", "Dipsy", "Laa Laa", "Po");

            assertThat(stringTree.size()).isEqualTo(4);
        }

    }

    @Nested
    @Order(7)
    @DisplayName("7. RB-tree depth() method tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class RedBlackBinarySearchTreeDepthTest {

        @Order(1)
        @DisplayName("1. RB-tree returns depth = 0 when tree is empty")
        @Test
        public void returnsZeroDepthWhenTreeIsEmpty() {
            var localTimeRedBlackTree = new RedBlackBinarySearchTree<LocalTime>();

            assertThat(localTimeRedBlackTree.depth()).isEqualTo(0);
        }

        @Order(2)
        @DisplayName("2. RB-tree depth tests")
        @ParameterizedTest
        @MethodSource("depthTestArguments")
        public void returnsProperDepthWithReBalanceAfterInsertion(int depth, Integer... elements) {
            var rbTree = RedBlackBinarySearchTree.of(elements);

            assertThat(rbTree.depth()).isEqualTo(depth);
        }

        public static Stream<Arguments> depthTestArguments() {
            return Stream.of(Arguments.of(0, new Integer[] {10}), Arguments.of(1, new Integer[] {1, 2, 3}),
              Arguments.of(2, new Integer[] {10, 20, 40, 50}),
              Arguments.of(3, new Integer[] {-11, 7, 15, 2, 4, 900, 77, 345, 789, 1000}));
        }

    }

    @Nested
    @Order(8)
    @DisplayName("8. RB-tree properties tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class RedBlackBinarySearchTreePropertiesTest {

        @Order(1)
        @DisplayName("1. RB-tree root is always black")
        @ParameterizedTest
        @ArgumentsSource(RbTreeArgumentProvider.class)
        public void rootIsAlwaysBlack(RedBlackBinarySearchTree<?> tree) {
            NodeProxy root = getInternalRootField(tree);
            var color = root.color();

            assertThat(color).isEqualTo(COLOR_NAME_BLACK);
        }

        @Order(2)
        @DisplayName("2. RB-tree has either RED or BLACK nodes")
        @ParameterizedTest
        @ArgumentsSource(RbTreeArgumentProvider.class)
        public void allNodesAreEitherRedOrBlack(RedBlackBinarySearchTree<?> tree) {
            NodeProxy root = getInternalRootField(tree);

            assertNodeColors(root);
        }

        private void assertNodeColors(NodeProxy iterator) {
            if (iterator != null) {
                assertNodeColors(iterator.left());

                assertNotNull(iterator.color());
                Condition<String> colorCondition =
                  new Condition<>(s -> s.equalsIgnoreCase(COLOR_NAME_BLACK) || s.equalsIgnoreCase(COLOR_NAME_RED),
                    "Color condition");
                assertThat((String) iterator.color()).is(colorCondition);

                assertNodeColors(iterator.right());
            }
        }

        @Order(3)
        @DisplayName("3. RB-tree RED nodes doesn't have RED children")
        @ParameterizedTest
        @ArgumentsSource(RbTreeArgumentProvider.class)
        public void redNodesDoesntHaveRedChildren(RedBlackBinarySearchTree<?> tree) {
            NodeProxy root = getInternalRootField(tree);
            Predicate<NodeProxy> assertionPredicate = n -> n.color().equals(COLOR_NAME_BLACK) || (n.left() == null ||
              n.left().color().equals(COLOR_NAME_BLACK) &&
                (n.right() == null || n.right().color().equals(COLOR_NAME_BLACK)));

            traverseTreeAndAssertThatRedNodesDoesntHaveRedChildren(root, assertionPredicate);
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

        @Order(4)
        @DisplayName("4. RB-tree each paths from a node to any of it's descending leaves has the same number of black nodes.")
        @SneakyThrows
        @ParameterizedTest
        @ArgumentsSource(RbTreeArgumentProvider.class)
        public void allPathsFromRootToLeavesContainsTheSameNumberOfBlackNodes(RedBlackBinarySearchTree<?> tree) {
            NodeProxy root = getInternalRootField(tree);
            var nodeToDepthPairList = new ArrayList<Pair<NodeProxy, Integer>>();
            countDepthForEachLeaf(root, nodeToDepthPairList, 0);
            var allLeavesDepth = nodeToDepthPairList.stream().map(Pair::getValue).toList();

            assertThat(allLeavesDepth).containsOnly(allLeavesDepth.get(0));
        }

        private void countDepthForEachLeaf(NodeProxy node, List<Pair<NodeProxy, Integer>> nodeToDepthCounter,
                                           int depthCounter) {
            if (node != null) {
                if (node.color().equals(COLOR_NAME_BLACK)) {
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
    @Order(9)
    @DisplayName("9. RB-tree inOrderTraversal() method tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class RedBlackBinarySearchTreeInOrderTraversalTest {

        @Order(1)
        @DisplayName("1. RB-tree has correct storing order")
        @SneakyThrows
        @ParameterizedTest
        @ArgumentsSource(RbTreeArgumentProvider.class)
        public <T extends Comparable<T>> void hasCorrectStoredOrder(RedBlackBinarySearchTree<T> tree) {
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
    }

    public static final String NODE_CLASS_NAME = "Node";
    public static final String COLOR_CLASS_NAME = "Color";
    public static final String PACKAGE_NAME = "com.bobocode.cs";
    public static final String TEST_CLASS_NAME = "RedBlackBinarySearchTree";
    public static final String SIZE_FIELD_NAME = "size";
    public static final String ROOT_FIELD_NAME = "root";
    public static final String NODE_LEFT_FIELD_NAME = "left";
    public static final String NODE_RIGHT_FIELD_NAME = "right";
    public static final String NODE_PARENT_FIELD_NAME = "parent";
    public static final String NODE_ELEMENT_FIELD_NAME = "element";
    public static final String NODE_COLOR_FIELD_NAME = "color";
    public static final String COLOR_NAME_RED = "RED";
    public static final String COLOR_NAME_BLACK = "BLACK";

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
        private ColorProxy colorProxy;
        private Field colorField;
        private Field leftField;
        private Field rightField;

        @SneakyThrows
        public NodeProxy(Object target) {
            Objects.requireNonNull(target);
            this.targetClass = Class.forName(String.format("%s.%s$%s", PACKAGE_NAME, TEST_CLASS_NAME, NODE_CLASS_NAME));
            this.target = target;
            this.elementField = targetClass.getDeclaredField(NODE_ELEMENT_FIELD_NAME);
            this.elementField.setAccessible(true);
            this.parentField = targetClass.getDeclaredField(NODE_PARENT_FIELD_NAME);
            this.parentField.setAccessible(true);
            this.colorField = targetClass.getDeclaredField(NODE_COLOR_FIELD_NAME);
            this.colorField.setAccessible(true);
            this.colorProxy = new ColorProxy(colorField.get(target));
            this.leftField = targetClass.getDeclaredField(NODE_LEFT_FIELD_NAME);
            this.leftField.setAccessible(true);
            this.rightField = targetClass.getDeclaredField(NODE_RIGHT_FIELD_NAME);
            this.rightField.setAccessible(true);
        }

        @SneakyThrows
        public Object element() {
            return elementField.get(target);
        }

        @SneakyThrows
        public Object color() {
            return colorProxy.getColor();
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
    }

    @SneakyThrows
    private NodeProxy getInternalRootField(RedBlackBinarySearchTree<?> tree) {
        var node = getAccessibleFieldByPredicate(tree, f -> f.getName().equals(ROOT_FIELD_NAME)).get(tree);
        return new NodeProxy(node);
    }

    @SneakyThrows
    private int getInternalSizeValue(RedBlackBinarySearchTree<?> tree) {
        return (int) getAccessibleFieldByPredicate(tree, f -> f.getName().equals(SIZE_FIELD_NAME)).get(tree);
    }


    private Field getAccessibleFieldByPredicate(Object object, Predicate<Field> predicate) {
        Field field = Arrays.stream(object.getClass().getDeclaredFields())
          .filter(predicate)
          .findAny()
          .orElseThrow();
        field.setAccessible(true);
        return field;
    }

    static class RbTreeArgumentProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(Arguments.of(RedBlackBinarySearchTree.of(1, 2, 3)), Arguments.of(
                RedBlackBinarySearchTree.of("Twilight Sparkle", "Fluttershy", "Pinkie Pie", "Applejack", "Rainbow Dash",
                  "Princess Luna", "Sunset Shimmer", "Wonderbolts", "Firecracker Burst", "Princess Celestia")),
              Arguments.of(RedBlackBinarySearchTree.of(17, 23, 36, 10, 5, 18, 44, 7, 0, 64, 3, 99)),
              Arguments.of(RedBlackBinarySearchTree.of(7.62, 5.56, 12.7, 152d, 11.43, 9.0, 155d, 5.45, 75d, 80d)),
              Arguments.of(
                RedBlackBinarySearchTree.of(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.TUESDAY, DayOfWeek.SATURDAY,
                  DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.SUNDAY)), Arguments.of(RedBlackBinarySearchTree.of(
                IntStream.iterate(-10_000, i -> i + 1).limit(20_000).boxed().toArray(n -> new Integer[20_000]))));
        }
    }
}