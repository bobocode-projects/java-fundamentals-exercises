package com.bobocode.lambdas.tutorial;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * An example of crazy lambda that is hard but still important to understand
 */
public class CrazyLambdaExample {
    public static void main(String[] args) {
        Supplier<Function<Predicate<String>, Consumer<String>>> stringPredicateToConsumerFunctionSupplier =
                getStringPredicateToConsumerFunctionSupplier();

        Function<Predicate<String>, Consumer<String>> stringPredicateConsumerFunction = stringPredicateToConsumerFunctionSupplier.get();
        Consumer<String> stringIsEmptyChecker = stringPredicateConsumerFunction.apply(String::isEmpty);
        stringIsEmptyChecker.accept("");
    }

    /**
     * Returns the {@link Supplier} instance that supplies {@link Function} that receives a string {@link Predicate} as
     * a parameter and returns string {@link Consumer}
     *
     * @return an instance of supplier
     */
    private static Supplier<Function<Predicate<String>, Consumer<String>>> getStringPredicateToConsumerFunctionSupplier() {
        return () -> stringPredicate -> str -> System.out.println(stringPredicate.test(str));
    }


}
