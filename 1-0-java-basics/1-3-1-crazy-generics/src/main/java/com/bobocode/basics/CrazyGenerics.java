package com.bobocode.basics;

import com.bobocode.basics.util.BaseEntity;
import com.bobocode.util.ExerciseNotCompletedException;
import lombok.Data;

import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;
public class CrazyGenerics {

    @Data
    public static class Sourced<T> {
        private T value;
        private String source;
    }

    @Data
    public static class Limited<T extends Number> {
        private final T actual;
        private final T min;
        private final T max;
    }

    public interface Converter<T, R> {
        R convert(T obj);
    }

    public static class MaxHolder<T extends Comparable<? super T>> {
        private T max;

        public MaxHolder(T max) {
            this.max = max;
        }

        public void put(T val) {
            if (val == null) {
                return;
            }
            if (this.max == null) {
                this.max = val;
                return;
            }
            if (val.compareTo(this.max) > 0) {
                this.max = val;
            }
        }

        public T getMax() {
            return max;
        }
    }

    interface StrictProcessor<T extends Serializable & Comparable<? super T>> {
        void process(T obj);
    }

    interface CollectionRepository<T extends BaseEntity, C extends Collection<T>> {
        void save(T entity);

        C getEntityCollection();
    }

    interface ListRepository<T extends BaseEntity> extends CollectionRepository<T, List<T>> {
    }

    interface ComparableCollection<E> extends Collection<E>, Comparable<Collection<?>> {
        @Override
        default int compareTo(Collection<?> o) {
            return Integer.compare(this.size(), o.size());
        }
    }

    static class CollectionUtil {
        static final Comparator<BaseEntity> CREATED_ON_COMPARATOR = Comparator.comparing(BaseEntity::getCreatedOn);

        public static void print(List<?> list) {
            list.forEach(element -> System.out.println(" – " + element));
        }

        public static boolean hasNewEntities(Collection<? extends BaseEntity> entities) {
            if (entities == null) {
                return false;
            }
            for (BaseEntity e : entities) {
                if (e.getUuid() == null) {
                    return true;
                }
            }
            return false;
        }

        public static boolean isValidCollection(Collection<? extends BaseEntity> entities,
                                                Predicate<? super BaseEntity> validationPredicate) {
            if (entities == null) {
                return true;
            }
            for (BaseEntity e : entities) {
                if (!validationPredicate.test(e)) {
                    return false;
                }
            }
            return true;
        }

        public static <T extends BaseEntity> boolean hasDuplicates(List<? extends T> entities, T targetEntity) {
            if (entities == null || targetEntity == null) {
                return false;
            }
            int count = 0;
            UUID targetUuid = targetEntity.getUuid();
            for (T e : entities) {
                if (Objects.equals(e.getUuid(), targetUuid)) {
                    count++;
                    if (count > 1) {
                        return true;
                    }
                }
            }
            return false;
        }

        public static <T> Optional<T> findMax(Iterable<? extends T> elements, Comparator<? super T> comparator) {
            if (elements == null) {
                return Optional.empty();
            }
            Iterator<? extends T> it = elements.iterator();
            if (!it.hasNext()) {
                return Optional.empty();
            }
            T max = it.next();
            while (it.hasNext()) {
                T element = it.next();
                if (comparator.compare(element, max) > 0) {
                    max = element;
                }
            }
            return Optional.of(max);
        }

        public static <T extends BaseEntity> T findMostRecentlyCreatedEntity(Collection<? extends T> entities) {
            return findMax(entities, (Comparator<? super T>) CREATED_ON_COMPARATOR)
                    .orElseThrow(NoSuchElementException::new);
        }

        public static void swap(List<?> elements, int i, int j) {
            Objects.checkIndex(i, elements.size());
            Objects.checkIndex(j, elements.size());
            swapHelper(elements, i, j);
        }


        private static <T> void swapHelper(List<T> list, int i, int j) {
            T tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
        }

    }
}

