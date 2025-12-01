package com.bobocode.basics;

public class BoxDemoApp {
    public static void main(String[] args) {
        Box<Integer> intBox = new Box<>(123);
        Box<Integer> intBox2 = new Box<>(321);

        System.out.println(intBox.getValue() + intBox2.getValue());

        intBox.setValue(222);
        // intBox.setValue("abc"); // ❌ Тепер так не можна

        System.out.println(intBox.getValue() + intBox2.getValue());
    }
}
