package com.bobocode.linked_queue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QueueTest {

    private final Queue<Integer> integerQueue = new LinkedQueue<>();

    @Test
    @Order(1)
    void sizeOfEmptyQueue() {
        assertEquals(0, integerQueue.size());
    }

    @Test
    @Order(2)
    void size() {
        integerQueue.add(98);
        integerQueue.add(9);
        integerQueue.add(5);
        integerQueue.add(6);

        assertEquals(4, integerQueue.size());
    }

    @Test
    @Order(3)
    void addElementIntoEmptyQueue() {
        integerQueue.add(1312);

        assertEquals(1, integerQueue.size());
        assertEquals(1312, integerQueue.poll().intValue());
    }

    @Test
    @Order(4)
    void addElement() {
        integerQueue.add(324);
        integerQueue.add(23);
        integerQueue.add(5);

        assertEquals(3, integerQueue.size());
        assertEquals(324, integerQueue.poll().intValue());
    }

    @Test
    @Order(5)
    void pollElementFromEmptyQueue() {
        assertNull(integerQueue.poll());
    }

    @Test
    @Order(6)
    void pollElement() {
        integerQueue.add(33);
        integerQueue.add(123);
        integerQueue.add(222);
        integerQueue.add(444);

        integerQueue.poll(); // should poll 33

        assertEquals(123, integerQueue.poll().intValue());
        assertEquals(2, integerQueue.size());
    }

    @Test
    @Order(7)
    void pollLastElement() {
        integerQueue.add(8);
        integerQueue.add(123);
        integerQueue.add(99);
        integerQueue.add(46);

        integerQueue.poll(); // should poll 8
        integerQueue.poll(); // should poll 123
        integerQueue.poll(); // should poll 99

        assertEquals(46, integerQueue.poll().intValue());
        assertEquals(0, integerQueue.size());
    }

    @Test
    @Order(8)
    void isEmpty() {
        integerQueue.add(3);
        integerQueue.add(9);

        assertFalse(integerQueue.isEmpty());

    }

    @Test
    void isEmptyOnEmptyQueue() {
        assertTrue(integerQueue.isEmpty());
    }

}
