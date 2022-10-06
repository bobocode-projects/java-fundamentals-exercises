package com.bobocode;

import java.util.List;

public class DemoApp {
    public static void main(String[] args) {
        List<String> wordList = List.of("Hello", "Hey");
        System.out.println(wordList.getClass().getComponentType()); // 👈
        
        String[] wordArray = wordList.toArray(new String[1]);
        System.out.println(wordArray.getClass().getComponentType());
    }

//    public static <T> T[] createGenericArray(int size) {
//        return new T[size]; // it's not possible to create a generic array
//    }
    
}
