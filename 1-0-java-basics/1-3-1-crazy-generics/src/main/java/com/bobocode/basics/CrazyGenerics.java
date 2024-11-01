package com.bobocode.basics;

import com.bobocode.basics.util.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;

/**
 * {@link CrazyGenerics} is an exercise class. It consists of classes, interfaces and methods that should be updated
 * using generics.
 * <p>
 * TODO: go step by step from top to bottom. Read the java doc, write code and run CrazyGenericsTest to verify your impl
 * <p>
 * Hint: in some cases you will need to refactor the code, like replace {@link Object} with a generic type. In order
 * cases you will need to add new fields, create new classes, or add new methods. Always try to read java doc and update
 * the code according to it.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com">visit our website</a></strong>
 * <p>
 *
 * @author Taras Boychuk
 */
public class CrazyGenerics {
    /**
     * {@link Sourced} is a container class that allows storing any object along with the source of that data.
     * The value type can be specified by a type parameter "T".
     *
     * @param <T> – value type
     */
    @Data
    public static class Sourced<T> { // todo: refactor class to introduce type parameter and make value generic
        private T value;
        private String source;
    }

    /**
     * {@link Limited} is a container class that allows storing an actual value along with possible min and max values.
     * It is special form of triple. All three values have a generic type that should be a subclass of {@link Number}.
     *
     * @param <T> – actual, min and max type
     */
    @Data
    public static class Limited<T extends Number> {
        // todo: refactor class to introduce type param bounded by number and make fields generic numbers
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
    public interface Converter<T, R> { // todo: introduce type parameters
        // todo: add convert method
        R convert(T param);
    }

    /**
     * {@link MaxHolder} is a container class that keeps track of the maximum value only. It works with comparable objects
     * and allows you to put new values. Every time you put a value, it is stored only if the new value is greater
     * than the current max.
     *
     * @param <T> – value type
     */
    public static class MaxHolder<T extends Comparable<? super T>> { // todo: refactor class to make it generic
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
            } // todo: update parameter and implement the method
        }

        public T getMax() {
            return max;
        }
    }

    /**
     * {@link StrictProcessor} defines a contract of a processor that can process only objects that are {@link Serializable}
     * and {@link Comparable}.
     *
     * @param <T> – the type of objects that can be processed
     */
    interface StrictProcessor<T extends Serializable & Comparable<? super T>> { // todo: make it generic
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
     * {@link List}.
     *
     * @param <T> – a type of the entity that should be a subclass of {@link BaseEntity}
     */
    interface ListRepository<T extends BaseEntity> extends CollectionRepository<T, List<T>> { // todo: update interface according to the javadoc
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
    interface ComparableCollection<E> extends Collection<E>, Comparable<Collection<?>> { // todo: refactor it to make generic and provide a default impl of compareTo
        default int compareTo(Collection<?> other) {
            return Integer.compare(this.size(), other.size());
        }
    }

    /**
     * {@link CollectionUtil} is an util class that provides various generic helper methods.
     */
    static class CollectionUtil {
        static final Comparator<BaseEntity> CREATED_ON_COMPARATOR = Comparator.comparing(BaseEntity::getCreatedOn);

        /**
         * An util method that allows to print a dashed list of elements
         *
         * @param list
         */
        public static void print(List<?> list) {
            // todo: refactor it so the list of any type can be printed, not only integers
            list.forEach(element -> System.out.println(" – " + element));
        }

        /**
         * Util method that check if provided collection has new entities. An entity is any object
         * that extends {@link BaseEntity}. A new entity is an entity that does not have an id assigned.
         * (In other word, which id value equals null).
         *
         * @param entities provided collection of entities
         * @return true if at least one of the elements has null id
         */
        public static boolean hasNewEntities(Collection<? extends BaseEntity> entities) {
            for (BaseEntity e : entities) {
                if (e.getUuid() == null) {
                    return true; // todo: refactor parameter and implement method
                }
            }
            return false; // todo: refactor parameter and implement method
        }

        /**
         * Util method that checks if a provided collection of entities is valid. An entity is any subclass of
         * a {@link BaseEntity} A validation criteria can be different for different cases, so it is passed
         * as second parameter.
         *
         * @param entities            provided collection of entities
         * @param validationPredicate criteria for validation
         * @return true if all entities fit validation criteria
         */
        public static boolean isValidCollection(Collection<? extends BaseEntity> entities, Predicate<? super BaseEntity> validationPredicate) {
            return entities.stream()
                    .allMatch(validationPredicate);

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
        public static <T extends BaseEntity> boolean hasDuplicates(List<T> entities, T targetEntity) {
            return entities.stream()
                    .filter(entity -> entity.getUuid().equals(targetEntity.getUuid()))
                    .count() > 1;
        }

        /**
         * findMax is a generic util method that accepts an {@link Iterable} and {@link Comparator} and returns an
         * optional object, that has maximum "value" based on the given comparator.
         *
         * @param elements   provided iterable of elements
         * @param comparator an object that will be used to compare elements
         * @param <T>        type of elements
         * @return optional max value
         */
        // todo: create a method and implement its logic manually without using util method from JDK

        public  static <T> Optional<T> findMax(Iterable<T> elements, Comparator<? super T> comparator) {
            if (elements.iterator().hasNext()) {
                T max = elements.iterator().next();
                for (T e : elements) {
                    if (comparator.compare(e, max) > 0) {
                        max = e;
                    }
                }
                return Optional.of(max);
            }
            return Optional.empty();
        }

        /**
         * findMostRecentlyCreatedEntity is a generic util method that accepts a collection of entities and returns the
         * one that is the most recently created. If collection is empty,
         * it throws {@link NoSuchElementException}.
         * <p>
         * This method reuses findMax method and passes entities along with prepare comparator instance,
         * that is stored as constant CREATED_ON_COMPARATOR.
         *
         * @param entities provided collection of entities
         * @param <T>      entity type
         * @return an entity from the given collection that has the max createdOn value
         */
        // todo: create a method according to JavaDoc and implement it using previous method

        public static <T extends BaseEntity> T findMostRecentlyCreatedEntity(Collection<T> entities) {
            return findMax(entities, CREATED_ON_COMPARATOR).orElseThrow();
        }


        /**
         * An util method that allows to swap two elements of any list. It changes the list so the element with the index
         * i will be located on index j, and the element with index j, will be located on the index i.
         * Please note that in order to make it convenient and simple, it DOES NOT declare any type parameter.
         *
         * @param elements a list of any given type
         * @param i        index of the element to swap
         * @param j        index of the other element to swap
         */
        public static void swap(List<?> elements, int i, int j) {
            swapHelper(elements, i, j);
        }

        private static <T> void swapHelper(List<T> elements, int i, int j) {
            T temp = elements.get(i);
            elements.set(i, elements.get(j));
            elements.set(j, temp);
        }

    }
}