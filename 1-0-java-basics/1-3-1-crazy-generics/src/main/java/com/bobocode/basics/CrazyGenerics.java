package com.bobocode.basics;

import com.bobocode.basics.util.BaseEntity;

import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;

/**
 * {@link CrazyGenerics} is an exercise class with generics refactoring.
 */

public class CrazyGenerics {

    /**
     * Sourced: container for any value with its source.
     */
    public static class Sourced<T> {
        private T value;
        private String source;

        public Sourced(T value, String source) {
            this.value = value;
            this.source = source;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }

    /**
     * Limited: container for a number with min/max values.
     */
    public static class Limited<T extends Number> {
        private final T actual;
        private final T min;
        private final T max;

        public Limited(T actual, T min, T max) {
            this.actual = actual;
            this.min = min;
            this.max = max;
        }

        public T getActual() {
            return actual;
        }

        public T getMin() {
            return min;
        }

        public T getMax() {
            return max;
        }
    }

    /**
     * Converter: convert from T to R.
     */
    public interface Converter<T, R> {
        R convert(T source);
    }

    /**
     * MaxHolder: keeps track of maximum Comparable value.
     */
    public static class MaxHolder<T extends Comparable<T>> {
        private T max;

        public MaxHolder(T max) {
            this.max = max;
        }

        public void put(T val) {
            if (max == null || val.compareTo(max) > 0) {
                max = val;
            }
        }

        public T getMax() {
            return max;
        }
    }

    /**
     * StrictProcessor: processes only Serializable & Comparable objects.
     */
    interface StrictProcessor<T extends Serializable & Comparable<T>> {
        void process(T obj);
    }

    /**
     * CollectionRepository: store entities in any collection.
     */
    interface CollectionRepository<T extends BaseEntity, C extends Collection<T>> {
        void save(T entity);

        C getEntityCollection();
    }

    /**
     * ListRepository: repository specialized for List.
     */
    interface ListRepository<T extends BaseEntity> extends CollectionRepository<T, List<T>> {
    }

    /**
     * ComparableCollection: compares by size.
     */
    interface ComparableCollection<E> extends Collection<E>, Comparable<Collection<?>> {
        @Override
        default int compareTo(Collection<?> o) {
            return Integer.compare(this.size(), o.size());
        }
    }

    /**
     * CollectionUtil: utility methods for collections.
     */
    static class CollectionUtil {

        static final Comparator<BaseEntity> CREATED_ON_COMPARATOR =
                Comparator.comparing(BaseEntity::getCreatedOn);

        public static <T> void print(List<T> list) {
            list.forEach(e -> System.out.println(" – " + e));
        }

        public static <T extends BaseEntity> boolean hasNewEntities(Collection<T> entities) {
            return entities.stream().anyMatch(e -> e.getId() == null);
        }

        public static <T extends BaseEntity> boolean isValidCollection(Collection<T> entities, Predicate<T> validationPredicate) {
            return entities.stream().allMatch(validationPredicate);
        }

        public static <T extends BaseEntity> boolean hasDuplicates(List<T> entities, T targetEntity) {
            return entities.stream().filter(e -> e.getUuid().equals(targetEntity.getUuid())).count() > 1;
        }

        public static <T> Optional<T> findMax(Iterable<T> elements, Comparator<? super T> comparator) {
            Iterator<T> it = elements.iterator();
            if (!it.hasNext()) return Optional.empty();
            T max = it.next();
            while (it.hasNext()) {
                T current = it.next();
                if (comparator.compare(current, max) > 0) {
                    max = current;
                }
            }
            return Optional.of(max);
        }

        public static <T extends BaseEntity> T findMostRecentlyCreatedEntity(Collection<T> entities) {
            return findMax(entities, CREATED_ON_COMPARATOR)
                    .orElseThrow(NoSuchElementException::new);
        }

        public static <T> void swap(List<T> elements, int i, int j) {
            Objects.checkIndex(i, elements.size());
            Objects.checkIndex(j, elements.size());
            T tmp = elements.get(i);
            elements.set(i, elements.get(j));
            elements.set(j, tmp);
        }
    }
}
