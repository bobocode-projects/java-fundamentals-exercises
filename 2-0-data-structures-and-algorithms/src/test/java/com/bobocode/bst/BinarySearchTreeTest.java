package com.bobocode.bst;

import com.bobocode.util.ExerciseNotCompletedException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BinarySearchTreeTest {

    private static final Integer[] someElements = {10, 9, 11, 8, 12, 7};

    @Test
    @Order(1)
    void createWithElements() {
        BinarySearchTree<Integer> bst = createTreeWith(someElements);
        for (var e: someElements) {
            assertThat(bst.contains(e)).isTrue();
        }
        assertThat(bst.size()).isEqualTo(someElements.length);
    }

    @Test
    @Order(2)
    void insertUniqueElements() {
        BinarySearchTree<Integer> bst = createTreeWith();
        for (int i = 0; i < someElements.length; i++) {
            var e = someElements[i];
            assertThat(bst.contains(e)).isFalse(); //does not contain
            assertThat(bst.size()).isEqualTo(i);

            assertThat(bst.insert(e)).isTrue(); //do insert

            assertThat(bst.contains(e)).isTrue(); //and contains
            assertThat(bst.size()).isEqualTo(i + 1);
        }
    }

    @Test
    @Order(3)
    void insertNonUniqueElements() {
        BinarySearchTree<Integer> bst = createTreeWith(someElements);
        for (var e: someElements) {
            assertThat(bst.insert(e)).isFalse(); //does not insert
            assertThat(bst.contains(e)).isTrue(); //but contains
        }
        assertThat(bst.size()).isEqualTo(someElements.length);
    }

    @ParameterizedTest
    @MethodSource("depthArguments")
    @Order(4)
    void depth(Integer[] elements, int depth) {
        BinarySearchTree<Integer> bst = createTreeWith(elements);
        assertThat(bst.depth()).isEqualTo(depth);
    }

    @Test
    @Order(5)
    void inorderTraversal() {
        BinarySearchTree<Integer> bst = createTreeWith(someElements);
        Integer[] sortedElements = Arrays.copyOf(someElements, someElements.length);
        Arrays.sort(sortedElements);

        List<Integer> traversedElements = new ArrayList<>(bst.size());
        bst.inOrderTraversal(traversedElements::add);

        assertThat(traversedElements).isEqualTo(List.of(sortedElements));
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

    @SuppressWarnings("unchecked")
    private BinarySearchTree<Integer> createTreeWith(Integer... elements) {
         return Mockito.mock(BinarySearchTree.class,
                (InvocationOnMock i) -> {throw new ExerciseNotCompletedException();}); //todo
    }
}
