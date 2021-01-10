package com.bobocode.array_wrapper;

public interface ListOfLongs {

    int add(long element);

    void add(int index, long element);

    long get(int index);

    void set(int index, long element);

    void remove(int index);

    int find(long element);

    long[] toArray();

    String toString();

    int size();
}
