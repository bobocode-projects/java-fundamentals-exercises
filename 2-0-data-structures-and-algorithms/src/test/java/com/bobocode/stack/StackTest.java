package com.bobocode.stack;

import com.bobocode.stack.exception.EmptyStackException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    private Stack<Integer> intStack = new NodeStack<>();

    @Test
    void testPushElementOntoEmptyStack() {
        intStack.push(234);
    }

    @Test
    void testPopElementFromEmptyStack() {
        assertThrows(EmptyStackException.class, () -> intStack.pop());
    }

    @Test
    void testPushElements() {
        intStack = NodeStack.of(23, 35, 72);

        intStack.push(55);

        assertThat(intStack.pop(), is(55));
    }

    @Test
    void testPopElements() {
        intStack = NodeStack.of(87, 53, 66);

        intStack.pop();
        intStack.push(234);
        Integer lastElement = intStack.pop();

        assertThat(lastElement, is(234));
    }

    @Test
    void testSize(){
        intStack = NodeStack.of(87, 53, 66);

        int actualSize = intStack.size();

        assertThat(actualSize, is(3));
    }

    @Test
    void testSizeOnEmptyStack(){
        int actualSize = intStack.size();

        assertThat(actualSize, is(0));
    }

    @Test
    void testIsEmpty(){
        intStack = NodeStack.of(87, 53, 66);

        boolean stackEmpty = intStack.isEmpty();

        assertThat(stackEmpty, is(false));
    }

    @Test
    void testIsEmptyOnEmptyStack(){
        boolean stackEmpty = intStack.isEmpty();

        assertThat(stackEmpty, is(true));
    }
}
