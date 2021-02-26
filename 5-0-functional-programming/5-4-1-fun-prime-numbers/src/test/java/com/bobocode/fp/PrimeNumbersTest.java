package com.bobocode.fp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PrimeNumbersTest {

    @ParameterizedTest
    @CsvSource({"0,0", "1,1", "2,3", "3,6", "4,11", "5,18", "10,101", "20,569"})
    void sum(int n, int sumOfPrimes) {
        int result = PrimeNumbers.sum(n);

        assertThat(result).isEqualTo(sumOfPrimes);
    }

    @ParameterizedTest
    @MethodSource("collectArgs")
    void collect(int n, List<Integer> primeNumbersList) {
        List<Integer> result = PrimeNumbers.collect(n);

        assertThat(result).isEqualTo(primeNumbersList);
    }


    @ParameterizedTest
    @CsvSource({"1,1", "2,2", "3,3", "4,5", "5,7", "10,23", "20,67"})
    void processByIndexFindsCorrectPrimeNumber(int index, int primeNumber) {
        var list = new ArrayList<>();

        PrimeNumbers.processByIndex(index, list::add);

        assertThat(list.get(0)).isEqualTo(primeNumber);
    }

    static Stream<Arguments> collectArgs() {
        return Stream.of(
                arguments(1, List.of(1)),
                arguments(2, List.of(1, 2)),
                arguments(3, List.of(1, 2, 3)),
                arguments(4, List.of(1, 2, 3, 5)),
                arguments(5, List.of(1, 2, 3, 5, 7)),
                arguments(10, List.of(1, 2, 3, 5, 7, 11, 13, 17, 19, 23))
        );
    }
}
