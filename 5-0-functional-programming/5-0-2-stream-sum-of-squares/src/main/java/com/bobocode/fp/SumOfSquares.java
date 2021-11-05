package com.bobocode.fp;

import com.bobocode.fp.exception.InvalidRangeException;

/**
 * This class allow to calculate a sum of squares of integer number in a certain range. It was implemented using
 * OO approach. Your job is to refactor it using functional approach. E.g. avoid using mutable variables
 *
 * @author Taras Boychuk
 */
public class SumOfSquares {
    public static void main(String[] args) {
        System.out.println("Sum of squares from 5 to 10 is " + calculateSumOfSquaresInRange(5, 10));
    }

    /**
     * This method calculates the sum of squares of integer in the range
     *
     * @param startInclusive first element in range
     * @param endInclusive   last element in range
     * @return the sum of squares of each element in the range
     */
    static int calculateSumOfSquaresInRange(int startInclusive, int endInclusive) {
        if (endInclusive < startInclusive) {
            throw new InvalidRangeException();
        }

        // todo: refactor using functional approach – instead of using for loop, use IntStream.rangeClose()
        int sumOfSquares = 0;
        for (int i = startInclusive; i <= endInclusive; i++) {
            sumOfSquares += i * i;
        }
        return sumOfSquares;
    }
}
