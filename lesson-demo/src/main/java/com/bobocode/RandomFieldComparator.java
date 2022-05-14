package com.bobocode;

import java.util.Comparator;

/**
 * A generic comparator that is comparing a random field of the given class. The field is either primitive or
 * {@link Comparable}. It is chosen during comparator instance creation and is used for all comparisons.
 * <p>
 * If no field is available to compare, the constructor throws {@link IllegalArgumentException}
 *
 * @param <T> the type of the objects that may be compared by this comparator
 */
public class RandomFieldComparator<T> implements Comparator<T> {

    public RandomFieldComparator(Class<T> targetType) {
        throw new UnsupportedOperationException("This method should be implemented"); // todo:
    }

    /**
     * Compares two objects of the class T by the value of the field that was randomly chosen. It allows null values
     * for the fields, and it treats null value grater than a non-null value.
     *
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(T o1, T o2) {
        throw new UnsupportedOperationException("This method should be implemented"); // todo:
    }

    /**
     * Returns a statement "Random field comparator of class '%s' is comparing '%s'" where the first param is the name
     * of the type T, and the second parameter is the comparing field name.
     *
     * @return a predefined statement
     */
    @Override
    public String toString() {
        throw new UnsupportedOperationException("This method should be implemented"); // todo:
    }
}
