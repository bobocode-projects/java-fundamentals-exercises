package com.bobocode;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Stream;

public class ComparatorDemoApp {
    public static void main(String[] args) {
        Stream.of("Andrii", "Maria", "Yehven", "Taras")
                .max(thenComparing(comparing(s -> s.length()), s -> s.charAt(0)))
                .ifPresent(System.out::println);
    }

    static <T, R extends Comparable<? super R>> Comparator<T> comparing(
            Function<? super T, ? extends R> extractionFunction
    ) {
        return (o1, o2) -> extractionFunction.apply(o1).compareTo(extractionFunction.apply(o2));
    }

    static <T, R extends Comparable<? super R>> Comparator<T> thenComparing(
            Comparator<? super T> initialComparator,
            Function<? super T, ? extends R> extractionFunction
    ) {
        return (o1, o2) -> {
            var initialResult = initialComparator.compare(o1, o2);
            if (initialResult != 0) {
                return initialResult;
            } else {
                return extractionFunction.apply(o1).compareTo(extractionFunction.apply(o2));
            }
        };
    }
}
