package com.bobocode.cs;

import lombok.SneakyThrows;
import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.reflect.Modifier.isStatic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A Reflection-based step by step test for a {@link HashTable} class. PLEASE NOTE that Reflection API should not be used
 * for testing a production code. We use it for learning purposes only!
 *
 * @author Taras Boychuk
 */
@TestClassOrder(OrderAnnotation.class)
@DisplayName("HashTable Test")
class HashTableTest {

    private HashTable<String, Integer> hashTable = new HashTable<>();

    @Nested
    @Order(1)
    @DisplayName("1. Node Test")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class NodeClassTest {

        @Test
        @Order(1)
        @DisplayName("A nested class Node exists")
        void nodeClassExists() {
            var nestedClassList = Arrays.stream(HashTable.class.getDeclaredClasses())
                    .map(Class::getSimpleName)
                    .toList();

            assertThat(nestedClassList).contains("Node");
        }

        @Test
        @Order(2)
        @DisplayName("Node is a static nested class")
        @SneakyThrows
        void nodeIsStaticClass() {
            var nodeClass = Class.forName("com.bobocode.cs.HashTable$Node");

            assertTrue(isStatic(nodeClass.getModifiers()));
        }

        @Test
        @Order(3)
        @DisplayName("Node class has two type parameters")
        @SneakyThrows
        void nodeHasTwoTypeParams() {
            var nodeClass = Class.forName("com.bobocode.cs.HashTable$Node");
            var typeParametersList = nodeClass.getTypeParameters();

            assertThat(typeParametersList).hasSize(2);
        }

        @Test
        @Order(4)
        @DisplayName("Node class has 'key' and 'value' fields")
        @SneakyThrows
        void nodeHasKeyValuesFields() {
            var nodeClass = Class.forName("com.bobocode.cs.HashTable$Node");
            var fieldNames = Arrays.stream(nodeClass.getDeclaredFields())
                    .map(Field::getName)
                    .toList();

            assertThat(fieldNames).contains("key").contains("value");
        }

        @Test
        @Order(5)
        @DisplayName("Node class has a field 'next'")
        @SneakyThrows
        void nodeHasReferenceToNext() {
            var nodeClass = Class.forName("com.bobocode.cs.HashTable$Node");
            var fieldNames = Arrays.stream(nodeClass.getDeclaredFields())
                    .map(Field::getName)
                    .toList();

            assertThat(fieldNames).contains("next");
        }

        @Test
        @Order(6)
        @DisplayName("Node class has one constructor")
        @SneakyThrows
        void nodeHasOneConstructor() {
            var nodeClass = Class.forName("com.bobocode.cs.HashTable$Node");

            var constructors = nodeClass.getDeclaredConstructors();

            assertThat(constructors).hasSize(1);
        }

        @Test
        @Order(7)
        @DisplayName("Node constructor accept key and value")
        @SneakyThrows
        void nodeConstructorAcceptKeyValue() {
            var nodeClass = Class.forName("com.bobocode.cs.HashTable$Node");

            var constructor = nodeClass.getDeclaredConstructors()[0];

            assertThat(constructor.getParameters()).hasSize(2);
            assertThat(constructor.getParameters()[0].getName()).isEqualTo("key");
            assertThat(constructor.getParameters()[1].getName()).isEqualTo("value");
        }
    }

    @Nested
    @Order(2)
    @DisplayName("2. HashTable fields Test")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class HashTableFieldsTest {

        @Test
        @Order(1)
        @DisplayName("HastTable has a field 'table' which is an array of nodes")
        @SneakyThrows
        void tableFieldExists() {
            var tableField = HashTable.class.getDeclaredField("table");
            var tableType = tableField.getType();
            var nodeClass = Class.forName("com.bobocode.cs.HashTable$Node");

            assertTrue(tableType.isArray());
            assertThat(tableType.getComponentType()).isEqualTo(nodeClass);
        }

        @Test
        @Order(2)
        @DisplayName("HashTable has an integer field 'size'")
        @SneakyThrows
        void sizeFieldExists() {
            var sizeField = HashTable.class.getDeclaredField("size");

            assertThat(sizeField.getType()).isEqualTo(int.class);
        }
    }

    @Nested
    @Order(3)
    @DisplayName("3. HashTable constructors Test")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class HashTableConstructorsTest {

        @Test
        @Order(1)
        @SneakyThrows
        @DisplayName("A default constructor initializes an array with default size 8")
        void defaultConstructor() {
            var defaultConstructor = HashTable.class.getConstructor();

            var hashTable = defaultConstructor.newInstance();
            var table = getInternalTable(hashTable);

            assertThat(table).hasSize(8);
        }

        @Test
        @Order(2)
        @SneakyThrows
        @DisplayName("An additional constructor accepts an initial array size")
        void constructorWithTableCapacity() {
            var constructor = HashTable.class.getConstructor(int.class);

            var hashTable = constructor.newInstance(16);
            var table = getInternalTable(hashTable);

            assertThat(table).hasSize(16);
        }

        @Test
        @Order(3)
        @SneakyThrows
        @DisplayName("An additional constructor throws exception when argument is negative")
        void constructorWithTableCapacityWhenArgumentIsNegative() {
            var constructor = HashTable.class.getConstructor(int.class);

            assertThatThrownBy(() -> constructor.newInstance(-2))
                    .hasCauseInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @Order(4)
    @DisplayName("4. Hash Function Test")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class HashFunctionTest {

        @Test
        @Order(1)
        @DisplayName("calculateIndex returns the same value for the same key")
        void calculateIndexReturnTheSameValueWhenKeyIsTheSame() {
            var indexSet = Stream.generate(() -> "ASDFDFSD34234234")
                    .limit(10)
                    .map(key -> HashTable.calculateIndex(key, 8))
                    .collect(Collectors.toSet());

            assertThat(indexSet).hasSize(1);
        }

        @Test
        @Order(2)
        @DisplayName("calculateIndex returns different values for different keys")
        void calculateIndexReturnDifferentValuesWheKeysAreDifferent() {
            var arrayCapacity = 8;
            var indexSet = Stream.of("A", "Aa", "AaB", "4234", "2234fasdf", "ASDFDFSD34234234", "afsd-fdfd-ae43-5gd3")
                    .map(str -> HashTable.calculateIndex(str, arrayCapacity))
                    .collect(Collectors.toSet());

            assertThat(indexSet)
                    .hasSizeGreaterThan(1);
        }

        @Test
        @Order(3)
        @DisplayName("calculateIndex returns values in array bounds")
        void calculateIndexReturnIndexInArrayBounds() {
            var arrayCapacity = 8;
            var keys = Stream.generate(() -> ThreadLocalRandom.current().nextLong())
                    .limit(100)
                    .toList();

            var indexes = keys.stream()
                    .map(key -> HashTable.calculateIndex(key, arrayCapacity))
                    .toList();

            assertThat(indexes)
                    .isNotEmpty()
                    .allMatch(i -> i >= 0 && i < arrayCapacity);
        }

        @Test
        @Order(4)
        @DisplayName("calculateIndex return non-negative value when hashCode is negative")
        void calculateIndexReturnPositiveIndexWhenHashCodeIsNegative() {
            var key = Long.MAX_VALUE;

            var index = HashTable.calculateIndex(key, 8);

            assertThat(index).isNotNegative();
        }
    }

    @Nested
    @Order(5)
    @DisplayName("5. HashTable methods Test")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class HashTableMethodsTest {

        @Test
        @SneakyThrows
        @Order(1)
        @DisplayName("put creates new entry and returns null when the table is empty, should increase the table size")
        void putWhenTableIsEmpty() {
            var previousValue = hashTable.put("madmax", 833);

            var keyValueExists = checkKeyValueExists("madmax", 833);

            assertNull(previousValue);
            assertTrue(keyValueExists);
            assertEquals(1, getSize());
        }

        @Test
        @Order(2)
        @DisplayName("put elements adds entry to the same bucket and increases table size when the hash code is the same")
        @SneakyThrows
        void putTwoElementsWithTheSameHashCode() {
            var table = getInternalTable(hashTable);
            var prevValueA = hashTable.put("AaAa", 123);
            var prevValueB = hashTable.put("BBBB", 456);
            var containsKeyValueA = checkKeyValueExists("AaAa", 123);
            var containsKeyValueB = checkKeyValueExists("BBBB", 456);
            var bucketIndexA = HashTable.calculateIndex("AaAa", table.length);
            var bucketIndexB = HashTable.calculateIndex("BBBB", table.length);

            assertNull(prevValueA);
            assertNull(prevValueB);
            assertTrue(containsKeyValueA);
            assertTrue(containsKeyValueB);
            assertThat(bucketIndexA).isEqualTo(bucketIndexB);
            assertEquals(2, getSize());
        }

        @Test
        @Order(3)
        @DisplayName(
                "put element updates the value and returns the previous one when key is the same, should not increase table size")
        void putElementWithTheSameKey() {
            hashTable.put("madmax", 833);

            var previousValue = hashTable.put("madmax", 876);
            var containsNewValueByKey = checkKeyValueExists("madmax", 876);

            assertThat(previousValue).isEqualTo(833);
            assertTrue(containsNewValueByKey);
            assertEquals(1, getSize());
        }

        @Test
        @Order(4)
        @DisplayName("get returns null when given key does not exists")
        void getElementWhenKeyDoesNotExists() {
            var foundValue = hashTable.get("xxx");

            assertNull(foundValue);
        }

        @Test
        @Order(5)
        @DisplayName("get returns a corresponding value by the given key")
        void getWhenKeyExists() {
            addToTable("madmax", 833);

            var foundValue = hashTable.get("madmax");

            assertThat(foundValue).isEqualTo(833);
        }

        @Test
        @Order(6)
        @DisplayName("get returns a corresponding value when there are other keys with the same index")
        void getWhenOtherKeyHaveTheSameIndex() {
            addToTable("madmax", 833);
            addToTable("AaAa", 654);
            addToTable("BBBB", 721);

            var foundValue = hashTable.get("BBBB");

            assertThat(foundValue).isEqualTo(721);
        }

        @Test
        @Order(7)
        @DisplayName("containsKey returns true if element exists")
        void containsKeyWhenElementExists() {
            addToTable("madmax", 833);

            var result = hashTable.containsKey("madmax");

            assertThat(result).isTrue();
        }

        @Test
        @Order(8)
        @DisplayName("containsKey returns false if element does not exist")
        void containsKeyWhenElementDoesNotExist() {
            var result = hashTable.containsKey("madmax");

            assertThat(result).isFalse();
        }

        @Test
        @Order(9)
        @DisplayName("containsValue returns true if value exists")
        void containsValue() {
            addToTable("madmax", 833);

            var result = hashTable.containsValue(833);

            assertThat(result).isTrue();
        }

        @Test
        @Order(9)
        @DisplayName("containsValue returns false if value does not exist")
        void containsValueWhenItDoesNotExist() {
            addToTable("madmax", 833);

            var result = hashTable.containsValue(666);

            assertThat(result).isFalse();
        }

        @Test
        @Order(10)
        @DisplayName("containsValue returns true if the same value appears multiple times")
        void containsValueWhenValueAppearsMultipleTimes() {
            addToTable("madmax", 833);
            addToTable("bobby", 833);
            addToTable("altea", 833);

            var result = hashTable.containsValue(833);

            assertThat(result).isTrue();
        }

        @Test
        @Order(11)
        @SneakyThrows
        @DisplayName("size returns the number of entries in the table")
        void size() {
            setSize(12);

            var size = hashTable.size();

            assertThat(size).isEqualTo(12);
        }

        @Test
        @Order(12)
        @DisplayName("isEmpty returns false when there are some elements")
        void isEmptyWhenTableGetsElements() {
            addToTable("madmax", 833);
            setSize(1);

            var empty = hashTable.isEmpty();

            assertFalse(empty);
        }

        @Test
        @Order(13)
        @DisplayName("isEmpty returns true when there is no elements")
        void isEmptyWhenThereIsNoElements() {
            var empty = hashTable.isEmpty();

            assertTrue(empty);
        }

        @Test
        @Order(13)
        @DisplayName("remove deletes the entry, decreases table size and returns a value")
        void remove() {
            addToTable("madmax", 833);
            setSize(1);
            var result = hashTable.remove("madmax");

            assertThat(result).isEqualTo(833);
            assertFalse(checkKeyValueExists("madmaxx", 833));
            assertEquals(0, getSize());
        }

        @Test
        @Order(14)
        @DisplayName("remove returns null when key does not exists")
        void removeWhenKeyDoesNotExists() {
            var result = hashTable.remove("madmax");

            assertNull(result);
        }

        @Test
        @Order(15)
        @DisplayName("remove deletes the element when it's in the middle of the list and decreases the size of table")
        void removeFromTheMiddleOfTheList() {
            addToTable("AaAa", 843);
            addToTable("BBBB", 434);
            addToTable("AaBB", 587);

            var size = 3;
            setSize(size);
            var removedValue = hashTable.remove("BBBB");

            assertTrue(checkKeyValueExists("AaAa", 843));
            assertFalse(checkKeyExists("BBBB"));
            assertTrue(checkKeyValueExists("AaBB", 587));
            assertThat(removedValue).isEqualTo(434);
            assertEquals(size - 1, getSize());
        }

        @Test
        @Order(16)
        @DisplayName("remove deletes the element when it's in the end of the list and decreases the size of table")
        void removeFromTheEndOfTheList() {
            addToTable("AaAa", 843);
            addToTable("BBBB", 434);
            addToTable("AaBB", 587);
            var size = 3;
            setSize(size);

            var removedValue = hashTable.remove("AaBB");

            assertTrue(checkKeyValueExists("AaAa", 843));
            assertTrue(checkKeyValueExists("BBBB", 434));
            assertFalse(checkKeyExists("AaBB"));
            assertThat(removedValue).isEqualTo(587);
            assertEquals(2, getSize());
        }
    }

    @Nested
    @Order(6)
    @DisplayName("6. Helper methods Test")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class HashTableHelperMethodsTest {

        @Test
        @Order(1)
        @DisplayName("resizeTable creates a new array and put there all elements")
        void resizeTable() {
            addToTable("madmax", 833);
            addToTable("altea", 553);
            addToTable("AaAa", 123);
            addToTable("BBBB", 456);

            hashTable.resizeTable(16);

            assertThat(getInternalTable(hashTable)).hasSize(16);
            assertTrue(checkKeyValueExists("madmax", 833));
            assertTrue(checkKeyValueExists("altea", 553));
            assertTrue(checkKeyValueExists("AaAa", 123));
            assertTrue(checkKeyValueExists("BBBB", 456));
        }

        @Test
        @DisplayName("toString returns a string that represents an underlying table")
        void toStringTest() {
            addToTable("madmax", 833);
            addToTable("altea", 553);
            addToTable("johnny", 439);
            addToTable("leon", 886);
            var table = getInternalTable(hashTable);
            var expectedString = tableToString(table);

            String tableStr = hashTable.toString();

            assertThat(tableStr).isEqualTo(expectedString);
        }

    }

    // Util methods
    @SneakyThrows
    private Object[] getInternalTable(HashTable<?, ?> hashTable) {
        var tableField = HashTable.class.getDeclaredField("table");
        tableField.setAccessible(true);
        return (Object[]) tableField.get(hashTable);
    }

    private void addToTable(String key, Integer value) {
        var table = getInternalTable(hashTable);
        var index = HashTable.calculateIndex(key, table.length);
        var newNode = createNewNode(key, value);
        if (table[index] == null) {
            table[index] = newNode;
        } else {
            var current = new NodeProxy(table[index]);
            while (current.next() != null && !current.key().equals(key)) {
                current = current.next();
            }
            if (current.key().equals(key)) {
                current.setValue(value);
            } else {
                current.setNext(newNode);
            }
        }
    }

    private NodeProxy getNodeByKey(Object key) {
        var table = getInternalTable(hashTable);
        for (var head : table) {
            if (head != null) {
                var current = new NodeProxy(head);
                while (current != null) {
                    if (current.key().equals(key)) {
                        return current;
                    }
                    current = current.next();
                }
            }
        }
        return null;
    }

    private boolean checkKeyValueExists(Object key, Object value) {
        var node = getNodeByKey(key);
        return node != null && node.value().equals(value);
    }

    private boolean checkKeyExists(Object key) {
        var node = getNodeByKey(key);
        return node != null;
    }

    @SneakyThrows
    private void setSize(int size) {
        var sizeField = HashTable.class.getDeclaredField("size");
        sizeField.setAccessible(true);
        sizeField.set(hashTable, size);
    }

    @SneakyThrows
    private int getSize() {
        var sizeField = HashTable.class.getDeclaredField("size");
        sizeField.setAccessible(true);
        return sizeField.getInt(hashTable);
    }

    private String tableToString(Object[] table) {
        StringBuilder result = new StringBuilder();
        var n = table.length;
        for (int i = 0; i < n; i++) {
            result.append(i).append(": ");
            if (table[i] != null) {
                var current = new NodeProxy(table[i]);
                while (current.next() != null) {
                    result.append(current.key()).append("=").append(current.value()).append(" -> ");
                    current = current.next();
                }
                result.append(current.key()).append("=").append(current.value());
            }
            result.append("\n");
        }
        return result.toString();
    }

    @SneakyThrows
    private Object createNewNode(String key, Integer value) {
        var nodeClass = Class.forName("com.bobocode.cs.HashTable$Node");
        var constructor = nodeClass.getConstructor(Object.class, Object.class);
        return constructor.newInstance(key, value);
    }

    static class NodeProxy {

        Class<?> targetClass;
        Field keyField;
        Field valueField;
        Field nextField;
        Object target;

        @SneakyThrows
        public NodeProxy(Object target) {
            Objects.requireNonNull(target);
            this.targetClass = Class.forName("com.bobocode.cs.HashTable$Node");
            this.target = target;
            this.keyField = targetClass.getDeclaredField("key");
            this.keyField.setAccessible(true);
            this.valueField = targetClass.getDeclaredField("value");
            this.valueField.setAccessible(true);
            this.nextField = targetClass.getDeclaredField("next");
            this.nextField.setAccessible(true);
        }

        @SneakyThrows
        public Object key() {
            return keyField.get(target);
        }

        @SneakyThrows
        public Object value() {
            return valueField.get(target);
        }

        @SneakyThrows
        public NodeProxy next() {
            return Optional.ofNullable(nextField.get(target))
                    .map(NodeProxy::new)
                    .orElse(null);
        }

        @SneakyThrows
        public void setValue(Object value) {
            valueField.set(target, value);
        }

        @SneakyThrows
        public void setNext(Object newNode) {
            nextField.set(target, newNode);
        }
    }
}