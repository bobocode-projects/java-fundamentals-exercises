package com.bobocode.streams.tutorial;


import java.util.function.LongPredicate;
import java.util.stream.LongStream;

public class StreamParallelProcessing {
    static final long STREAM_SIZE = 100_000_000;
    static final int N = 10;

    public static void main(String[] args) {
        LongPredicate isDivisibleBySeven = n -> n % 7 == 0;

        System.out.println("Sequential processing");
        performNTimes(N, () -> LongStream.range(1, STREAM_SIZE)
                .filter(isDivisibleBySeven)
                .count());

        System.out.println("\nParallel processing");
        performNTimes(N, () -> LongStream.range(1, STREAM_SIZE)
                .parallel()
                .filter(isDivisibleBySeven)
                .count());

    }

    static void performNTimes(int n, Runnable r) {
        LongStream.range(0, n).forEach(i -> {
                    long start = System.nanoTime();
                    r.run();
                    System.out.println((System.nanoTime() - start) / 1_000_000 + " ms");
                }
        );
    }
}
