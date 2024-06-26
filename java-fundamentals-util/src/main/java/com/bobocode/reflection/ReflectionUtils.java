package com.bobocode.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.Predicate;

public class ReflectionUtils {

    public static Field getAccessibleFieldByPredicate(Class<?> targetClass, Predicate<Field> fieldNamePredicate) {
        var field = Arrays.stream(targetClass.getDeclaredFields())
          .filter(fieldNamePredicate)
          .findFirst()
          .orElseThrow();
        field.setAccessible(true);
        return field;
    }

}
