package com.bobocode.fp;

import org.junit.jupiter.api.*;

import java.util.function.Function;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FunctionsTest {
    private FunctionMap<Integer, Integer> integerFunctionMap;

    @BeforeEach
    public void init() {
        integerFunctionMap = Functions.intFunctionMap();
    }

    @Test
    @Order(7)
    void squareFunction() {
        Function<Integer, Integer> squareFunction = integerFunctionMap.getFunction("square");

        int result = squareFunction.apply(5);

        assertThat(result).isEqualTo(25);
    }

    @Test
    @Order(1)
    void absFunction() {
        Function<Integer, Integer> absFunction = integerFunctionMap.getFunction("abs");

        int result = absFunction.apply(-192);

        assertThat(result).isEqualTo(192);
    }

    @Test
    @Order(5)
    void incrementFunction() {
        Function<Integer, Integer> incrementFunction = integerFunctionMap.getFunction("increment");

        int result = incrementFunction.apply(399);

        assertThat(result).isEqualTo(400);
    }

    @Test
    @Order(6)
    void destDecrementFunction() {
        Function<Integer, Integer> decrementFunction = integerFunctionMap.getFunction("decrement");

        int result = decrementFunction.apply(800);

        assertThat(result).isEqualTo(799);
    }

    @Test
    @Order(2)
    void signFunctionOnNegativeValue() {
        Function<Integer, Integer> signFunction = integerFunctionMap.getFunction("sgn");

        int result = signFunction.apply(-123);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    @Order(3)
    void signFunctionOnPositiveValue() {
        Function<Integer, Integer> signFunction = integerFunctionMap.getFunction("sgn");

        int result = signFunction.apply(23);

        assertThat(result).isEqualTo(1);
    }

    @Test
    @Order(4)
    void signFunctionOnZero() {
        Function<Integer, Integer> signFunction = integerFunctionMap.getFunction("sgn");

        int result = signFunction.apply(0);

        assertThat(result).isEqualTo(0);
    }
}
