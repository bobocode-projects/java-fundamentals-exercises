package com.bobocode.oop.data;

import java.util.Set;

public interface IFlightDao {

    boolean register(String flightNumber);

    Set<String> findAll();
}
