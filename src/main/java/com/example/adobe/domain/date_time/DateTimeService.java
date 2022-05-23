package com.example.adobe.domain.date_time;

import kong.unirest.HttpResponse;

import java.util.concurrent.CompletableFuture;

public interface DateTimeService {
    CompletableFuture<HttpResponse<DateTimeResponse>> getRemoteDateTimeFlight(String fromLocation, String toLocation);

}
