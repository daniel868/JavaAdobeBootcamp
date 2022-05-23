package com.example.adobe.domain.date_time;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class DateTimeClient implements DateTimeService {

    private final static String URL = "https://airlabs.co/api/v9/schedules";
    private final static String API_KEY = "16b2a750-2d0d-42aa-b776-5d91ff73a7d5";

    @Async
    @Override
    public CompletableFuture<HttpResponse<DateTimeResponse>> getRemoteDateTimeFlight(String fromLocation, String toLocation) {
        String flightUrl = String.format("%s?dep_iata=%s&arr_iata=%s", URL,
                fromLocation,
                toLocation);

        return Unirest.get(flightUrl)
                .queryString("api_key", API_KEY)
                .asObjectAsync(DateTimeResponse.class);

    }

}
