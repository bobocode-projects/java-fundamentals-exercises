package com.bobocode.oop.factory;

import com.bobocode.oop.data.FlightDao;
import com.bobocode.oop.service.FlightService;

/**
 * {@link FlightServiceFactory} is used to create an instance of {@link FlightService}
 * <p>
 * todo: 1. Implement method {@link FlightServiceFactory#creteFlightService()}
 */
public class FlightServiceFactory {

    /**
     * Create a new instance of {@link FlightService}
     *
     * @return FlightService
     */
    public FlightService creteFlightService() {
        return new FlightService(new FlightDao());
    }
}
