package com.example.adobe.entity.flight;

public enum FlightType {
    COMMERCIAL_FLIGHT("Commercial_flight"), CARGO_FLIGHT("Cargo_Flight");
    private final String flightType;

    FlightType(String flightType) {
        this.flightType = flightType;
    }

    public String getFlightType() {
        return flightType;
    }
}
