package com.bobocode.fp;

import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * {@link PrimeNumbers} provides an API to work with prime numbers. The implementation is based on the
 * {@link java.util.stream.IntStream} of prime numbers. That stream is used in all public methods on this class.
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
        return primeNumberStream(n)
                .sum();
    }

    /**
     * Collects n first prime numbers.
     *
     * @return a list of collected prime numbers
     */
    public static List<Integer> collect(int n) {
        return primeNumberStream(n)
                .boxed()
                .collect(toList());
    }

    /**
     * Find a prime number by index and then applies a provided consumer passing found prime number
     *
     * @param idx      the position of a prime number (index), starting from 0
     * @param consumer a logic that should be applied to the found prime number
     */
    public static void processByIndex(int idx, IntConsumer consumer) {
        primeNumberStream(idx + 1)
                .skip(idx)
                .findAny()
                .ifPresent(consumer);
    }

    private static IntStream primeNumberStream(int size) {
        return IntStream.iterate(2, i -> i + 1)
                .filter(PrimeNumbers::isPrime)
                .limit(size);
    }

    private static boolean isPrime(int n) {
        return (n != 1) && IntStream.range(2, n)
                .noneMatch(i -> n % i == 0);
    }
}
