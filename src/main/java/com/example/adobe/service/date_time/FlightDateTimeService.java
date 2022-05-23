package com.example.adobe.service.date_time;

import com.example.adobe.domain.date_time.DateTimeDetails;
import com.example.adobe.domain.date_time.DateTimeResponse;

import java.util.Optional;

public interface FlightDateTimeService {
    DateTimeDetails getFlightDateTime(String fromLocation, String toLocation);
}
