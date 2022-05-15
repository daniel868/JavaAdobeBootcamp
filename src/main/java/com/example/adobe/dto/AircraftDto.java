package com.example.adobe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AircraftDto {
    private final int aircraftType;
    private final String aircraftName;
    private final int enginesNumber;
    private final float maxSpeed;

    private float maxWeight;
    private int totalPackageNumber;

    private Double totalFlightHours;
    private int passengerSeats;

}
