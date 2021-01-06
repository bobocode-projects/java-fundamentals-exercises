package com.bobocode.flight_search.service;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * {@link FlightService} provides an API that allows to manage flight numbers
 * <p>
 * todo: 1. Using {@link com.bobocode.flight_search.data.FlightDao} implement method {@link FlightService#registerFlight(String)}
 * todo: 2. Using {@link com.bobocode.flight_search.data.FlightDao} implement method {@link FlightService#searchFlights(String)}
 */
public class FlightService {

    private Flights flights;

    public FlightService(Flights flights) {
        this.flights = flights;
    }

    /**
     * Adds a new flight number
     *
     * @param flightNumber a flight number to add
     * @return {@code true} if a flight number was added, {@code false} otherwise
     */
    public boolean registerFlight(String flightNumber) {
        return flights.register(flightNumber);
    }

    /**
     * Returns all flight numbers that contains a provided key.
     *
     * @param query a search query
     * @return a list of found flight numbers
     */
    public List<String> searchFlights(String query) {
        return flights.findAll().stream()
                .filter(flightNum -> flightNum.toUpperCase().contains(query.toUpperCase()))
                .collect(toList());
    }
}
