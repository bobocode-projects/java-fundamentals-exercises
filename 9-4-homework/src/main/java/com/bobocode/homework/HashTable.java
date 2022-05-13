package com.bobocode.homework;

public class HashTable<K, V> {
    private final int DEFAULT_CAPACITY = 12;

    @SuppressWarnings("unchecked")
    private Node<K, V>[] tab = new Node[DEFAULT_CAPACITY];
    private int size = 0;

    public void put(K key, V value) {
        if (size >= tab.length * 0.75) {
            resize(tab.length * 2);
        }
        put(tab, key, value);
    }

    private void put(Node<K, V>[] table, K key, V value) {
        int index = Math.abs(key.hashCode() % table.length);
        Node<K, V> node = table[index];
        if (node == null) {
            table[index] = new Node<>(key, value);
            size++;
            return;
        }

        while (node != null) {
            if (key.equals(node.key)) {
                node.value = value;
                return;
            } else if (node.next == null) {
                node.next = new Node<>(key, value);
                size++;
                return;
            }
            node = node.next;
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        size = 0;
        Node<K, V>[] newTab = new Node[newSize];
        for (Node<K, V> node: tab) {
            while (node != null) {
                put(newTab, node.key, node.value);
                node = node.next;
            }
        }
        tab = newTab;
    }

    public void printTable() {
        for (int i = 0; i < tab.length; i++) {
            System.out.print(i + ": ");
            if (tab[i] != null) {
                tab[i].print();
            } else {
                System.out.println();
            }
        }
    }

    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
