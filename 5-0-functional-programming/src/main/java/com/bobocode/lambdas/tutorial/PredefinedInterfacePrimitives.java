package com.bobocode.lambdas.tutorial;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.DoubleSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.LongBinaryOperator;

/**
 * Always use special classes for Primitives
 */
public class PredefinedInterfacePrimitives {
    public static void main(String[] args) {
        printRandomDoubleUsingSupplier();
        printLongSumUsingBinaryOperation(124124L, 132134L);
        printSqrtUsingFunction(25);
    }

    private static void printRandomDoubleUsingSupplier() {
        DoubleSupplier doubleSupplier = () -> ThreadLocalRandom.current().nextDouble();

        System.out.println("Random double: " + doubleSupplier.getAsDouble());
    }

    private static void printLongSumUsingBinaryOperation(long a, long b) {
        LongBinaryOperator sumOperator = Long::sum;

        System.out.println("\n" + a + " " + b + " = " + sumOperator.applyAsLong(a, b));
    }

    private static void printSqrtUsingFunction(int a) {
        IntToDoubleFunction sqrtFunction = Math::sqrt;

        System.out.println("\nsqrt(" + a + ") = " + sqrtFunction.applyAsDouble(a));
    }

}
