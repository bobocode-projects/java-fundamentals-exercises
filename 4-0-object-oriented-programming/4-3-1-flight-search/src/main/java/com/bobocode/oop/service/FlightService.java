package com.bobocode.oop.service;

import com.bobocode.oop.data.FlightDao;
import com.bobocode.oop.data.IFlightDao;
import com.bobocode.util.ExerciseNotCompletedException;

import java.util.List;
import java.util.Set;

/**
 * {@link FlightService} provides an API that allows to manage flight numbers
 * <p>
 * todo: 1. Using {@link com.bobocode.oop.data.FlightDao} implement method {@link FlightService#registerFlight(String)}
 * todo: 2. Using {@link com.bobocode.oop.data.FlightDao} implement method {@link FlightService#searchFlights(String)}
 */
public class FlightService {

    private final IFlightDao flightDao;

    public FlightService() {
        this.flightDao = new FlightDao();
    }

    public FlightService(IFlightDao flightDao) {
        this.flightDao = flightDao;
    }

    /**
     * Adds a new flight number
     *
     * @param flightNumber a flight number to add
     * @return {@code true} if a flight number was added, {@code false} otherwise
     */
    public boolean registerFlight(String flightNumber) {
        return flightDao.register(flightNumber);
    }

    /**
     * Returns all flight numbers that contains a provided key.
     *
     * @param query a search query
     * @return a list of found flight numbers
     */
    public List<String> searchFlights(String query) {
        return flightDao.findAll().stream().filter(flightId -> flightId.contains(query)).toList();
    }
}
