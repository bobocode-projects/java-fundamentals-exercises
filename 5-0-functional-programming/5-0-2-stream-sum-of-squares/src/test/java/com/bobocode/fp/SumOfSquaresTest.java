package com.bobocode.fp;

import com.bobocode.fp.exception.InvalidRangeException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

/**
 * A test class for {@link SumOfSquares}
 *
 * @author Taras Boychuk
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SumOfSquaresTest {

    @Test
    @Order(1)
    void calculateSumOfSquaresOfZero() {
        int sumOfSquares = SumOfSquares.calculateSumOfSquaresInRange(0, 0);

        assertThat(sumOfSquares).isZero();
    }

    @Test
    @Order(2)
    void calculateSumOfSquaresOfOne() {
        int sumOfSquares = SumOfSquares.calculateSumOfSquaresInRange(0, 1);

        assertThat(sumOfSquares).isEqualTo(1);
    }

    @Test
    @Order(3)
    void calculateSumOfSquares() {
        int sumOfSquares = SumOfSquares.calculateSumOfSquaresInRange(1, 5); // 1*1 + 2*2 + 3*3 + 4*4 + 5*5 = 55

        assertThat(sumOfSquares).isEqualTo(55);
    }

    @Test
    @Order(4)
    void calculateSumOfSquaresOnNegative() {
        int sumOfSquares = SumOfSquares.calculateSumOfSquaresInRange(-4, -2); // -4*(-4) + -3*(-3) + -2*(-2) = 29

        assertThat(sumOfSquares).isEqualTo(29);
    }

    @Test
    @Order(5)
    void calculateWithInvalidRange() {
        assertThatExceptionOfType(InvalidRangeException.class)
                .isThrownBy(() -> SumOfSquares.calculateSumOfSquaresInRange(4, 1));
    }
}
