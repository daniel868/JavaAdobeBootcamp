package com.example.adobe.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlightDto {
    private Long aircraftId;
    private Long flightType;

    private String departureDateTime;
    private String landingDateTime;
    private String fromLocation;
    private String toLocation;

}
