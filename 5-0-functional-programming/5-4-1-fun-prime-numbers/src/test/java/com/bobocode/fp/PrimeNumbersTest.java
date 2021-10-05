package com.bobocode.fp;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PrimeNumbersTest {

    @Order(1)
    @ParameterizedTest
    @CsvSource({"0, 0", "1, 2", "2, 5", "3, 10", "4, 17", "5, 28", "10, 129", "20, 639"})
    void sum(int n, int sumOfPrimes) {
        int result = PrimeNumbers.sum(n);

        assertThat(result).isEqualTo(sumOfPrimes);
    }

    @Order(2)
    @ParameterizedTest
    @MethodSource("collectArgs")
    void collect(int n, List<Integer> primeNumbersList) {
        List<Integer> result = PrimeNumbers.collect(n);

        assertThat(result).isEqualTo(primeNumbersList);
    }

    @Order(3)
    @ParameterizedTest
    @CsvSource({"0, 2", "1, 3", "2, 5", "3, 7", "10, 31", "20, 73", "279, 1811"})
    void processByIndexFindsCorrectPrimeNumber(int index, int primeNumber) {
        var list = new ArrayList<>();

        PrimeNumbers.processByIndex(index, list::add);

        assertThat(list.get(0)).isEqualTo(primeNumber);
    }

    static Stream<Arguments> collectArgs() {
        return Stream.of(
                arguments(1, List.of(2)),
                arguments(2, List.of(2, 3)),
                arguments(3, List.of(2, 3, 5)),
                arguments(4, List.of(2, 3, 5, 7)),
                arguments(5, List.of(2, 3, 5, 7, 11)),
                arguments(10, List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29))
        );
    }
}
