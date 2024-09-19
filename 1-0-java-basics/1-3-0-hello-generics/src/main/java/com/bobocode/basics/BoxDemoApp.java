package com.bobocode.basics;

public class BoxDemoApp {
    public static void main(String[] args) {
        Box<Integer> intBox = new Box<>(123);
        Box<Integer> intBox2 = new Box<>(321);
        System.out.println((int) intBox.getValue() + (int) intBox2.getValue());

        intBox.setValue(222);
        System.out.println((int) intBox.getValue() + (int) intBox2.getValue());
    }
}
