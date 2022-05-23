package com.example.adobe.service.date_time;

import com.example.adobe.domain.date_time.DateTimeDetails;
import com.example.adobe.domain.date_time.DateTimeResponse;
import com.example.adobe.domain.date_time.DateTimeService;
import com.example.adobe.exception.FlightNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class FlightDateTimeServiceImpl implements FlightDateTimeService {

    private final DateTimeService dateTimeService;

    public FlightDateTimeServiceImpl(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }


    @Override
    public DateTimeDetails getFlightDateTime(String fromLocation, String toLocation) {
        try {
            DateTimeResponse dateTimeResponse = dateTimeService
                    .getRemoteDateTimeFlight(fromLocation, toLocation)
                    .get()
                    .getBody();

            return dateTimeResponse.getResponse()
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new FlightNotFoundException(""));

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new FlightNotFoundException("Could not find a flight with your requirements");
    }
}
