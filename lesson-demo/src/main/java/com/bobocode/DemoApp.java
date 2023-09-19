package com.bobocode;

import lombok.SneakyThrows;

public class DemoApp {
    public static void main(String[] args) {
        var person = new Person("John", "Gold", 43);
        var personJson = covertToJson(person);
        System.out.println(personJson); // should print {"firstName": "John", "lastName": "Gold", "age": 43}
    }

    /**
     * Accepts and arbitrary object and returns a JSON string representing all object's data.
     *
     * @param object
     * @return JSON string
     */
    @SneakyThrows
    private static String covertToJson(Object object) {
        StringBuilder builder = new StringBuilder();
        // todo:
        return builder.toString();
    }


    static class Person {
        private String firstName;
        private String lastName;
        private int age;

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }
    }

}
