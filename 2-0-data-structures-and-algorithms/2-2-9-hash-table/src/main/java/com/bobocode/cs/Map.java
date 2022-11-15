package com.bobocode.cs;

/**
 * A {@link Map} is a simplified interface of so-called dictionary. It maps keys to values and provides an API for data
 * access and manipulation. Please note that a map does not support duplicate keys.
 * <p>
 * It was added as a simple contact on top of a {@link HashTable} class.
 *
 * @param <K> key type
 * @param <V> value type
 * @author Taras Boychuk
 */
public interface Map<K, V> {
    /**
     * Creates or updates a mapping for a given key and value. If the key is new, it creates a mapping and return null.
     * If the key exists, it updates the value and returns the old value.
     *
     * @param key
     * @param value
     * @return an old value or null
     */
    V put(K key, V value);

    /**
     * Returns the value that is mapped to the given key, or null if there is no such key.
     *
     * @param key
     * @return the value that is mapped to the given key, or null if there is no such key
     */
    V get(K key);

    /**
     * Returns true if a given key exist or false otherwise.
     *
     * @param key
     * @return true if a given key exist or false otherwise
     */
    boolean containsKey(K key);

    /**
     * Returns true if a given value exist or false otherwise.
     *
     * @param value
     * @return true if a given value exist or false otherwise
     */
    boolean containsValue(V value);

    /**
     * Returns the number of entries (key-value mappings).
     *
     * @return the number of entries
     */
    int size();

    /**
     * Returns true if there is no entries or false otherwise.
     *
     * @return true if there is no entries or false otherwise
     */
    boolean isEmpty();

    /**
     * Removes a mapping for a given key, and returns a removed value. If there is no such key, it just returns null.
     *
     * @param key
     * @return a removed value or null
     */
    V remove(K key);
}
