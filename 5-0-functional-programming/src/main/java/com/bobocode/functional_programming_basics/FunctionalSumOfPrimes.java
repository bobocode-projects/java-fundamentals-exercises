package com.bobocode.functional_programming_basics;

import java.util.stream.IntStream;

/**
 * This example demonstrates how to use functional approach in Java. The program calculates the sum of first 20 prime
 * numbers without using mutable variables
 *
 * See {@link OOSumOfPrimes}
 */
public class FunctionalSumOfPrimes {
    public static void main(String[] args) {
        IntStream.iterate(0, i -> i + 1)
                .filter(FunctionalSumOfPrimes::isPrime)
                .limit(20)
                .reduce((a, b) -> a + b)
                .ifPresent(sum -> System.out.println("Sum of first 20 primes: " + sum));
    }

    private static boolean isPrime(int n) {
        return IntStream.range(2, n)
                .noneMatch(i -> n % i == 0);
    }
}
