package com.bobocode.basics;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Box<T> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

}
