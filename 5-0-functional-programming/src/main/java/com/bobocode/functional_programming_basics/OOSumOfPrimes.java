package com.bobocode.functional_programming_basics;

/**
 * This examples demonstrates how to calculate the sum of prime numbers using OO approach. The point is that to perform
 * this calculation in OOP-style we have to use mutable variables (e.g. sumOfPrimes, i, primes)
 *
 * See {@link FunctionalSumOfPrimes}
 */
public class OOSumOfPrimes {
    public static void main(String[] args) {
        int sumOfPrimes = 0;
        for (int i = 0, primes = 0; primes < 20; i++) {
            if (isPrime(i)) {
                sumOfPrimes += i;
                primes++;
            }
        }

        System.out.println("Sum of first 20 primes: " + sumOfPrimes);
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
