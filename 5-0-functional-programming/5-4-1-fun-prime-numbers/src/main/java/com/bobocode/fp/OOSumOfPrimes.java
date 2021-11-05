package com.bobocode.fp;

/**
 * This examples demonstrates how to calculate the sum of prime numbers using Object-Oriented approach.
 * Please note that in order to perform this calculation in OOP-style we have to use mutable variables
 * (e.g. sumOfPrimes, i, primes). Implementing the same functionality in a functional style using Steam API and lambdas,
 * you will not create and/or modify variables.
 *
 * @author Taras Boychuk
 */
public class OOSumOfPrimes {
    public static void main(String[] args) {
        int sumOfPrimes = 0;
        int primes = 0;
        for (int i = 0; primes <= 20; i++) {
            if (isPrime(i)) {
                System.out.println(primes + " : " + i);
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
