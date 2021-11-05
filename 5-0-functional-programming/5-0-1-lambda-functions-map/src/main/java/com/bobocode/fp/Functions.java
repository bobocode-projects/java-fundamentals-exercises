package com.bobocode.fp;

import static java.lang.Math.abs;

/**
 * An util class that provides a factory method for creating an instance of a {@link FunctionMap} filled with a list
 * of functions.
 * <p>
 * todo: implement a method and verify it by running {@link FunctionsTest}
 *
 * @author Taras Boychuk
 */
public class Functions {
    /**
     * A static factory method that creates an integer function map with basic functions:
     * - abs (absolute value)
     * - sgn (signum function)
     * - increment
     * - decrement
     * - square
     *
     * @return an instance of {@link FunctionMap} that contains all listed functions
     */
    public static FunctionMap<Integer, Integer> intFunctionMap() {
        FunctionMap<Integer, Integer> intFunctionMap = new FunctionMap<>();

        intFunctionMap.addFunction("square", n -> n * n);
        intFunctionMap.addFunction("abs", Math::abs);
        intFunctionMap.addFunction("increment", n -> n + 1);
        intFunctionMap.addFunction("decrement", n -> n - 1);
        intFunctionMap.addFunction("sgn", n -> (n != 0) ? n / abs(n) : 0);

        return intFunctionMap;
    }
}
