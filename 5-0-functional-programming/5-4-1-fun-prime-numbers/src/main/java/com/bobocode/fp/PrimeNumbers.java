package com.bobocode.fp;

import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * {@link PrimeNumbers} provides an API to work with prime numbers. It is using a stream of prime numbers.
 * <p>
 * See {@link OOSumOfPrimes} for a reference
 */
public class PrimeNumbers {
    private PrimeNumbers() {
    }

    /**
     * Calculates the sum on first n prime numbers.
     * E.g. if n = 5, the result should be 2 + 3 + 5 + 7 + 11 = 28
     *
     * @param n the number of first prime numbers
     * @return the sum of n prime numbers
     */
    public static int sum(int n) {
        return primeNumberStream()
                .limit(n)
                .sum();
    }

    /**
     * Collects n first prime numbers.
     *
     * @return a list of collected prime numbers
     */
    public static List<Integer> collect(int n) {
        return primeNumberStream()
                .limit(n)
                .boxed()
                .collect(toList());
    }

    /**
     * Find a prime number by index and then applies a provided consumer passing found prime number
     *
     * @param idx      the position of a prime number (index)
     * @param consumer a logic that should be applied to the found prime number
     */
    public static void processByIndex(int idx, IntConsumer consumer) {
        primeNumberStream()
                .limit(idx)
                .reduce((a, b) -> b)
                .ifPresent(consumer);
    }

    private static IntStream primeNumberStream() {
        return IntStream.iterate(1, i -> i + 1)
                .filter(PrimeNumbers::isPrime);
    }

    private static boolean isPrime(int n) {
        return IntStream.range(2, n)
                .noneMatch(i -> n % i == 0);
    }
}
