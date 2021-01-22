package com.bobocode.streams.tutorial;


import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamPrimitives {
    public static void main(String[] args) {
        List<Account> accounts = Accounts.generateAccountList(10);

        printLongValues();
        printMaxBalance(accounts);
    }

    /**
     * {@link LongStream} is a stream of primitive long values.
     * It can also be boxed into a {@link Stream} of {@link Long} values. PLEASE NOTE, that you should always use
     * primitive streams whenever it's possible to avoid performance issues
     */
    private static void printLongValues() {
        LongStream longStream = LongStream.of(1, 2, 3, 4, 5);
        Stream<Long> boxedLongStream = longStream.boxed();
        boxedLongStream.forEach(System.out::println);
    }

    /**
     * This method creates a {@link Stream} of {@link Account} and then maps it to stream of doubles. To transform stream
     * of objects like an {@link Account} into a stream of primitive doubles you should use special mapper method
     * mapToDouble(), which creates an instance of {@link java.util.stream.DoubleStream}
     */
    private static void printMaxBalance(List<Account> accounts) {
        double maxBalance = accounts.stream()
                .mapToDouble(a -> a.getBalance().doubleValue())
                .max().getAsDouble();

        System.out.println(maxBalance);
    }
}
