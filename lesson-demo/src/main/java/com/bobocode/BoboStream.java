package com.bobocode;

import java.util.function.Consumer;
import java.util.function.Function;

public interface BoboStream<T> {
    <R> BoboStream<R> map(Function<? super T, ? extends R> mapperFunction);

    void forEach(Consumer<? super T> consumer);
}
