package com.bobocode.basics;

import com.bobocode.basics.util.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * {@link CrazyGenerics} is an exercise class. It consists of classes, interfaces and methods that should be updated
 * using generics.
 * <p>
 * todo: go step by step from top to bottom. Read the java doc, write code and run CrazyGenericsTest to verify your impl
 * <p>
 * Hint: in some cases you will need to refactor the code, like replace {@link Object} with a generic type. In order
 * cases you will need to add new fields, create new classes, or add new methods. Always try to read java doc and update
 * the code according to it.
 */
public class CrazyGenerics {
    /**
     * {@link Sourced} is a container class that allows storing any object along with the source of that data.
     * The value type can be specified by a type parameter "T".
     *
     * @param <T> – value type
     */
    @Data
    public static class Sourced<T> { // todo: refactor class to make value generic
        private T value;
        private String source;
    }

    /**
     * {@link Bounded} is a container class that allows storing an actual value along with possible min and max values.
     * It is special form of triple. All three values have a generic type that should be a subclass of {@link Number}.
     *
     * @param <T> – actual, min and max type
     */
    @Data
    public static class Bounded<T extends Number> { // todo: refactor class to make fields generic numbers
        private final T actual;
        private final T min;
        private final T max;
    }

    /**
     * {@link Converter} interface declares a typical contract of a converter. It works with two independent generic types.
     * It defines a convert method which accepts one parameter of one type and returns a converted result of another type.
     *
     * @param <T> – source object type
     * @param <R> - converted result type
     */
    public interface Converter<T, R> { // todo: make interface generic
        R convert(T obj);
    }

    /**
     * {@link MaxHolder} is a container class that keeps track of the maximum value only. It works with comparable objects
     * and allows you to put new values. Every time you put a value, it is stored only if the new value is greater
     * than the current max.
     *
     * @param <T> – value type
     */
    public static class MaxHolder<T extends Comparable<T>> { // todo: refactor class to make it generic
        private T max;

        public MaxHolder(T max) {
            this.max = max;
        }

        /**
         * Puts a new value to the holder. A new value is stored to the max, only if it is greater than current max value.
         *
         * @param val a new value
         */
        public void put(T val) {
            if (val.compareTo(max) > 0) {
                max = val;
            }
        }

        public T getMax() {
            return max;
        }
    }

    /**
     * {@link StrictProcessor} defines a contact of a processor that can process only objects that are {@link Serializable}
     * and {@link Comparable}.
     *
     * @param <T> – the type of objects that can be processed
     */
    interface StrictProcessor<T extends Serializable & Comparable<T>> { // todo: make it generic
        void process(T obj);
    }

    /**
     * {@link CollectionRepository} defines a contract of a runtime store for entities based on any {@link Collection}.
     * It has methods that allow to save new entity, and get whole collection.
     *
     * @param <T> – a type of the entity that should be a subclass of {@link BaseEntity}
     * @param <C> – a type of any collection
     */
    interface CollectionRepository<T extends BaseEntity, C extends Collection<T>> { // todo: update interface according to the javadoc
        void save(T entity);

        C getEntityCollection();
    }

    /**
     * {@link ListRepository} extends {@link CollectionRepository} but specifies the underlying collection as
     * {@link java.util.List}.
     *
     * @param <T> – a type of the entity that should be a subclass of {@link BaseEntity}
     */
    interface ListRepository<T extends BaseEntity> extends CollectionRepository<T, List<T>> { // todo: update interface according to the javadoc
    }

}
