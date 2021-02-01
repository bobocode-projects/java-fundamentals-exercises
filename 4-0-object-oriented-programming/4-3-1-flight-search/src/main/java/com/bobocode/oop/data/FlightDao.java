package com.bobocode.oop.data;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.HashSet;
import java.util.Set;

/**
 * {@link FlightDao} represents a Data Access Object (DAO) for flights. The implementation is simplified, so it just
 * uses {@link HashSet} to store flight numbers.
 * <p>
 * todo: 1. Implement a method {@link FlightDao#register(String)} that store new flight number into the set
 * todo: 2. Implement a method {@link FlightDao#findAll()} that returns a set of all flight numbers
 */
public class FlightDao {
    private Set<String> flights = new HashSet<>();

    /**
     * Stores a new flight number
     *
     * @param flightNumber a flight number to store
     * @return {@code true} if a flight number was stored, {@code false} otherwise
     */
    public boolean register(String flightNumber) {
        throw new ExerciseNotCompletedException();// todo: implement this method
    }

    /**
     * Returns all stored flight numbers
     *
     * @return a set of flight numbers
     */
    public Set<String> findAll() {
        throw new ExerciseNotCompletedException();// todo: implement this method
    }

}
