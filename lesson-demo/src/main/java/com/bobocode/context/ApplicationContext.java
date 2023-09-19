package com.bobocode.context;

import com.bobocode.annotation.Component;
import com.bobocode.util.ExerciseNotCompletedException;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple version of application context that accepts a base package, scans the package to find all classes marked with
 *
 * @Component, and creates corresponding instances that form the context.
 */
public class ApplicationContext {
    private Map<String, Object> nameToBeanMap = new HashMap<>();

    /**
     * Creates a register of beans based on the given package
     * @param basePackage
     */
    public ApplicationContext(String basePackage) {
        var reflections = new Reflections(basePackage);
        for (var beanTypes : reflections.getTypesAnnotatedWith(Component.class)) {
            throw new ExerciseNotCompletedException();
        }

    }

    /**
     * Returns a bean of the given type
     * @param beanType
     * @return
     * @param <T>
     */
    public <T> T getBean(Class<T> beanType) {
        throw new ExerciseNotCompletedException(); // todo
    }
}
