package com.bobocode;

import java.util.Stack;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class FunctionalProgrammingDemoApp {
    public static void main(String[] args) {
        Stack<String> wordsStack = Stream.of("Hello", "Hey")
                .collect(toStack());

        while (!wordsStack.isEmpty()) {
            System.out.println(wordsStack.pop());
        }

    }

    private static <T> Collector<T, ?, Stack<T>> toStack() {
        return null;
        // todo:
        //        return Collector.of(
        //        
        //);
    }

}
