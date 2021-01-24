package com.bobocode.lambda_math_functions;

public class InvalidFunctionNameException extends RuntimeException {
    public InvalidFunctionNameException(String functionName) {
        super("Function " + functionName + " doesn't exist.");
    }
}