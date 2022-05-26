package com.example.adobe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AircraftDto {
    private int aircraftType;
    private String aircraftName;
    private int enginesNumber;
    private float maxSpeed;

    private float maxWeight;
    private int totalPackageNumber;

    private Double totalFlightHours;
    private int passengerSeats;

}
