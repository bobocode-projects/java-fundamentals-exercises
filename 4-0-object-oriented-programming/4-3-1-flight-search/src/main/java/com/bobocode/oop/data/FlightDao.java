package com.bobocode.oop.data;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * {@link FlightDao} represents a Data Access Object (DAO) for flights. The implementation is simplified, so it just
 * uses {@link HashSet} to store flight numbers.
 * <p>
 * todo: 1. Implement a method {@link FlightDao#register(String)} that store new flight number into the set
 * todo: 2. Implement a method {@link FlightDao#findAll()} that returns a set of all flight numbers
 */
public class FlightDao implements IFlightDao {

    private final Set<String> flights = new HashSet<>();

    /**
     * Stores a new flight number
     *
     * @param flightNumber a flight number to store
     * @return {@code true} if a flight number was stored, {@code false} otherwise
     */
    @Override
    public boolean register(String flightNumber) {
        if (!flights.contains(flightNumber)) {
            this.flights.add(flightNumber);
            return true;
        } else return false;
    }

    /**
     * Returns all stored flight numbers
     *
     * @return a set of flight numbers
     */
    @Override
    public Set<String> findAll() {
        return Collections.unmodifiableSet(flights);
    }

}
