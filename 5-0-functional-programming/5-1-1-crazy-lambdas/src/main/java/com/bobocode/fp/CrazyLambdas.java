package com.bobocode.fp;

import com.bobocode.util.ExerciseNotCompletedException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

/**
 * {@link CrazyLambdas} is an exercise class. Each method returns a functional interface and it should be implemented
 * using either lambda or a method reference. Every method that is not implemented yet throws
 * {@link ExerciseNotCompletedException}.
 * <p>
 * TODO: remove exception and implement each method of this class using lambda or method reference
 */
public class CrazyLambdas {

    /**
     * Returns {@link Supplier} that always supply "Hello"
     *
     * @return a string supplier
     */
    public static Supplier<String> helloSupplier() {
        // because supplier method has NO PARAMETERS, a lambda starts with empty brackets
        return () -> "Hello";
    }

    /**
     * Returns a {@link Predicate} of string that checks if string is empty
     *
     * @return a string predicate
     */
    public static Predicate<String> isEmptyPredicate() {
        // have a string parameter we can call isEmpty() and return result, e.g `str -> str.isEmpty()`  
        // so if we only call a method, it's better to provide a reference to that method instead of lambda expression
        return String::isEmpty;
    }

    /**
     * Return a {@link Function} that accepts {@link String} and returns that string repeated n time, where n is passed
     * as function argument
     *
     * @return function that repeats Strings
     */
    public static BiFunction<String, Integer, String> stringMultiplier() {
        // Bi means two parameters (str, n), and we can implement this method using a lambda with two params
        // e.g. `(str, n) -> str.repeat(n)`, however in this case it's also better to provide a reference instead.
        // BiFunction method `apply` has two params, and String method `repeat` has only one, but when you use a static
        // method reference to a non-static method it's first parameter becomes `this` 
        return String::repeat;
    }

    /**
     * Returns a {@link Function} that converts a {@link BigDecimal} number into a {@link String} that start with
     * a dollar sign and then gets a value
     *
     * @return function that converts adds dollar sign
     */
    public static Function<BigDecimal, String> toDollarStringFunction() {
        // Function is a classic lambda, where parameter and return types are different 
        return val -> "$" + val;
    }

    /**
     * Receives two parameter that represent a range and returns a {@link Predicate<String>} that verifies if string
     * length is in the specified range. E.g. min <= length < max
     *
     * @param min min length
     * @param max max length
     * @return a string predicate
     */
    public static Predicate<String> lengthInRangePredicate(int min, int max) {
        // A lambda has one string parameter and we need to compare its length with provided min and max values.
        // Please note, that `min` and `max` must be "effectively final" if we want to use them in lambda expression. 
        // Try to uncomment the line below
        // min = 1;
        return str -> str.length() >= min && str.length() < max;
    }

    /**
     * Returns a {@link Supplier} of random integers
     *
     * @return int supplier
     */
    public static IntSupplier randomIntSupplier() {
        // This is a special Supplier for int primitive. Its method has no arguments and supplies an int value.
        return () -> ThreadLocalRandom.current().nextInt();
    }


    /**
     * Returns an {@link IntUnaryOperator} that receives an int as a bound parameter, and returns a random int
     *
     * @return int operation
     */
    public static IntUnaryOperator boundedRandomIntSupplier() {
        // IntUnaryOperator is just an UnaryOperator for int primitives. Its method accepts int and returns int.
        // So a parameter is a bound that should be used when generating a random integer 
        return bound -> ThreadLocalRandom.current().nextInt(bound);
    }

    /**
     * Returns {@link IntUnaryOperator} that calculates an integer square
     *
     * @return square operation
     */
    public static IntUnaryOperator intSquareOperation() {
        // a classical example of lambda, we use parameter and return its square 
        return x -> x * x;
    }

    /**
     * Returns a {@link LongBinaryOperator} sum operation.
     *
     * @return binary sum operation
     */
    public static LongBinaryOperator longSumOperation() {
        // LongBinaryOperator is a binary operator for long primitive. 
        // It can be done using lambda with two params like `(a, b) -> a + b` but it's better to use method reference
        return Long::sum;
    }

    /**
     * Returns a {@link ToIntFunction<String>} that converts string to integer.
     *
     * @return string to int converter
     */
    public static ToIntFunction<String> stringToIntConverter() {
        // ToIntFunction is a special form of Function that returns an int primitive. In this case we also use a simple
        // method reference instead of a longer lambda `str -> Integer.parseInt(str)` 
        return Integer::parseInt;
    }

    /**
     * Receives int parameter n, and returns a {@link Supplier} that supplies {@link IntUnaryOperator}
     * that is a function f(x) = n * x
     *
     * @param n a multiplier
     * @return a function supplier
     */
    public static Supplier<IntUnaryOperator> nMultiplyFunctionSupplier(int n) {
        // As you can see we have Supplier that supplies IntUnaryOperator, which means we'll need a nested lambda.
        // If it looks complex, you can start by implementing an inner lambda which is `x -> n * x`. Then on top of that
        // you just need to implement a supplier that supplies that lambda above.
        // Or you can start by implementing a supplier like `() -> ...` and then add inner lambda instead of three dots.
        return () -> x -> n * x;
    }

    /**
     * Returns a {@link UnaryOperator} that accepts str to str function and returns the same function composed with trim
     *
     * @return function that composes functions with trim() function
     */
    public static UnaryOperator<Function<String, String>> composeWithTrimFunction() {
        // UnaryOperator has the same parameter and return type. In our case it's a function. So our job is to use
        // that function and compose it with another function called `trim`
        // As you can see Function provides some additional default methods, and one of them is `compose`. 
        // So we have one parameter and we'll call compose, like `strFunction -> strFunction.compose(...)` then 
        // instead of three dots, we need to pass another function(lambda) trim, you can pass `s -> s.trim()`, or just
        // use a method reference to `trim`
        return strFunction -> strFunction.compose(String::trim);
    }

    /**
     * Receives a {@link Runnable} parameter, and returns a {@link Supplier<Thread>}. The thread will be started only
     * when you call supplier method {@link Supplier#get()}
     *
     * @param runnable the code you want to tun in new thread
     * @return a thread supplier
     */
    public static Supplier<Thread> runningThreadSupplier(Runnable runnable) {
        // Having a runnable you can create and start a thread. And in this case you need to implement a supplier that
        // will supply this running thread. The main point is that THREAD WON'T BE CREATED AND STARTED until 
        // method `get` of the supplier is called.
        // In this case you need to do multiple operations like create thread, call start and return it, so we need to
        // use lambda body with curly brackets and return statement
        return () -> {
            Thread t = new Thread(runnable);
            t.start();
            return t;
        };
    }

    /**
     * Returns a {@link Consumer} that accepts {@link Runnable} as a parameter and runs in a new thread.
     *
     * @return a runnable consumer
     */
    public static Consumer<Runnable> newThreadRunnableConsumer() {
        // In this case runnable is a parameter of a Consumer method. We use that parameter to create Thread 
        // and start it. Since consumer does not return any value (void), we call method `start` right within 
        // lambda expression. (Method `start` also returns `void`)  
        return runnable -> new Thread(runnable).start();
    }

    /**
     * Returns a {@link Function} that accepts an instance of {@link Runnable} and returns a {@link Supplier} of a
     * started {@link Thread} that is created from a given {@link Runnable}
     *
     * @return a function that transforms runnable into a thread supplier
     */
    public static Function<Runnable, Supplier<Thread>> runnableToThreadSupplierFunction() {
        // This method is very similar to `runningThreadSupplier`. But in this case we should implement a function
        // that accepts a runnable and then does exactly what we did before in `runningThreadSupplier`.
        return runnable -> () -> {
            Thread t = new Thread(runnable);
            t.start();
            return t;
        };
    }

    /**
     * Returns a {@link BiFunction} that has two parameters. First is {@link IntUnaryOperator} which is some integer function.
     * Second is {@link IntPredicate} which is some integer condition. And the third is {@link IntUnaryOperator} which is
     * a new composed function that uses provided predicate (second parameter of binary function) to verify its input
     * parameter. If predicate returns {@code true} it applies a provided integer function
     * (first parameter of binary function) and returns a result value, otherwise it returns an element itself.
     *
     * @return a binary function that receiver predicate and function and compose them to create a new function
     */
    public static BiFunction<IntUnaryOperator, IntPredicate, IntUnaryOperator> functionToConditionalFunction() {
        // BiFunction accepts two parameters, so you can start from implementing this part
        // `(intFunction, condition) -> ...` then the return type is `IntUnaryOperator`, and in order to implement 
        // this result `IntUnaryOperator` we need a lambda with parameter e.g. `x`, so we can add it like
        // `(intFunction, condition) -> x -> ...`. Now we should check the condition for `x` 
        // `(intFunction, condition) -> x -> condition.test(x) ? ...` if it's true, we call provided `intFunction` 
        // and return result, otherwise we just return `x`
        return (intFunction, condition) -> x -> condition.test(x) ? intFunction.applyAsInt(x) : x;
    }

    /**
     * Returns a {@link BiFunction} which first parameter is a {@link Map} where key is a function name, and value is some
     * {@link IntUnaryOperator}, and second parameter is a {@link String} which is a function name. If the map contains a
     * function by a given name then it is returned by high order function otherwise an identity() is returned.
     *
     * @return a high-order function that fetches a function from a function map by a given name or returns identity()
     */
    public static BiFunction<Map<String, IntUnaryOperator>, String, IntUnaryOperator> functionLoader() {
        // This BiFunction accepts a map of functions and a function name, so we start form this
        // `(functionMap, functionName) -> ...` then using a name we need to extract a function from map and return it
        // or return `IntUnaryOperator.identity()` if no function was found. For this use case there is a default method
        // of a class `Map` called `getOrDefault`
        return (functionMap, functionName) -> functionMap.getOrDefault(functionName, IntUnaryOperator.identity());
    }

    /**
     * Returns {@link Supplier} of {@link Supplier} of {@link Supplier} of {@link String} "WELL DONE!".
     *
     * @return a supplier instance
     */
    public static Supplier<Supplier<Supplier<String>>> trickyWellDoneSupplier() {
        // You just need to create a couple of nested lambdas like `() -> () -> ...`
        return () -> () -> () -> "WELL DONE!";
    }
}

