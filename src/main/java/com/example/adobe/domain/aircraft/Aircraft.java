package com.example.adobe.domain.aircraft;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Aircraft {
    protected final String aircraftName;
    protected final int enginesNumber;
    protected final Double totalFlightHours;

    public Aircraft(String aircraftName, int enginesNumber, Double totalFlightHours) {
        this.aircraftName = aircraftName;
        this.enginesNumber = enginesNumber;
        this.totalFlightHours = totalFlightHours;
    }
}
