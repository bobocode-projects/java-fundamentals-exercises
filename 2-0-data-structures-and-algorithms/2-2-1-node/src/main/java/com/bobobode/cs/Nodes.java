package com.bobobode.cs;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that consists of static methods only and provides util methods for {@link Node}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com">visit our website</a></strong>
 * <p>
 *
 * @author Taras Boychuk
 */
public class Nodes {

    private Nodes() {
    }

    /**
     * Creates a new instance of {@link Node} that holds provided element
     *
     * @param element any element of type T
     * @param <T>     generic type
     * @return a new instance of {@link Node}
     */
    public static <T> Node<T> create(T element) {
        return new Node<>(element);
    }

    /**
     * Create a connection between first and second nodes, so object first stores a reference to the second.
     *
     * @param first  any {@link Node} object
     * @param second any {@link Node} object
     * @param <T>    a genetic type
     */
    public static <T> void link(Node<T> first, Node<T> second) {
        if (first == null || second == null) { return; }
        first.setNext(second);
    }

    /**
     * Creates two new {@link Node} objects using provided firstElement and secondElement, and create a connection
     * between those two elements so the first node will hold a reference to a second one.
     *
     * @param firstElement  any element of type T
     * @param secondElement any element of type T
     * @param <T>           a genetic type
     * @return a reference to a first node created based on firstElement
     */
    public static <T> Node<T> pairOf(T firstElement, T secondElement) {
        return new Node<>(firstElement, new Node<>(secondElement));
    }

    /**
     * Creates two new {@link Node} objects using provided firstElement and secondElement, and creates connections
     * between those nodes so the first node will hold a reference to a second one, and a second node will hold
     * a reference to the first one.
     *
     * @param firstElement  any element of type T
     * @param secondElement any element of type T
     * @param <T>           generic type T
     * @return a reference to the first node
     */
    public static <T> Node<T> closedPairOf(T firstElement, T secondElement) {
        var firstNode = new Node<>(firstElement);
        var secondNode = new Node<>(secondElement);
        firstNode.setNext(secondNode);
        secondNode.setNext(firstNode);
        return firstNode;
    }

    /**
     * Creates a linked chain of {@link Node} objects based on provided elements. Creates a connection between those
     * nodes so each node will hold a reference to the next one in the chain. HINT: it's basically a linked list.
     *
     * @param elements a array of elements of type T
     * @param <T>      generic type T
     * @return a reference to the first element of the chain
     */
    public static <T> Node<T> chainOf(T... elements) {
        List<T> elementsList = List.of(elements);
        List<Node> nodes = new ArrayList<>(elementsList.size());
        for (int i = 0; i <= elementsList.size() - 1; i++) {
            if (i == elementsList.size() - 1) {
                nodes.add(new Node<>(elementsList.get(i)));
            } else {
                var firstNode = i != 0 ? nodes.getLast() : new Node<>(elementsList.get(i));
                var nextNode = new Node<>(elementsList.get(i + 1));
                firstNode.setNext(nextNode);
                nodes.add(firstNode);
                nodes.add(nextNode);
            }
        }
        return nodes.getFirst();
    }

    /**
     * Creates a linked circle of {@link Node} objects based on provided elements. Creates a connection between those
     * nodes so each node will hold a reference to the next one in the chain, and the last one will hold a reference to
     * the first one.
     *
     * @param elements a array of elements of type T
     * @param <T>      generic type T
     * @return a reference to the first element of the chain
     */
    public static <T> Node<T> circleOf(T... elements) {
        List<T> elementsList = List.of(elements);
        List<Node> nodes = new ArrayList<>(elementsList.size());
        for (int i = 0; i <= elementsList.size() - 1; i++) {
            if (i == elementsList.size() - 1) {
                nodes.getLast().setNext(nodes.getFirst());
            } else {
                var firstNode = i != 0 ? nodes.getLast() : new Node<>(elementsList.get(i));
                var nextNode = new Node<>(elementsList.get(i + 1));
                firstNode.setNext(nextNode);
                nodes.add(firstNode);
                nodes.add(nextNode);
            }
        }
        return nodes.getFirst();
    }
}
