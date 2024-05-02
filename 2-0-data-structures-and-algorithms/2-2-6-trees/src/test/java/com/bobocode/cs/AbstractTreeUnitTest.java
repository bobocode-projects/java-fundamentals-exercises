package com.bobocode.cs;

import lombok.SneakyThrows;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class AbstractTreeUnitTest {

    protected static final Integer[] someElements = {10, 9, 11, 8, 12, 7};

    protected static final Predicate<Field> SIZE_FIELD = field ->
            field.getName().toLowerCase().contains("size") || field.getName().toLowerCase().contains("length");

    protected static final Predicate<Field> NODE_FIELD = field ->
            field.getType().getSimpleName().equals("Node");

    protected static final Predicate<Field> ELEMENT_FIELD = field -> field.getName().toLowerCase().contains("value");

    protected static final Predicate<Field> LEFT_FIELD = field ->
            field.getName().toLowerCase().contains("left")
                    && field.getType().getSimpleName().equals("Node");

    protected static final Predicate<Field> RIGHT_FIELD = field ->
            field.getName().toLowerCase().contains("right")
                    && field.getType().getSimpleName().equals("Node");

    protected static Class<?> getInnerClass(Class<?> treeClazz) {
        return Arrays.stream(treeClazz.getDeclaredClasses())
                .filter(Class::isMemberClass)
                .findAny()
                .orElseThrow();
    }

    @SneakyThrows
    protected int getInnerSize(Tree<?> tree) {
        return (int) getInnerSizeField(tree.getClass()).get(tree);
    }

    protected Field getInnerSizeField(Class<?> treeClazz) {
        Field sizeField = Arrays.stream(treeClazz.getDeclaredFields())
                .filter(SIZE_FIELD)
                .findAny()
                .orElseThrow();
        sizeField.setAccessible(true);
        return sizeField;
    }

    protected void checkTreeNode(Class<?> treeClazz) {
        Class<?> innerClass = getInnerClass(treeClazz);
        String name = innerClass.getSimpleName();

        assertThat(name).isEqualTo("Node");
    }

    protected void checkTreeFieldsCheck(Class<?> treeClazz) {
        boolean hasSizeField = Arrays.stream(treeClazz.getDeclaredFields())
                .anyMatch(SIZE_FIELD);

        boolean hasNodeField = Arrays.stream(treeClazz.getDeclaredFields())
                .anyMatch(NODE_FIELD);

        assertThat(hasSizeField).isTrue();
        assertThat(hasNodeField).isTrue();
    }

    protected void commonNodeFieldsCheck(Class<?> treeClazz) {
        Class<?> innerClass = getInnerClass(treeClazz);

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

    protected void testOfMethod(Tree<Integer> tree) {
        for (var e : AbstractTreeUnitTest.someElements) {
            assertThat(contains(getRootObject(tree), e)).isTrue();
        }
        assertThat(getInnerSize(tree)).isEqualTo(AbstractTreeUnitTest.someElements.length);
    }

    protected void testInsertMethod(Tree<Integer> tree) {
        for (Integer e : AbstractTreeUnitTest.someElements) {
            assertThat(contains(getRootObject(tree), e)).isFalse(); //does not contain
            assertThat(tree.insert(e)).isTrue(); //do insert
            assertThat(contains(getRootObject(tree), e)).isTrue(); //and contains
        }
    }

    protected Object findNodeByElement(Object node, int element) {
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
    protected Object getRootObject(Tree<?> tree) {
        Field nodeField = Arrays.stream(tree.getClass().getDeclaredFields())
                .filter(NODE_FIELD)
                .findAny()
                .orElseThrow();
        nodeField.setAccessible(true);
        return nodeField.get(tree);
    }

    @SneakyThrows
    protected boolean contains(Object node, int element) {
        return findNodeByElement(node, element) != null;
    }

    @SneakyThrows
    protected int getElement(Object node) {
        return (int) getNodesField(node, ELEMENT_FIELD).get(node);
    }

    @SneakyThrows
    protected Object getLeftNode(Object node) {
        return getNodesField(node, LEFT_FIELD).get(node);
    }

    @SneakyThrows
    protected Object getRightNode(Object node) {
        return getNodesField(node, RIGHT_FIELD).get(node);
    }

    @SneakyThrows
    protected Field getNodesField(Object node, Predicate<Field> option) {
        Field field = Arrays.stream(node.getClass().getDeclaredFields())
                .filter(option)
                .findAny()
                .orElseThrow();
        field.setAccessible(true);
        return field;
    }

}
