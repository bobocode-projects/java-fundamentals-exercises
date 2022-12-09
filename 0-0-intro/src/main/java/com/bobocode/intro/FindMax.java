package com.bobocode.intro;

public class FindMax {
    public static void main(String[] args) {
        System.out.println(findMax(new int[]{1, 2, 23, 24, 98, 4}));
        System.out.println(findMax(new int[]{}));
    }

    public static int findMax(int[] input) {
        if (0 == input.length) {
            throw new IllegalArgumentException("Array is empty");
        }
        int max = input[0];
        for (int i = 1; i < input.length; i++) {
            if (input[i] > max) {
                max = input[i];
            }
        }
        return max;
    }
}
