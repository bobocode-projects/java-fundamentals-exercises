package com.bobocode;

public class FunctionCompositionDemoApp {
    public static void main(String[] args) {
        MyFunction<String, Integer> parseIntFunction = str -> Integer.parseInt(str);
        MyFunction<String, Integer> squareStringInt = parseIntFunction.andThen(x -> x * x);
        var result = squareStringInt.apply("4");
        System.out.println(result);//should print 16
    }

    interface MyFunction<T, R> {
        R apply(T element);

        default <U> MyFunction<T, U> andThen(MyFunction<R, U> afterFunction) {
            return x -> afterFunction.apply(apply(x));
        }
    }

}
