package com.bobocode.homework;

/**
 * A generic class Node that supports two type params: one for the key and one for the value.
 *
 * @param <K> key type parameter
 * @param <V> value type parameter
 */
public class Node<K, V> {
    K key;
    V value;
    Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void print() {
        System.out.print(key + ":" + value);
        var tmp = next;
        while (tmp != null) {
            System.out.print(" -> " + tmp.key + ":" + tmp.value);
            tmp = tmp.next;
        }
        System.out.println();
    }
}
