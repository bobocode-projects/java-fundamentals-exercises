package com.bobocode.fp;

import com.bobocode.fp.exception.InvalidFunctionNameException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * FunctionMap is an API that allows you to store and retrieve functions by string name. FunctionMap are stored in a
 * HashMap, where the key is a function name, and the value is a Function<T,R> instance
 */
public class FunctionMap<T, R> {
    private Map<String, Function<T, R>> functionMap;

    FunctionMap() {
        functionMap = new HashMap<>();
    }

    public void addFunction(String name, Function<T, R> function) {
        functionMap.put(name, function);
    }

    public Function<T, R> getFunction(String name) {
        if (functionMap.containsKey(name)) {
            return functionMap.get(name);
        } else {
            throw new InvalidFunctionNameException(name);
        }
    }
}