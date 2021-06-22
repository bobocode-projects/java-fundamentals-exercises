package com.bobobode.cs;

/**
 * Class {@link Node} is a very simple data structure that consists of an element itself and the reference to the next
 * node. An element can have any value since it's a generic. A reference to the next node allows to link {@link Node}
 * objects and build more comprehensive data structures on top of those liked nodes.
 *
 * @param <T> a generic type T
 */
public class Node<T> {

    private T currentElement;
    private Node <T> nextElement;
    public Node <T> getNextElement () {
        return nextElement;
    }

    public Node(T currentElement) {

        this.currentElement = currentElement;
        this.nextElement = null;
    }

    public void setNextElement(Node <T> nextElement) {
        this.nextElement = nextElement;
    }

}


