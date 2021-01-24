package com.bobocode.lambda_math_functions;

import com.bobocode.lambda_math_functions.FunctionMap;

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

        // todo: add simple functions to the function map (abs, sgn, increment, decrement, square)

        return intFunctionMap;
    }
}
