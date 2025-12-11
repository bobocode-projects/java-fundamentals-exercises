package genericsdemo;

public class Box<T> {
    private T value;
    public Box(T value) { this.value = value; }
    public T get() { return value; }
}

public class Main {
    public static void main(String[] args) {
        Box<String> b = new Box<>("hello");
        NumberBox<Integer> nb = new NumberBox<>(42);

        Utils.print("Generic method!");
        Wildcards.printBox(b);
        Wildcards.sumNumbers(nb);

        Pair<String, Integer> p = new Pair<>("age", 30);
        SelfComparable<MyCmp> a = new MyCmp(10);
        SelfComparable<MyCmp> c = new MyCmp(20);

        System.out.println(a.compareTo(c));
    }
}

class MyCmp extends SelfComparable<MyCmp> {
    public MyCmp(int data) {
        super(data);
    }
}

public class NumberBox<T extends Number> {
    private T num;
    public NumberBox(T num) { this.num = num; }
    public double doubleValue() { return num.doubleValue(); }
}

public class Pair<K, V> {
    private K key;
    private V value;
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

public class SelfComparable<T extends SelfComparable<T>> implements Comparable<T> {
    private int data;
    public SelfComparable(int data) { this.data = data; }

    @Override
    public int compareTo(T other) {
        return Integer.compare(this.data, other.data);
    }
}

public class Wildcards {
    public static void printBox(Box<?> box) {
        System.out.println("Box contains: " + box.get());
    }

    public static void sumNumbers(NumberBox<? extends Number> nb) {
        System.out.println("Double value: " + nb.doubleValue());
    }
}

public class Utils {
    public static <T> void print(T value) {
        System.out.println(value);
    }
}
