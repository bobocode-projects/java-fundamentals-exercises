package com.bobocode.fp;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.List;
import java.util.function.IntConsumer;

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
        throw new ExerciseNotCompletedException(); // todo: create an infinite stream of ints, then filter prime numbs

    }

    /**
     * Collects n first prime numbers.
     *
     * @return a list of collected prime numbers
     */
    public static List<Integer> collect(int n) {
        throw new ExerciseNotCompletedException(); // todo: reuse the logic of prime numbers stream and collect them
    }

    /**
     * Find a prime number by index and then applies a provided consumer passing found prime number
     *
     * @param idx      the position of a prime number (index)
     * @param consumer a logic that should be applied to the found prime number
     */
    public static void processByIndex(int idx, IntConsumer consumer) {
        throw new ExerciseNotCompletedException(); // todo: reuse the logic of prime numbers stream then process the last one
    }
}
