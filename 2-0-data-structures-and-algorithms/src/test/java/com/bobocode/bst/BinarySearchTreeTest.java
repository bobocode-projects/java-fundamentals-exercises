package com.bobocode.bst;

import com.bobocode.util.ExerciseNotCompletedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;

/*import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;*/

//todo: add Order
//todo: checklist https://bobocode.atlassian.net/l/c/MeYPQPHx
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BinarySearchTreeTest {

    @Test
    void insertIntoEmptyTree() {
        BinarySearchTree<Integer> bst = createOf();
        boolean inserted = bst.insert(123);

        assertThat(inserted).isTrue();
    }

    @Test
    void insertTwoElementsWithSameValue() {
        BinarySearchTree<Integer> bst = createOf(25);
        boolean inserted = bst.insert(25);

        assertThat(inserted).isFalse();
    }

    //TODO: rename test and method
    @Test
    void insertElements() {
        BinarySearchTree<Integer> bst = createOf(10, 9, 11, 8, 12, 7);
        //TODO: should insert
        //bst.insert()

        assertThat(bst.search(10)).isTrue();
        assertThat(bst.search(9)).isTrue();
        assertThat(bst.search(11)).isTrue();
        assertThat(bst.search(8)).isTrue();
        assertThat(bst.search(12)).isTrue();
        assertThat(bst.search(7)).isTrue();
    }

    //TODO: should be separate test??
    @Test
    void searchRootElement() {
        BinarySearchTree<Integer> bst = createOf(44);

        boolean foundExistingElement = bst.search(44);
        boolean foundNotExistingElement = bst.search(23423);

        assertThat(foundExistingElement).isTrue();
        assertThat(foundNotExistingElement).isFalse();
    }

    @Test
    void searchInEmptyTree() {
        BinarySearchTree<Integer> bst = createOf();
        boolean found = bst.search(55);

        assertThat(found).isFalse();
    }

    @Test
    void searchElements() {
        BinarySearchTree<Integer> bst = createOf(234, 54, 12, 544, 21, 10);

        assertThat(bst.search(234)).isTrue();
        assertThat(bst.search(54)).isTrue();
        assertThat(bst.search(12)).isTrue();
        assertThat(bst.search(544)).isTrue();
        assertThat(bst.search(21)).isTrue();
        assertThat(bst.search(10)).isTrue();
        assertThat(bst.search(1000)).isFalse();
    }

    @Test
    void sizeOfEmptyTree() {
        BinarySearchTree<Integer> bst = createOf();
        int actualTreeSize = bst.size();

        assertThat(actualTreeSize).isEqualTo(0);
    }

    @Test
    void size() {
        BinarySearchTree<Integer> bst = createOf(1, 2, 3, 4, 1);
        int actualTreeSize = bst.size();

        assertThat(actualTreeSize).isEqualTo(4);
    }

    @Test
    void heightOfEmptyTree() {
        BinarySearchTree<Integer> bst = createOf();
        int actualHeight = bst.height();

        assertThat(actualHeight).isEqualTo(0);
    }

    @Test
    void heightOfOneElementTree() {
        BinarySearchTree<Integer> bst = createOf(24);

        int actualHeight = bst.height();

        //todo: check value, this was initially
        assertThat(actualHeight).isEqualTo(0);
    }

    /**
     * .......10
     * ....../  \
     * .....5   15
     * ..../      \
     * ...1       20
     */
    @Test
    void height() {
        BinarySearchTree<Integer> bst = createOf(10, 5, 15, 1, 20);

        int actualHeight = bst.height();
        //todo: change contract, height should be 3
        assertThat(actualHeight).isEqualTo(2);
    }

    /**
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
    @Test
    void heightOfLikedListTree() {
        BinarySearchTree<Integer> bst = createOf(1, 2, 3, 4, 5);

        int actualHeight = bst.height();

        //todo: contr. will be changed
        assertThat(actualHeight).isEqualTo(4);
    }

    @Test
    void heightOfSingleElementTree() {
        BinarySearchTree<Integer> bst = createOf(1);

        int actualHeight = bst.height();
        //todo: contr. will be changed
        assertThat(actualHeight).isEqualTo(0);
    }

    @Test
    void inorderTraversalOfEmptyTree() {
        BinarySearchTree<Integer> bst = createOf();
        List<Integer> treeElementsList = new ArrayList<>(bst.size());
        bst.inOrderTraversal(treeElementsList::add);

        assertThat(treeElementsList).isEmpty();
    }

    @Test
    void inorderTraversal() {
        BinarySearchTree<Integer> bst = createOf(324, 23, 14, 1551, 2);

        List<Integer> treeElementsList = new ArrayList<>(bst.size());
        bst.inOrderTraversal(treeElementsList::add);

        assertThat(bst.size()).isEqualTo(treeElementsList.size());
        assertThat(treeElementsList).contains(2, 14, 23, 324, 1551);
    }

    private BinarySearchTree<Integer> createOf(Integer... elements) {
         return Mockito.mock(BinarySearchTree.class,
                (InvocationOnMock i) -> {throw new ExerciseNotCompletedException();}); //todo
    }
}
