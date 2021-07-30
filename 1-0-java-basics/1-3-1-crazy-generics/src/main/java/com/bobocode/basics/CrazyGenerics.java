package com.bobocode.basics;

import com.bobocode.basics.util.BaseEntity;
import com.bobocode.util.ExerciseNotCompletedException;
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
    public static class Sourced { // todo: refactor class to make value generic
        private Object value;
        private String source;
    }

    /**
     * {@link Limited} is a container class that allows storing an actual value along with possible min and max values.
     * It is special form of triple. All three values have a generic type that should be a subclass of {@link Number}.
     *
     * @param <T> – actual, min and max type
     */
    @Data
    public static class Limited { // todo: refactor class to make fields generic numbers
        private final Object actual;
        private final Object min;
        private final Object max;
    }

    /**
     * {@link Converter} interface declares a typical contract of a converter. It works with two independent generic types.
     * It defines a convert method which accepts one parameter of one type and returns a converted result of another type.
     *
     * @param <T> – source object type
     * @param <R> - converted result type
     */
    public interface Converter { // todo: make interface generic
        // todo: add convert method
    }

    /**
     * {@link MaxHolder} is a container class that keeps track of the maximum value only. It works with comparable objects
     * and allows you to put new values. Every time you put a value, it is stored only if the new value is greater
     * than the current max.
     *
     * @param <T> – value type
     */
    public static class MaxHolder { // todo: refactor class to make it generic
        private Object max;

        public MaxHolder(Object max) {
            this.max = max;
        }

        /**
         * Puts a new value to the holder. A new value is stored to the max, only if it is greater than current max value.
         *
         * @param val a new value
         */
        public void put(Object val) {
            throw new ExerciseNotCompletedException(); // todo: update parameter and implement the method
        }

        public Object getMax() {
            return max;
        }
    }

    /**
     * {@link StrictProcessor} defines a contact of a processor that can process only objects that are {@link Serializable}
     * and {@link Comparable}.
     *
     * @param <T> – the type of objects that can be processed
     */
    interface StrictProcessor { // todo: make it generic
        void process(Object obj);
    }

    /**
     * {@link CollectionRepository} defines a contract of a runtime store for entities based on any {@link Collection}.
     * It has methods that allow to save new entity, and get whole collection.
     *
     * @param <T> – a type of the entity that should be a subclass of {@link BaseEntity}
     * @param <C> – a type of any collection
     */
    interface CollectionRepository { // todo: update interface according to the javadoc
        void save(Object entity);

        Collection<Object> getEntityCollection();
    }

    /**
     * {@link ListRepository} extends {@link CollectionRepository} but specifies the underlying collection as
     * {@link java.util.List}.
     *
     * @param <T> – a type of the entity that should be a subclass of {@link BaseEntity}
     */
    interface ListRepository { // todo: update interface according to the javadoc
    }

    static class ConsoleUtil {
        /**
         * A util method that allows to print a dashed list of elements
         *
         * @param list
         */
        void print(List<Integer> list) { // todo: refactor it so the list of any type can be printed, not only integers
            list.forEach(element -> System.out.println(" – " + element));
        }
    }

    /**
     * {@link ComparableCollection} is a {@link Collection} that can be compared by size. It extends a {@link Collection}
     * interface and {@link Comparable} interface, and provides a default implementation of a compareTo method that
     * compares collections sizes.
     * <p>
     * Please note that size does not depend on the elements type, so it is allowed to compare collections of different
     * element types.
     *
     * @param <E> a type of collection elements
     */
    interface ComparableCollection { // todo: refactor it to make generic and provide a default impl of compareTo

    }

    /**
     * {@link PersistenceUtil} is a util class that provides persistence-related tools.
     */
    static class PersistenceUtil {
        /**
         * Util method that check if provided collection has new entities. An entity is any object
         * that extends {@link BaseEntity}. A new entity is an entity that does not have an id assigned.
         * (In other word, which id equals null).
         *
         * @param entities provided collection of entities
         * @return true if at least one of the elements has null id
         */
        public static boolean hasNewEntities(Collection<BaseEntity> entities) {
            throw new ExerciseNotCompletedException(); // todo: param and implement method
        }

        /**
         * Util method that checks if a provided collection of entities is valid. A validation criteria can be different
         * for different cases, so it is passed as second parameter.
         *
         * @param entities            provided collection of entities
         * @param validationPredicate criteria for validation
         * @return true if all entities fit validation criteria
         */
        public static boolean isValidCollection() {
            throw new ExerciseNotCompletedException(); // todo: add method parameters and implement the logic
        }

        /**
         * hasDuplicates is a generic util method checks if a list of entities contains target entity more than once.
         * In other words, it checks if target entity has duplicates in the provided list. A duplicate is an entity that
         * has the same UUID.
         *
         * @param entities     given list of entities
         * @param targetEntity a target entity
         * @param <T>          entity type
         * @return true if entities list contains target entity more than once
         */
        public static boolean hasDuplicates() {
            throw new ExerciseNotCompletedException(); // todo: update method signature and implement it
        }

        /**
         * findMostRecentlyCreatedEntity is a generic util method that accepts a collection of entities and returns the
         * one that is the most recently created. If collection is empty, it throws {@link java.util.NoSuchElementException}
         *
         * @param entities provided collection of entities
         * @param <T>      entity type
         * @return an entity from the given collection that has the max createdOn value
         */
        // todo: create a method according to the JavaDoc
    }


}
