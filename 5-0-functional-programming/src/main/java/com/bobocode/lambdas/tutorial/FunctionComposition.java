package com.bobocode.lambdas.tutorial;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

/**
 * One of functional programming techniques if a function composition. Having two different functions f(x) and g(x) in
 * math you would write f(g(x)) to compose those function. The same you can do in Java using method
 * {@link IntUnaryOperator#compose} or {@link IntUnaryOperator#andThen(IntUnaryOperator)}
 * <p>
 * In fact most predefined functional interfaces have similar capabilities.
 * E.g. {@link Function#compose(Function)} or {@link Predicate#and(Predicate)}
 */
public class FunctionComposition {
    public static void main(String[] args) {
        printSquareOfDoubleUsingFunctionComposition();
        printStringIsBlankUsingPredicateComposition();
    }

    private static void printSquareOfDoubleUsingFunctionComposition() {
        IntUnaryOperator squareFunction = a -> a * a; // s(x) = x * x
        IntUnaryOperator doublerFunction = a -> 2 * a; // d(x) = 2 * x

        IntUnaryOperator squareOfDoubleFunction = squareFunction.compose(doublerFunction); // s(d(x))

        System.out.println("square(double(3)) = " + squareOfDoubleFunction.applyAsInt(3));
    }

    private static void printStringIsBlankUsingPredicateComposition() {
        Predicate<String> isEmptyPredicate = String::isEmpty;
        Predicate<String> isNotNullPredicate = Objects::nonNull;

        Predicate<String> isNotBlank = isNotNullPredicate.and(isEmptyPredicate.negate());

        String str = "Hi";
        System.out.println("String \"" + str + "\" is not blank? " + isNotBlank.test(str));


    }


}
