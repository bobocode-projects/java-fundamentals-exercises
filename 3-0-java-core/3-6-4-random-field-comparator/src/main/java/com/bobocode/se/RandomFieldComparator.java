package com.bobocode.se;

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
 *
 * @author Stanislav Zabramnyi
 */
public class RandomFieldComparator<T> implements Comparator<T> {

    private final Class<T> targetType;
    private final Field fieldToCompare;

    public RandomFieldComparator(Class<T> targetType) {
        Objects.requireNonNull(targetType);
        this.targetType = targetType;
        this.fieldToCompare = chooseFieldToCompare(targetType);
    }

    /**
     * Compares two objects of the class T by the value of the field that was randomly chosen. It allows null values
     * for the fields, and it treats null value grater than a non-null value.
     *
     * @param o1
     * @param o2
     * @return 1 in case of first parameter {@param o1} is greater than second one {@param o2},
     *         0 if objects are equals,
     *        -1 in case of first parameter {@param o1} is less than second one {@param o2}.
     */
    @Override
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public int compare(T o1, T o2) {
        Objects.requireNonNull(o1);
        Objects.requireNonNull(o2);
        fieldToCompare.setAccessible(true);

        var field1 = (Comparable) fieldToCompare.get(o1);
        var field2 = (Comparable) fieldToCompare.get(o2);

        if (field1 == null && field2 == null) {
            return 0;
        } else if (field1 == null) {
            return 1;
        } else if (field2 == null) {
            return -1;
        } else {
            return field1.compareTo(field2);
        }
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
                .filter(f -> isComparableType(f.getType()))
                .findAny().orElseThrow(() -> new IllegalArgumentException("There are no fields available to compare"));
    }

    private boolean isComparableType(Class<?> type) {
        return Arrays.asList(type.getInterfaces()).contains(Comparable.class);
    }
}
