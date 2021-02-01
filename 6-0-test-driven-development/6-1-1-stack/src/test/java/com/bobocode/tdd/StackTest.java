package com.bobocode.tdd;

import com.bobocode.tdd.exception.EmptyStackException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StackTest {

    private Stack<Integer> intStack = new LinkedStack<>();

    @Test
    void pushElementOntoEmptyStack() {
        intStack.push(243);

        assertThat(intStack.pop()).isEqualTo(243);
    }

    @Test
    void popElementFromEmptyStack() {
        assertThrows(EmptyStackException.class, () -> intStack.pop());
    }

    @Test
    void pushElements() {
        intStack = LinkedStack.of(23, 35, 72);

        intStack.push(55);

        assertThat(intStack.pop()).isEqualTo(55);
    }

    @Test
    void popElements() {
        intStack = LinkedStack.of(87, 53, 66);

        intStack.pop();
        intStack.push(234);
        Integer lastElement = intStack.pop();

        assertThat(lastElement).isEqualTo(234);
    }

    @Test
    void size() {
        intStack = LinkedStack.of(87, 53, 66);

        int actualSize = intStack.size();

        assertThat(actualSize).isEqualTo(3);
    }

    @Test
    void sizeOnEmptyStack() {
        int actualSize = intStack.size();

        assertThat(actualSize).isEqualTo(0);
    }

    @Test
    void isEmpty() {
        intStack = LinkedStack.of(87, 53, 66);

        boolean stackEmpty = intStack.isEmpty();

        assertThat(stackEmpty).isEqualTo(false);
    }

    @Test
    void isEmptyOnEmptyStack() {
        boolean stackEmpty = intStack.isEmpty();

        assertThat(stackEmpty).isEqualTo(true);
    }
}
