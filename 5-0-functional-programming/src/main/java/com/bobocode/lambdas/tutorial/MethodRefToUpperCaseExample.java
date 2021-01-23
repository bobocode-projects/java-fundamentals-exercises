package com.bobocode.lambdas.tutorial;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * A list of different examples of method reference usage.
 */
public class MethodRefToUpperCaseExample {
    public static void main(String[] args) {
        String s = "Method reference insights";

        printUpperStringUsingFunction(s);
        printUpperStringUsingUnaryOperation(s);
        printUpperStringUsingStringSupplier(s);
    }

    /**
     * This is the unbound method reference example. Since we used unbound method reference, the {@link Function} can
     * receive a string as an input parameter.
     */
    private static void printUpperStringUsingFunction(String s) {
        Function<String, String> upperCaseFunction = String::toUpperCase;

        System.out.println(upperCaseFunction.apply(s));
    }

    /**
     * This is the unbound method reference example. Since toUpperCase() receives and returns the same type
     * {@link String}, we can easily replace {@link Function} with {@link UnaryOperator}
     */
    private static void printUpperStringUsingUnaryOperation(String s) {
        UnaryOperator<String> upperCaseOperator = String::toUpperCase;

        System.out.println(upperCaseOperator.apply(s));
    }

    /**
     * This is the bound method reference example. We actually reference a method that is bound to concrete
     * string instance. Since we reference a concrete string, there is no input parameter, only an output. In this case
     * we can use {@link Supplier}
     */
    private static void printUpperStringUsingStringSupplier(String s) {
        Supplier<String> stringSupplier = s::toUpperCase;

        System.out.println(stringSupplier.get());
    }

}
