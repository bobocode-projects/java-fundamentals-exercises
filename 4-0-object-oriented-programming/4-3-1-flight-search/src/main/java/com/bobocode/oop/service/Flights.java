package com.bobocode.flight_search.service;

import java.util.Set;

public interface Flights {
    boolean register(String flightNumber);

    Set<String> findAll();
}
