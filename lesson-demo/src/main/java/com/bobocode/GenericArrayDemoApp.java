package com.bobocode;

import java.util.List;

public class GenericArrayDemoApp {
    public static void main(String[] args) {
        var minValue = min(List.of(4, 3, 5), List.of(5, 4, 2, 7));
        System.out.println(minValue);
    }

    static int min(List<Integer>... lists) {
        Object[] objects = lists;
        objects[1] = List.of("hello");
        var min = Integer.MAX_VALUE;
        for (var list : lists) {
            for (var e : list) {
                if (e < min) {
                    min = e;
                }
            }
        }
        return min;
    }
}
