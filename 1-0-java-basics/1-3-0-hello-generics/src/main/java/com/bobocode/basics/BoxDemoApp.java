package com.bobocode.basics;

/**
 * This demo demonstrates why using Object is not safe. It's not safe because runtime casting can cause runtime
 * exceptions. We should always fail as soon as possible. So in this code we should not allow setting String
 * value at compile time, if we expect to work with integers.
 * <p>
 * todo: refactor class {@link Box} to make type parameterization safe and make this demo fail at compile time
 */
public class BoxDemoApp {
    public static void main(String[] args) {
        Box<Integer> intBox = new Box<>(123);
        Box<Integer> intBox2 = new Box<>(321);
        System.out.println((int) intBox.getValue() + intBox2.getValue());

        intBox.setValue(222);
//        intBox.setValue("abc"); // this should not be allowed
        System.out.println((int) intBox.getValue() + intBox2.getValue());
    }
}
