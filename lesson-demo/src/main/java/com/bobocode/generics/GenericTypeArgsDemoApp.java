package com.bobocode.generics;

import lombok.SneakyThrows;

import java.lang.reflect.ParameterizedType;

public class GenericTypeArgsDemoApp {
    @SneakyThrows
    public static void main(String[] args) {
        for (var field : Container.class.getDeclaredFields()) {
            var paramType = (ParameterizedType) field.getGenericType();
            var typeArgument = paramType.getActualTypeArguments()[0];
            System.out.println(field.getName() + " = " + field.getType() + " - " + typeArgument);
        }
        var field = Container.class.getDeclaredField("integerList");
        System.out.println(field.getType());

    }
}
