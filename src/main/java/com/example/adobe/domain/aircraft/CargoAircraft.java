package com.example.adobe.domain.aircraft;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargoAircraft extends Aircraft{
    private final float maxWeight;
    private final float maxSpeed;
    @Builder
    public CargoAircraft(String aircraftName, int enginesNumber, Double totalFlightHours, float maxWeight, float maxSpeed) {
        super(aircraftName, enginesNumber, totalFlightHours);
        this.maxWeight = maxWeight;
        this.maxSpeed = maxSpeed;
    }
}
