package com.bobocode.basics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListPractice {
    public static void main(String[] args) {
        // 1
        ArrayList<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        colors.add("White");
        System.out.println("1. Initial list: " + colors);

        // 2
        System.out.println("2. Iterating:");
        for (String color : colors) {
            System.out.println(" - " + color);
        }

        // 3
        colors.add(0, "Black");
        System.out.println("3. After adding Black at index 0: " + colors);

        // 4
        String element = colors.get(2);
        System.out.println("4. Element at index 2: " + element);

        // 5
        colors.set(1, "Yellow");
        System.out.println("5. After setting index 1 to Yellow: " + colors);

        // 6
        colors.remove(2);
        System.out.println("6. After removing 3rd element: " + colors);

        // 7
        boolean containsWhite = colors.contains("White");
        System.out.println("7. Contains 'White'? " + containsWhite);
        int index = colors.indexOf("White");
        System.out.println("   Index of 'White': " + index);

        // 8
        Collections.sort(colors);
        System.out.println("8. Sorted list: " + colors);

        // 9
        ArrayList<String> copyList = new ArrayList<>(colors);
        Collections.copy(copyList, colors);
        System.out.println("9. Copied list: " + copyList);

        // 10
        Collections.reverse(colors);
        System.out.println("10. Reversed list: " + colors);

        // 11
        boolean isEqual = colors.equals(copyList);
        System.out.println("11. Is equal to copy? " + isEqual);

        // 12
        colors.clear();
        System.out.println("12. List is cleared.");

        // 13
        System.out.println("13. Is list empty? " + colors.isEmpty());

        // 14
        colors.ensureCapacity(20);
        System.out.println("14. Capacity increased to holding at least 20 elements");

        // 15
        colors.trimToSize();
        System.out.println("15. Trimming capacity to size: " + colors.size());
    }
}
