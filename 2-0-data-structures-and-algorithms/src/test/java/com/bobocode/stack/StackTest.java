package com.bobocode.stack;

import com.bobocode.stack.exception.EmptyStackException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StackTest {

    private Stack<Integer> intStack = new LinkedStack<>();

    @Test
    @Order(1)
    void pushElementOntoEmptyStack() {
        intStack.push(243);

        assertThat(intStack.pop()).isEqualTo(243);
    }

    @Test
    @Order(2)
    void popElementFromEmptyStack() {
        assertThrows(EmptyStackException.class, () -> intStack.pop());
    }

    @Test
    @Order(3)
    void pushElements() {
        intStack = LinkedStack.of(23, 35, 72);

        intStack.push(55);

        assertThat(intStack.pop()).isEqualTo(55);
    }

    @Test
    @Order(4)
    void popElements() {
        intStack = LinkedStack.of(87, 53, 66);

        intStack.pop();
        intStack.push(234);
        Integer lastElement = intStack.pop();

        assertThat(lastElement).isEqualTo(234);
    }

    @Test
    @Order(5)
    void size() {
        intStack = LinkedStack.of(87, 53, 66);

        int actualSize = intStack.size();

        assertThat(actualSize).isEqualTo(3);
    }

    @Test
    @Order(6)
    void sizeOnEmptyStack() {
        int actualSize = intStack.size();

        assertThat(actualSize).isEqualTo(0);
    }

    @Test
    @Order(7)
    void isEmpty() {
        intStack = LinkedStack.of(87, 53, 66);

        boolean stackEmpty = intStack.isEmpty();

        assertThat(stackEmpty).isEqualTo(false);
    }

    @Test
    @Order(8)
    void isEmptyOnEmptyStack() {
        boolean stackEmpty = intStack.isEmpty();

        assertThat(stackEmpty).isEqualTo(true);
    }
}
