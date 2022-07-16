package com.bobocode;

public class DemoApp {
    public static void main(String[] args) {
        MyFunction<String, Integer> parseIntFunction = str -> Integer.parseInt(str);
//        MyFunction<String, Integer> squareStringInt = parseIntFunction.andThen(x -> x * x);
//        var result = squareStringInt.apply("4");
//        System.out.println(result);//should print 16
        // todo: uncomment the code about and realize method andThen
    }

    interface MyFunction<T, R> {
        R apply(T element);
    }

}
