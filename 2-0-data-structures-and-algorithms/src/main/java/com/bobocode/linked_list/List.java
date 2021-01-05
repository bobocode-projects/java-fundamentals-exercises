package com.bobocode.linked_list;


public interface List<E> {
    void add(E element);

    void add(int index, E element);

    void set(int index, E element);

    E get(int index);

    E getFirst();

    E getLast();

    void remove(int index);

    boolean contains(E element);

    boolean isEmpty();

    int size();

    void clear();

}
