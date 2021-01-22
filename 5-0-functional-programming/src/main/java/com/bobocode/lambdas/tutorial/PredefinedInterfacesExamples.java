package com.bobocode.lambdas.tutorial;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;
import java.util.stream.IntStream;

/**
 * A list of predefined interfaces examples.
 */
public class PredefinedInterfacesExamples {
    public static void main(String[] args) {
        printEmailUsingAccountConsumer();
        printARandomNumberUsingIntegerSupplier();
        calculate3xValueUsingIntegerFunction();
        checkIfNumberIsPositiveUsingIntegerPredicate();
        verifyGoogleEmailUsingAccountPredicate();
        printPrimeNumbersUsingIntegerPredicate();
    }

    private static void printARandomNumberUsingIntegerSupplier() {
        IntSupplier integerSupplier = () -> ThreadLocalRandom.current().nextInt(1000);

        System.out.println("\nNext random value: " + integerSupplier.getAsInt());
    }

    private static void printEmailUsingAccountConsumer() {
        Consumer<Account> accountConsumer = acc -> System.out.println("\nAccount email: " + acc.getEmail());
        Account account = Accounts.generateAccount();

        accountConsumer.accept(account);
    }

    private static void calculate3xValueUsingIntegerFunction() {
        IntUnaryOperator tripleFunction = n -> 3 * n;
        int a = 12;

        System.out.println("\n3 * " + a + " = " + tripleFunction.applyAsInt(a));
    }

    private static void checkIfNumberIsPositiveUsingIntegerPredicate() {
        IntPredicate isPositive = n -> n > 0;
        int b = ThreadLocalRandom.current().nextInt();

        System.out.println("\n" + b + " is " + (isPositive.test(b) ? "positive" : "negative"));
    }

    private static void verifyGoogleEmailUsingAccountPredicate() {
        Account account = Accounts.generateAccount();
        Predicate<Account> isGmailUser = a -> a.getEmail().endsWith("@gmail.com");

        System.out.println("\n" + account.getEmail() + " is "
                + (isGmailUser.test(account) ? "" : "not") + " a Google email.");
    }

    private static void printPrimeNumbersUsingIntegerPredicate() {
        IntPredicate isPrime = n -> IntStream.range(2, n).noneMatch(i -> n % i == 0);
        System.out.println();
        IntStream.rangeClosed(1, 25)
                .forEach(i -> System.out.printf("%3d %10s\n", i, (isPrime.test(i) ? " is prime" : "")));
    }
}
