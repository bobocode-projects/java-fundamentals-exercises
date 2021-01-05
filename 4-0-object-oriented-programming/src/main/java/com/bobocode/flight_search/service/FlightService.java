package com.bobocode.flight_search.service;

import com.bobocode.flight_search.data.FlightDao;
import com.bobocode.util.ExerciseNotCompletedException;

import java.util.List;

/**
 * {@link FlightService} provides an API that allows to manage flight numbers
 * <p>
 * todo: 1. Using {@link FlightDao} implement method {@link FlightService#registerFlight(String)}
 * todo: 2. Using {@link FlightDao} implement method {@link FlightService#searchFlights(String)}
 */
public class FlightService {

    /**
     * Adds a new flight number
     *
     * @param flightNumber a flight number to add
     * @return {@code true} if a flight number was added, {@code false} otherwise
     */
    public boolean registerFlight(String flightNumber) {
        throw new ExerciseNotCompletedException();
    }

    /**
     * Returns all flight numbers that contains a provided key.
     *
     * @param query a search query
     * @return a list of found flight numbers
     */
    public List<String> searchFlights(String query) {
        throw new ExerciseNotCompletedException();
    }
}
