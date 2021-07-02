package com.bobocode.basics;

/**
 * {@link Box} is a container class that can store a value of any given type. Using Object as a field type
 * is flexible, because we can store anything we want there. But it is not safe, because it requires runtime casting
 * and there is no guarantee that we know the type of the stored value.
 * <p>
 * todo: refactor this class so it uses generic type "T" and run {@link com.bobocode.basics.BoxTest} to verify it
 */
public class Box<T> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
