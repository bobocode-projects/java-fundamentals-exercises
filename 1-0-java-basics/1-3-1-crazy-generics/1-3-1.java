class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

class NumericBox<T extends Number> {
    private T number;

    public void set(T number) {
        this.number = number;
    }

    public T get() {
        return number;
    }
}

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

class ComparableBox<T extends Comparable<T>> {
    private T value;

    public ComparableBox(T value) {
        this.value = value;
    }

    public boolean isGreaterThan(T other) {
        return value.compareTo(other) > 0;
    }
}

class Utils {
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}

class WildcardDemo {
    public static void showBox(Box<?> box) {
        System.out.println("Box contains: " + box.get());
    }
}

class BoundedWildcardDemo {
    public static void sumNumbers(NumericBox<? extends Number> box) {
        System.out.println("Number: " + box.get());
    }
}

public class Main {
    public static void main(String[] args) {
    }
}
