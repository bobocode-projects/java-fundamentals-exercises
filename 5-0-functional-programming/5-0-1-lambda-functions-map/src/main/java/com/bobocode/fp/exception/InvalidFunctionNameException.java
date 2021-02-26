package com.bobocode.fp.exception;

public class InvalidFunctionNameException extends RuntimeException {
    public InvalidFunctionNameException(String functionName) {
        super("Function " + functionName + " doesn't exist.");
    }
}