package com.bobocode;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class TimeComplexityDemo {
    public static void main(String[] args) {
        for (int n = 1; n < 1000_000_000; n *= 2) {
            var arr = generateArray(n);
            var attempts = 10;
            var totalExecutionTime = 0L;
            for (int i = 0; i < attempts; i++) {
                var star = System.nanoTime();
                var min = findMin(arr);
                var time = System.nanoTime() - star;
                totalExecutionTime += time;
            }
            System.out.printf("%10d, %10d,%n", n, totalExecutionTime / attempts);
        }

    }

    static int findMin(int[] arr) {
        Objects.requireNonNull(arr);
        Objects.checkIndex(0, arr.length);
        var min = arr[0];
        for (var e : arr) {
            if (e < min) {
                min = e;
            }
        }
        return min;
    }

    static int[] generateArray(int size) {
        var arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(size);
        }
        return arr;
    }
}
