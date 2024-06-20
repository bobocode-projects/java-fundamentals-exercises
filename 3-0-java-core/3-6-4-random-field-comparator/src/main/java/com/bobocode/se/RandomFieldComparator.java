package com.bobocode.se;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import lombok.SneakyThrows;

/**
 * A generic comparator that is comparing a random field of the given class. The field is either primitive or
 * {@link Comparable}. It is chosen during comparator instance creation and is used for all comparisons.
 * <p>
 * If no field is available to compare, the constructor throws {@link IllegalArgumentException}
 *
 * @param <T> the type of the objects that may be compared by this comparator
 *<p><p>
 *  <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com">visit our website</a></strong>
 *  <p>
 *
 * @author Stanislav Zabramnyi
 */
public class RandomFieldComparator<T> implements Comparator<T> {

    private final Class<T> targetType;
    private final Field fieldToCompare;

    public RandomFieldComparator(Class<T> targetType) {
        this.targetType = requireNonNull(targetType);
        this.fieldToCompare = chooseFieldToCompare(targetType);
    }

    /**
     * Compares two objects of the class T by the value of the field that was randomly chosen. It allows null values
     * for the fields, and it treats null value grater than a non-null value.
     *
     * @param o1
     * @param o2
     * @return positive int in case of first parameter {@param o1} is greater than second one {@param o2},
     *         zero if objects are equals,
     *         negative int in case of first parameter {@param o1} is less than second one {@param o2}.
     */
    @Override
    public int compare(T o1, T o2) {
        Objects.requireNonNull(o1);
        Objects.requireNonNull(o2);
        return compareFieldValues(o1, o2);
    }

    /**
     * Returns the name of the randomly-chosen comparing field.
     */
    public String getComparingFieldName() {
        return fieldToCompare.getName();
    }

    /**
     * Returns a statement "Random field comparator of class '%s' is comparing '%s'" where the first param is the name
     * of the type T, and the second parameter is the comparing field name.
     *
     * @return a predefined statement
     */
    @Override
    public String toString() {
        return String.format("Random field comparator of class '%s' is comparing '%s'", targetType.getSimpleName(),
                getComparingFieldName());
    }

    private Field chooseFieldToCompare(Class<T> targetType) {
        return Arrays.stream(targetType.getDeclaredFields())
                .filter(f -> Comparable.class.isAssignableFrom(f.getType()) || f.getType().isPrimitive())
                .findAny().orElseThrow(() -> new IllegalArgumentException("There are no fields available to compare"));
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    private <U extends Comparable<? super U>> int compareFieldValues(T o1, T o2) {
        fieldToCompare.setAccessible(true);
        var value1 = (U) fieldToCompare.get(o1);
        var value2 = (U) fieldToCompare.get(o2);
        Comparator<U> comparator = Comparator.nullsLast(Comparator.naturalOrder());
        return comparator.compare(value1, value2);
    }
}
