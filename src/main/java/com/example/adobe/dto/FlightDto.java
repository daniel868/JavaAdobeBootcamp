package com.example.adobe.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FlightDto {
    private final Long aircraftId;
    private final Long flightType;
    private final String departureDateTime;
    private final String landingDateTime;
    private final String fromLocation;
    private final String toLocation;

}
