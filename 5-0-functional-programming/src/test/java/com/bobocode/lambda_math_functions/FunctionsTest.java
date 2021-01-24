package com.bobocode.lambda_math_functions;

import com.bobocode.InvalidFunctionNameException;
import org.junit.jupiter.api.*;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@TestMethodOrder( MethodOrderer.OrderAnnotation.class)
public class FunctionsTest {
    private com.bobocode.FunctionMap<Integer, Integer> integerFunctionMap;

    @BeforeEach
    public void init() {
        integerFunctionMap = com.bobocode.Functions.intFunctionMap();
    }

    @Test
    @Order(7)
    void squareFunction() {
        Function<Integer, Integer> squareFunction = integerFunctionMap.getFunction("square");

        int actualResult = squareFunction.apply(5);

        assertEquals(25, actualResult);
    }

    @Test
    @Order(1)
    void absFunction() {
        Function<Integer, Integer> absFunction = integerFunctionMap.getFunction("abs");

        int actualResult = absFunction.apply(-192);

        assertEquals(192, actualResult);
    }

    @Test
    @Order(5)
    void incrementFunction() {
        Function<Integer, Integer> incrementFunction = integerFunctionMap.getFunction("increment");

        int actualResult = incrementFunction.apply(399);

        assertEquals(400, actualResult);
    }

    @Test
    @Order(6)
    void destDecrementFunction() {
        Function<Integer, Integer> decrementFunction = integerFunctionMap.getFunction("decrement");

        int actualResult = decrementFunction.apply(800);

        assertEquals(799, actualResult);
    }

    @Test
    @Order(2)
    void signFunctionOnNegativeValue() {
        Function<Integer, Integer> signFunction = integerFunctionMap.getFunction("sgn");

        int actualResult = signFunction.apply(-123);

        assertEquals(-1, actualResult);
    }

    @Test
    @Order(3)
    void signFunctionOnPositiveValue() {
        Function<Integer, Integer> signFunction = integerFunctionMap.getFunction("sgn");

        int actualResult = signFunction.apply(23);

        assertEquals(1, actualResult);
    }

    @Test
    @Order(4)
    void signFunctionOnZero() {
        Function<Integer, Integer> signFunction = integerFunctionMap.getFunction("sgn");

        int actualResult = signFunction.apply(0);

        assertEquals(0, actualResult);
    }

    @Test
    @Order(8)
    void getGetUnknownFunction() {
        assertThrows(InvalidFunctionNameException.class, () -> integerFunctionMap.getFunction("sqrt"));
    }
}
