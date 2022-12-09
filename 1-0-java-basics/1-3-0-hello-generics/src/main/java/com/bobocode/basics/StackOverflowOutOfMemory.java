package com.bobocode.basics;

public class StackOverflowOutOfMemory {

    public static void main(String[] args) {
        try {
            throwStackOverFlow();
        } catch (StackOverflowError er) {
            System.out.println("StackOverflowError caught");
        }
        try {
            throwOutOfMemory();
        } catch (OutOfMemoryError er) {
            System.out.println("OutOfMemoryError caught");
        }
    }

    private static void throwStackOverFlow() {
        throwStackOverFlow();
    }

    private static void throwOutOfMemory() {
        byte[] bytes = new byte[Integer.MAX_VALUE];
    }
}
