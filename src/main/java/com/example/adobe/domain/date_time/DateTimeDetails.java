package com.example.adobe.domain.date_time;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DateTimeDetails {
    private final String airline_iata;
    private final String airline_icao;
    private final String dep_iata;
    private final String dep_time;
    private final String arr_iata;
    private final String arr_time;

}
