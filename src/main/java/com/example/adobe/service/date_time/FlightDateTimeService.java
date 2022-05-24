package com.example.adobe.service.date_time;

import com.example.adobe.domain.date_time.DateTimeDetails;

public interface FlightDateTimeService {
    DateTimeDetails getFlightDateTime(String fromLocation, String toLocation);
}
