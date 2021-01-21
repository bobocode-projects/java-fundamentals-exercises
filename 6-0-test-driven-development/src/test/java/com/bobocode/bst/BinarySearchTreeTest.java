package com.bobocode.bst;

import com.bobocode.util.ExerciseNotCompletedException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BinarySearchTreeTest {

    /**
     * Always failing test, remove it before doing the exercise
     */
    @Test
    @Order(1)
    public void notImplemented() {
        fail(null, new ExerciseNotCompletedException());
    }
}
