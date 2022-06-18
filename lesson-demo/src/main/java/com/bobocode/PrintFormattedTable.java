package com.bobocode;

public class PrintFormattedTable {
    public static void main(String[] args) {
        String[] input = new String[]{"1", "2", "3asdfasdfsadf", "x", "5", "6", "a", "porosiatko", "casdfasdfasdfasdf", "10", "11fasdfasdfasdfasd", "12", "13", "14", "15", "16"};
        printFormatted(input);
    }

    static void printFormatted(String[] input) {
        var maxLengthInColumn = findMaxLengthInEachColumn(input);
        for (int i = 0; i < input.length; i++) {
            var elem = input[i];
            var column = calculateColumn(i);
            var spacesCount = maxLengthInColumn[column] + 4 - elem.length();
            System.out.print(elem + " ".repeat(spacesCount));
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }
    }

    private static int[] findMaxLengthInEachColumn(String[] input) {
        var maxLengthInColumn = new int[5];
        for (int i = 0; i < input.length; i++) {
            var elem = input[i];
            var column = calculateColumn(i);
            if (elem.length() > maxLengthInColumn[column]) {
                maxLengthInColumn[column] = elem.length();
            }
        }
        return maxLengthInColumn;
    }

    private static int calculateColumn(int i) {
        return i % 5;
    }

}
